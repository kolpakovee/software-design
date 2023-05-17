package ru.kolpakovee;

public record Question(int id, String answer, String question, int value, String airdate, String created_at,
                       String updated_at, int category_id, int game_id, Integer invalid_count, Category category) {
}

