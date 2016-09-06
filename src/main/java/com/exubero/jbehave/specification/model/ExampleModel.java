package com.exubero.jbehave.specification.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ExampleModel {
    private final ExampleResult exampleResult;
    private final String stepsId = UUID.randomUUID().toString();

    public ExampleModel(ExampleResult exampleResult) {
        this.exampleResult = exampleResult;
    }

    public List<String> getValues() {
        return exampleResult.getValues();
    }

    public ResultModel getSummaryResult() {
        return new ResultModel(exampleResult.getSummaryResult());
    }

    public String getStepsId() {
        return stepsId;
    }

    public List<StepModel> getSteps() {
        return exampleResult.getSteps().stream().map(StepModel::new).collect(Collectors.toList());
    }
}
