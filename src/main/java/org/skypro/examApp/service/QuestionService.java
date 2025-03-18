package org.skypro.examApp.service;

import org.skypro.examApp.domain.Question;

import java.util.Collection;

public interface QuestionService {

    void add(String question, String answer) ;

    void remove (Question question);

    Collection<Question> find (String query);

    Collection<Question> getAll();

    Question getRandomQuestion();


}
