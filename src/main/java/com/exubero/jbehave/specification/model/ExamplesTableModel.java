package com.exubero.jbehave.specification.model;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public class ExamplesTableModel {
    private final ExamplesTableResult examplesTableResult;

    public ExamplesTableModel(ExamplesTableResult examplesTableResult) {
        this.examplesTableResult = examplesTableResult;
    }

    @SuppressWarnings("unused") // used in template
    public List<String> getHeaders() {
        return examplesTableResult.getHeaders();
    }

    @SuppressWarnings("unused") // used in template
    public List<ExampleModel> getExamples() {
        return examplesTableResult.getExampleResults().stream().map(ExampleModel::new).collect(Collectors.toList());
    }
}
