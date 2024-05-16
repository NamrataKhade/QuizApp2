package com.quizapp.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.quizapp.entity.Question;

public interface QuestionService {

	Question saveQuestions(Question question);

	List<Question> getAllQuestions();

	List<Question> getQuestionByCategory(String category);

}
