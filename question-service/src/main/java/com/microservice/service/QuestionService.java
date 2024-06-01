package com.microservice.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.entity.Question;
import com.microservice.model.QuestionModel;
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

}
