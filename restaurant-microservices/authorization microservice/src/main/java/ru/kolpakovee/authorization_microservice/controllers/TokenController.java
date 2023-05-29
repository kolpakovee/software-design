package ru.kolpakovee.authorization_microservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolpakovee.authorization_microservice.exceptions.InvalidTokenException;
import ru.kolpakovee.authorization_microservice.pojo.UserInfoResponse;
import ru.kolpakovee.authorization_microservice.services.JwtService;
import ru.kolpakovee.authorization_microservice.services.UserService;

@RestController
@RequestMapping("/token")
@RequiredArgsConstructor
public class TokenController {
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/validate")
    public ResponseEntity<Boolean> validateToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        boolean isValid = jwtService.isValidToken(authToken);
        return ResponseEntity.ok(isValid);
    }

    @GetMapping("/user-info")
    public ResponseEntity<UserInfoResponse> userInfo(@RequestHeader(HttpHeaders.AUTHORIZATION) String authToken) {
        if (!jwtService.isValidToken(authToken)) {
            throw new InvalidTokenException("Invalid token.");
        }
        String email = jwtService.extractEmail(authToken.substring(7));
        return ResponseEntity.ok(userService.getUserInfoByEmail(email));
    }
}
