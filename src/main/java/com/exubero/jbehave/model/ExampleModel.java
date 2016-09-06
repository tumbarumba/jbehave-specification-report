package com.exubero.jbehave.model;

import java.util.List;

public class ExampleModel {
    private final ExampleResult exampleResult;

    public ExampleModel(ExampleResult exampleResult) {
        this.exampleResult = exampleResult;
    }

    public List<String> getValues() {
        return exampleResult.getValues();
    }

    public ResultModel getSummaryResult() {
        return new ResultModel(exampleResult.getSummaryResult());
    }
}
