package com.quizapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quizapp.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);
	
//	@Query(value="Select * from question q where q.category=:category ORDER BY RANDOM() LIMIT :numQ" ,nativeQuery=true)
//
//	List<Question> findRandomQuestionByCategory(String category, int numQ);
	
	@Query(value = "SELECT * FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	List<Question> findRandomQuestionByCategory(@Param("category") String category, @Param("numQ") int numQ);


}
