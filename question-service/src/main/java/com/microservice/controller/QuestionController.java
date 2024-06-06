package com.microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.model.QuestionModel;
import com.microservice.model.QuestionResponse;
import com.microservice.model.Response;
import com.microservice.service.QuestionService;



@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    QuestionService service;

    @GetMapping("/fetchAll")
    public ResponseEntity<List<QuestionModel>> fetchAll() {
        List<QuestionModel> response = service.fetchAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<QuestionModel> add(@RequestBody QuestionModel model) {
    	System.out.println("in controller");
        QuestionModel response = service.save(model);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/fetchByCategory")
    public ResponseEntity<?> getMethodName(@RequestParam String category) {
        List<QuestionModel> response = service.fetchByCategory(category);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/generateQuest")
    public ResponseEntity<List<Long>> generateQuestsForQuiz(@RequestParam String category,@RequestParam Integer noOfQuests){
        return service.generateQuestsForQuiz(category,noOfQuests);
    }
    
    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionResponse>> getQuestions(@RequestBody List<Long> questionIds) {
        return service.getQuestions(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses) {
        return service.getScore(responses);
    }
    
    
    
}
