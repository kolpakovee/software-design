package ru.kolpakovee.authorization_microservice.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kolpakovee.authorization_microservice.pojo.AuthResponse;
import ru.kolpakovee.authorization_microservice.pojo.LoginRequest;
import ru.kolpakovee.authorization_microservice.pojo.RegisterRequest;
import ru.kolpakovee.authorization_microservice.pojo.Response;
import ru.kolpakovee.authorization_microservice.services.AuthService;
import ru.kolpakovee.authorization_microservice.services.SessionService;

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
