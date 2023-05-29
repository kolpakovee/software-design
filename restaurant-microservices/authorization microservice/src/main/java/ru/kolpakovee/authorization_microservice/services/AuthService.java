package ru.kolpakovee.authorization_microservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kolpakovee.authorization_microservice.entities.User;
import ru.kolpakovee.authorization_microservice.enums.UserRole;
import ru.kolpakovee.authorization_microservice.exceptions.NotFoundEmailException;
import ru.kolpakovee.authorization_microservice.exceptions.NoSuchRoleExistsException;
import ru.kolpakovee.authorization_microservice.exceptions.UserAlreadyExistException;
import ru.kolpakovee.authorization_microservice.pojo.LoginRequest;
import ru.kolpakovee.authorization_microservice.pojo.AuthResponse;
import ru.kolpakovee.authorization_microservice.pojo.RegisterRequest;
import ru.kolpakovee.authorization_microservice.repositories.UserRepository;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager manager;

    public AuthResponse register(RegisterRequest request) {

        if (repository.findByUsername(request.getUsername()).isPresent() ||
                repository.findByEmail(request.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("User already exists.");
        }

        try {
            UserRole.valueOf(request.getRole());
        } catch (IllegalArgumentException e) {
            throw new NoSuchRoleExistsException("No such role exists.");
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .role(UserRole.valueOf(request.getRole()))
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        repository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthResponse login(LoginRequest request) {
        var user = repository.findByEmail(request.getEmail());

        if (user.isEmpty()){
            throw new NotFoundEmailException("User with this email was not found.");
        }

        manager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var jwtToken = jwtService.generateToken(user.get());



        return AuthResponse.builder()
                .token(jwtToken)
                .build();
    }
}
