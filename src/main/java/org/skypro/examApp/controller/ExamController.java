package org.skypro.examApp.controller;

import org.skypro.examApp.domain.BadRequestException;
import org.skypro.examApp.domain.Question;
import org.skypro.examApp.service.ExaminerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ExamController{

	private final ExaminerService examinerService;

	@Autowired
	public ExamController(ExaminerService examinerService){
		this.examinerService=examinerService;
	}

	@GetMapping("/exam/getQuestions")
	public ResponseEntity<Collection<Question>> getQuestions(@RequestParam int amount){
		try{
			Collection<Question> questions=examinerService.getQuestions(amount);
			return ResponseEntity.ok(questions);
		}catch(BadRequestException ex){
			return ResponseEntity.badRequest().body(null);
		}
	}
}
