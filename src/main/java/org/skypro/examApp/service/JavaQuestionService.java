package org.skypro.examApp.service;

import org.skypro.examApp.domain.Question;

import java.util.*;
import java.util.stream.Collectors;

public class JavaQuestionService implements QuestionService {

    Set<Question> questions;

    @Override
    public void add(String question, String answer) {
        boolean exist = questions.stream ().anyMatch ( q -> q.getQuestion ().equals ( question ) );
        if ( !exist ) {
            questions.add ( new Question ( UUID.randomUUID (), question, answer ) );
        } else {
            throw new IllegalArgumentException ( "Данный вопрос уже есть в списке" );
        }
    }

    @Override
    public void remove(Question question) {
        boolean exist = questions.stream ().anyMatch ( q -> q.getQuestion ().equals ( question.getQuestion () ) );
        if ( !exist ) {
            throw new IllegalArgumentException ( "Такого вопроса нет в списке" );
        } else {
            questions.remove ( question );
        }
    }

    @Override
    public Collection<Question> find(String query) {
        return questions
                .stream ()
                .filter ( q -> q.getQuestion ().toLowerCase ().contains ( query.toLowerCase () ) )
                .toList ();
    }

    @Override
    public Collection<Question> getAll() {
        return questions;
    }

    @Override
    public Question getRandomQuestion() {
        Random random = new Random ();
        List <Question> questionList = new ArrayList<> (questions);
        return questionList.get(random.nextInt (questions.size ()));
    }
}
