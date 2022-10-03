package com.messimari.restaurantml.api.handler;

import lombok.Data;

import java.util.List;

@Data
public class ProblemWithField extends Problem {

    private List<FieldValidation> fieldValidation;

    public ProblemWithField(Problem problem) {
        super(problem);
    }
}
