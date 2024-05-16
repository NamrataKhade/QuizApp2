package com.quizapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.entity.Question;
import com.quizapp.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService{
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question saveQuestions(Question question) {
		return questionRepository.save(question);
    
	}

	@Override
	public List<Question> getAllQuestions() {
		
		return questionRepository.findAll();
	}

	@Override
	public List<Question> getQuestionByCategory(String category) {
		
		return questionRepository.findByCategory(category);
	}

}
