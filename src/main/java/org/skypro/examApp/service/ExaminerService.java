package org.skypro.examApp.service;

import org.skypro.examApp.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public interface ExaminerService{
	Collection<Question> getQuestions(int amount);
}
