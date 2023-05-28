package ru.kolpakovee.authorization_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.authorization_microservice.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
