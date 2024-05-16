package com.quizapplication.quizservice.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapplication.quizservice.entity.Question;
import com.quizapplication.quizservice.entity.QuestionWrapper;
import com.quizapplication.quizservice.entity.Quiz;
import com.quizapplication.quizservice.entity.QuizDto;
import com.quizapplication.quizservice.entity.Response;
import com.quizapplication.quizservice.feign.QuizInterface;
import com.quizapplication.quizservice.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	QuizInterface quizInterface;

//	@Autowired
//   private  QuizDto quizDto;

//	@Autowired
//	private QuestionRepository questionRepository;

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizRepository.save(quiz);

		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

		Quiz quiz = quizRepository.findById(id).get();

		List<Integer> questionIds = quiz.getQuestionIds();
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromId(questionIds);

		return questions;
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}

}
