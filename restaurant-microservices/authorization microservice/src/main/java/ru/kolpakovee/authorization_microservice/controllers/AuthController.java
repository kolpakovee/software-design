package ru.kolpakovee.authorization_microservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kolpakovee.authorization_microservice.exceptions.InvalidTokenException;
import ru.kolpakovee.authorization_microservice.pojo.*;
import ru.kolpakovee.authorization_microservice.services.AuthService;
import ru.kolpakovee.authorization_microservice.services.JwtService;
import ru.kolpakovee.authorization_microservice.services.SessionService;
import ru.kolpakovee.authorization_microservice.services.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SessionService sessionService;

    @PostMapping("/register")
    public ResponseEntity<Response> register(@Valid @RequestBody RegisterRequest request) {
        var response = authService.register(request);
        sessionService.save(request, response);

        return ResponseEntity.ok(new Response("User successfully registered."));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        var response = authService.login(request);
        sessionService.save(request, response);

        return ResponseEntity.ok(response);
    }
}
