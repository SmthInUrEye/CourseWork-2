package org.skypro.examApp.service;

import org.skypro.examApp.controller.JavaQuestionController;
import org.skypro.examApp.domain.BadRequestException;
import org.skypro.examApp.domain.Question;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService{
	private QuestionService questionService;

	public ExaminerServiceImpl(QuestionService questionService){
		this.questionService=questionService;
	}

	@Override
	public Collection<Question> getQuestions(int amount){
		//HashSet гарантирует уникальность элементов
		Set<Question> examQuestions=new HashSet<>();
		if(amount>questionService.getAll().size()){
			throw new BadRequestException("Запрашиваемое количество вопросов отсутствует",400);
		}
		while(examQuestions.size()<amount){
			Question question=questionService.getRandomQuestion();
			examQuestions.add(question);
		}
		return examQuestions;
	}
}
