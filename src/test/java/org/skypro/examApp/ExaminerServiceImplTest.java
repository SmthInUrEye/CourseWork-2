package org.skypro.examApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examApp.domain.BadRequestException;
import org.skypro.examApp.domain.Question;
import org.skypro.examApp.service.ExaminerServiceImpl;
import org.skypro.examApp.service.QuestionService;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest{

	@Mock
	private QuestionService questionService;

	@InjectMocks
	private ExaminerServiceImpl examinerService;

	public static <T> T getRandomElement(List<T> list){
		Random random=new Random();
		// Получаем случайный индекс в пределах размера списка
		int index=random.nextInt(list.size());
		return list.get(index);
	}

	@BeforeEach
	public void setUp(){
		examinerService=new ExaminerServiceImpl(questionService);
	}

	@Test
	public void givenExistTestSet_whenRequireAmountIsGreaterThanAvailable_thenThrowsBadRequest(){
		Question firstQuestion=new Question(UUID.randomUUID(),"Вопрос №1","Ответ №1");
		Question secondQuestion=new Question(UUID.randomUUID(),"Вопрос №2","Ответ №2");
		Collection<Question> availableQuestions=List.of(firstQuestion,secondQuestion);
		when(questionService.getAll()).thenReturn(availableQuestions);

		BadRequestException thrown=assertThrows(BadRequestException.class,()->examinerService.getQuestions(5));

		assertEquals("Запрашиваемое количество вопросов отсутствует",thrown.getMessage(),"Неправильное сообщение об ошибке");
	}

	@Test
	public void givenExistTestSet_whenRequireCorrectAmount_thenReturnCorrectQuestionSet(){
		Question firstQuestion=new Question(UUID.randomUUID(),"Вопрос №1","Ответ №1");
		Question secondQuestion=new Question(UUID.randomUUID(),"Вопрос №2","Ответ №2");
		Collection<Question> availableQuestions=List.of(firstQuestion,secondQuestion);
		when(questionService.getRandomQuestion()).thenReturn(firstQuestion).thenReturn(secondQuestion);
		when(questionService.getAll()).thenReturn(availableQuestions);
		Collection<Question> examQuestion=examinerService.getQuestions(2);

		assertThat(examQuestion.stream()
				.map(Question::getId))
				.hasSize(2)
				.contains(firstQuestion.getId())
				.contains(secondQuestion.getId());
	}
}
