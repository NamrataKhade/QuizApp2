package com.quizapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quizapp.entity.Question;
import com.quizapp.entity.QuestionWrapper;
import com.quizapp.entity.Quiz;
import com.quizapp.entity.Reponse;
import com.quizapp.repository.QuestionRepository;
import com.quizapp.repository.QuizRepository;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

		List<Question> questions = questionRepository.findRandomQuestionByCategory(category, numQ);
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestion(questions);
		quizRepository.save(quiz);
		return new ResponseEntity<>("Success", HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {

		Optional<Quiz> quiz = quizRepository.findById(id);
		List<Question> questionsFromDB = quiz.get().getQuestion();

		List<QuestionWrapper> questionsForUser = new ArrayList<>();

		for (Question q : questionsFromDB) {
			QuestionWrapper qw = new QuestionWrapper(q.getQuestionId(), q.getQuestionTitle(), q.getOption1(),
					q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUser.add(qw);
		}
		return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Integer> calculateResult(Integer id, List<Reponse> responses) {
		Quiz quiz = quizRepository.findById(id).get();
        List<Question> questions = quiz.getQuestion();
        
        int right=0;
        int i=0;
        for(Reponse response:responses) {
        	if(response.getResponse().equals(questions.get(i).getRightAnswer()))
        		right++;
        	i++;
        }
		return new ResponseEntity<>(right,HttpStatus.OK);
	}

}
