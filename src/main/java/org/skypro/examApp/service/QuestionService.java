package org.skypro.examApp.service;

import org.skypro.examApp.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface QuestionService {

    void add(String question, String answer);

    void remove(Question question);

    Collection<Question> find(String query);

    Collection<Question> getAll();

    Question getRandomQuestion();

    Question getQuestion(String question, String answer);

}
