package com.exubero.jbehave.model;

import java.util.List;

public class ExampleModel {
    private final ExampleResult exampleResult;

    public ExampleModel(ExampleResult exampleResult) {
        this.exampleResult = exampleResult;
    }

    public List<String> getCells() {
        return exampleResult.getValues();
    }
}
