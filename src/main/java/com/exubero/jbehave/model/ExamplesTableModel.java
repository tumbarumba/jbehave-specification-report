package com.exubero.jbehave.model;

import java.util.List;
import java.util.stream.Collectors;

public class ExamplesTableModel {
    private final ExamplesTableResult examplesTableResult;

    public ExamplesTableModel(ExamplesTableResult examplesTableResult) {
        this.examplesTableResult = examplesTableResult;
    }

    public List<String> getHeaders() {
        return examplesTableResult.getHeaders();
    }

    public List<ExampleModel> getRows() {
        return examplesTableResult.getExampleResults().stream().map(ExampleModel::new).collect(Collectors.toList());
    }
}
