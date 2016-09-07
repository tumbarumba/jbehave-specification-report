package com.exubero.jbehave.specification.model;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class ExampleModel {
    private final ExampleResult exampleResult;
    private final String stepsId = UUID.randomUUID().toString();

    public ExampleModel(ExampleResult exampleResult) {
        this.exampleResult = exampleResult;
    }

    @SuppressWarnings("unused") // used in template
    public List<String> getValues() {
        return exampleResult.getValues();
    }

    @SuppressWarnings("unused") // used in template
    public ResultModel getSummaryResult() {
        return new ResultModel(exampleResult.getSummaryResult());
    }

    @SuppressWarnings("unused") // used in template
    public String getStepsId() {
        return stepsId;
    }

    @SuppressWarnings("unused") // used in template
    public List<StepModel> getSteps() {
        return exampleResult.getSteps().stream().map(StepModel::new).collect(Collectors.toList());
    }
}
