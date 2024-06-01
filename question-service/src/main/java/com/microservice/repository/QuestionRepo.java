package com.microservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.entity.Question;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {

    List<Question> findByCategoryIgnoreCase(String category);

}
