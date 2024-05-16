package com.quizapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quizapp.entity.Question;
import com.quizapp.service.QuestionService;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
    @Autowired
	private QuestionService questionService;

    @PostMapping("/add")
	public Question saveQuestion(@RequestBody Question question ) {
		return this.questionService.saveQuestions(question);
	}
    
    @GetMapping("/allQuestions")
    public List<Question> getAllQuestions(){
		return questionService.getAllQuestions();
    	
    }
    
    @GetMapping("/category/{category}")
    public List<Question> getQuestionByCategory(@PathVariable String category){
    	return questionService.getQuestionByCategory(category);
    }
    
    
}
