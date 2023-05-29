package ru.kolpakovee.restaurantapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.restaurantapi.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    public Optional<User> findByEmail(String email);
}
