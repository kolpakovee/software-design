package ru.kolpakovee.authorization_microservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kolpakovee.authorization_microservice.entities.Session;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
