package com.quizapplication.quizservice.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapplication.quizservice.entity.QuestionWrapper;
import com.quizapplication.quizservice.entity.Response;



@Service
public interface QuizService {

	ResponseEntity<String> createQuiz(String category, int numQ, String title);

	ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id);

	ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses);

	

}
