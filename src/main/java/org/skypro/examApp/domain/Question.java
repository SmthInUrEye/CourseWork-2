package org.skypro.examApp.domain;

import java.util.Objects;
import java.util.UUID;

public class Question {
    private final UUID id;
    private final String question;
    private final String answer;

    public Question(UUID id, String question, String answer) {
        checkQuestion(question);
        checkAnswer(answer);
        this.id = id;
        this.question = question;
        this.answer = answer;
    }

    public static void checkQuestion(String question) {
        if (question.isBlank())
            throw new IllegalArgumentException("Пустой вопрос");
    }

    public static void checkAnswer(String answer) {
        if (answer.isBlank())
            throw new IllegalArgumentException("Пустой ответ");
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(question + answer);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Question questAndAnswer = (Question) obj;
        return (Objects.equals(question, questAndAnswer.question) && Objects.equals(answer, questAndAnswer.answer));
    }
}
