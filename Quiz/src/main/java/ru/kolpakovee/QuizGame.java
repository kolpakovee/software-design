package ru.kolpakovee;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.Objects;
import java.util.Scanner;

public class QuizGame {
    private static final String API_URL = "http://jservice.io/api/random?count=1";

    private final OkHttpClient client;
    private final Gson gson;
    private final Scanner scanner;
    private int correctAnswers;
    private int incorrectAnswers;

    public QuizGame() {
        client = new OkHttpClient();
        gson = new Gson();
        correctAnswers = 0;
        incorrectAnswers = 0;
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        showWelcomeText();

        while (true) {
            waitForEnter();

            Question question = fetchQuestion();

            if (question != null) {
                System.out.println("Вопрос: " + question.question());
                System.out.print("Ответ: ");

                String userAnswer = getUserAnswer();

                if (userAnswer.equalsIgnoreCase("q")) {
                    break;
                }

                if (checkAnswer(question, userAnswer)) {
                    System.out.println("Верно!");
                    correctAnswers++;
                } else {
                    System.out.println("Неверно! Правильный ответ: " + question.answer());
                    incorrectAnswers++;
                }
            } else {
                System.out.println("Не удалось получить вопрос. Проверьте ваше интернет-соединение.");
            }
        }

        showGameStatistics();
    }

    private void waitForEnter() {
        System.out.println("Нажмите Enter, чтобы получить новый вопрос ...");
        scanner.nextLine();
    }

    private Question fetchQuestion() {
        Request request = new Request.Builder()
                .url(API_URL)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                String json = Objects.requireNonNull(response.body()).string();
                Question[] questions = gson.fromJson(json, Question[].class);
                if (questions.length != 0) {
                    return questions[0];
                }
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return null;
    }

//    private Category[] fetchCategory() {
//        Request request = new Request.Builder()
//                .url(CATEGORY_API_URL)
//                .build();
//
//        try (Response response = client.newCall(request).execute()) {
//            if (response.isSuccessful()) {
//                String json = Objects.requireNonNull(response.body()).string();
//                Category[] categories = gson.fromJson(json, Category[].class);
//                if (categories.length == 5) {
//                    return categories;
//                }
//            }
//        } catch (IOException e) {
//            throw new UncheckedIOException(e);
//        }
//
//        return null;
//    }

    private String getUserAnswer() {
        return scanner.nextLine();
    }

    private boolean checkAnswer(Question question, String userAnswer) {
        return userAnswer.equalsIgnoreCase(question.answer());
    }

    private void showGameStatistics() {
        System.out.println("Игра завершена.");
        System.out.println("Правильных ответов: " + correctAnswers);
        System.out.println("Неправильных ответов: " + incorrectAnswers);
    }

    private void showWelcomeText() {
        System.out.println("Добро пожаловать в игру-викторину!");
        System.out.println("Для ответа на вопросы введите ваш ответ, для выхода нажмите 'q'.");
    }
}
