package com.microservice.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservice.entity.Question;
import com.microservice.model.QuestionModel;
import com.microservice.model.QuestionResponse;
import com.microservice.model.Response;
import com.microservice.repository.QuestionRepo;

@Service
public class QuestionService {

    @Autowired
    QuestionRepo repo;

    @Autowired
    ModelMapper mapper;

    public List<QuestionModel> fetchAll() {
        List<Question> question = repo.findAll();
        Type listType = new TypeToken<List<QuestionModel>>() {}.getType();
        return mapper.map(question, listType);
    }

    public QuestionModel save(QuestionModel model) {
       Question question = mapper.map(model, Question.class);
       Question data = repo.save(question);
       return mapper.map(data, QuestionModel.class);
    }

    public List<QuestionModel> fetchByCategory(String category) {
        List<Question> question = repo.findByCategoryIgnoreCase(category);
        Type listType = new TypeToken<List<QuestionModel>>() {}.getType();
        return mapper.map(question, listType);
    }

    public ResponseEntity<List<Long>> generateQuestsForQuiz(String category, Integer noOfQuests) {
        List<Long> quests=repo.findRandomQuestionsByCategory(category,noOfQuests);
        return new ResponseEntity<>(quests,HttpStatus.OK);
    }

    /**
     * @param questionIds
     * @return
     */
    public ResponseEntity<List<QuestionResponse>> getQuestions(List<Long> questionIds) {
        List<Question> questions = new ArrayList<>();
        for(Long id: questionIds){
            questions.add(repo.findById(id).get());
        }
        Type questionsResponse = new TypeToken<List<QuestionResponse>>() {}.getType();
        List<QuestionResponse> questionResponses = mapper.map(questions, questionsResponse);
        return new ResponseEntity<>(questionResponses,HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<Response> responses) {
        Integer right = 0;
        for(Response r: responses){
            Question question=repo.findById(r.getId()).get();
            if(r.getResponse().equalsIgnoreCase(question.getCorrectAnswer()))
                right++;
           }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }

}
