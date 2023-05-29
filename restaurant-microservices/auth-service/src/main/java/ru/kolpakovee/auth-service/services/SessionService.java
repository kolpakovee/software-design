package ru.kolpakovee.authorization_microservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kolpakovee.authorization_microservice.entities.Session;
import ru.kolpakovee.authorization_microservice.pojo.AuthRequest;
import ru.kolpakovee.authorization_microservice.pojo.AuthResponse;
import ru.kolpakovee.authorization_microservice.repositories.SessionRepository;
import ru.kolpakovee.authorization_microservice.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    public void save(AuthRequest request, AuthResponse response) {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("Not found user with email: " + request.getEmail()));

        Session session = Session.builder()
                .sessionToken(response.getToken())
                .user(user)
                .build();

        sessionRepository.save(session);
    }
}
