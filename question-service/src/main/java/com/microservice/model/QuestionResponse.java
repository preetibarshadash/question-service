package com.microservice.model;

import lombok.Data;

@Data
public class QuestionResponse {

    private Long id;

    private String question;

    private String option1;

    private String option2;

    private String option3;

    private String option4;
}
