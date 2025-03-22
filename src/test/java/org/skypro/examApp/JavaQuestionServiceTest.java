package org.skypro.examApp;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.examApp.domain.Question;
import org.skypro.examApp.service.JavaQuestionService;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class JavaQuestionServiceTest{
	private JavaQuestionService javaQuestionService;
	private Set<Question> mockQuestions;

	@BeforeEach
	public void setUp(){
		mockQuestions=new HashSet<>();
		javaQuestionService=new JavaQuestionService(mockQuestions);

		javaQuestionService.add("Почему стоит изучать Java?","Потому что это круто!");
		javaQuestionService.add("Что такое Spring?","Это фреймворк для Java");

	}

	@Test
	public void testAddQuestion(){
		javaQuestionService.add("Добавим третий вопрос?","Кажется у нас получилось!");

		assertEquals(3,mockQuestions.size(),"Вопрос НЕ добавлен");

		assertTrue(mockQuestions.stream().anyMatch(q->q.getQuestion().equals("Добавим третий вопрос?")),"Вопрос НЕ найден");
	}

	@Test
	public void testAddDuplicateQuestion(){
		String question="Почему стоит изучать Java?";
		String answer="Потому что это круто!";

		IllegalArgumentException thrown=assertThrows(IllegalArgumentException.class,()->javaQuestionService.add(question,answer));

		assertEquals("Данный вопрос уже есть в списке",thrown.getMessage(),"Сообщение об ошибке неверно");
	}

	@Test
	public void testRemoveQuestion(){
		Question removingQuestion=new Question(UUID.randomUUID(),"Можно ли тебя удалить?","Конечно да!");
		mockQuestions.add(removingQuestion);
		assertTrue(mockQuestions.contains(removingQuestion));

		javaQuestionService.remove(removingQuestion);
		assertFalse(mockQuestions.contains(removingQuestion),"Вопрос не удалился");
	}

	@Test
	public void testGetRandomQuestion(){
		Question randomQuestion=javaQuestionService.getRandomQuestion();

		assertNotNull(randomQuestion,"Вопрос не добавлен");
	}

	@Test
	public void testFindQuestion(){
		Question findingQuestion=new Question(UUID.randomUUID(),"Как же найти question","Попробуй через find question");
		mockQuestions.add(findingQuestion);

		assertTrue(javaQuestionService.find("question").contains(findingQuestion),"Оказалось, что не содержит");
	}

}
