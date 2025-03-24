package org.skypro.examApp.controller;
import org.skypro.examApp.domain.Question;
import org.skypro.examApp.service.JavaQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Collection;

@RestController
public class JavaQuestionController{
private final JavaQuestionService javaQuestionService;

@Autowired
public JavaQuestionController(JavaQuestionService javaQuestionService){
	this.javaQuestionService=javaQuestionService;
}

@GetMapping("/exam/java")
public Collection<Question> getAllQuestions(){
	return javaQuestionService.getAll();
}

@GetMapping("/exam/java/find")
public Collection<Question> getSearch(@RequestParam String pattern){
	return javaQuestionService.find(pattern);
}

@GetMapping("/exam/java/add")
public ResponseEntity<String> addQuestion(
		@RequestParam("question") String questionText,
		@RequestParam("answer") String questionAnswer){
	javaQuestionService.add(questionText,questionAnswer);
	return ResponseEntity.ok("Вопрос добавлен");
}

@GetMapping("exam/java/remove")
public ResponseEntity<String> removeQuestion(
		@RequestParam("question") String questionText,
		@RequestParam("answer") String questionAnswer){
	javaQuestionService.remove(javaQuestionService.getQuestion(questionText,questionAnswer));
	return ResponseEntity.ok("Вопрос удалён");
}
}
