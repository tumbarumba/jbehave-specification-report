package com.exubero.jbehave.model;

import org.jbehave.core.model.ExamplesTable;

import java.util.ArrayList;
import java.util.List;

public class ExamplesTableResult {
    private final ExamplesTable table;
    private final List<ExampleResult> exampleResults = new ArrayList<>();

    public ExamplesTableResult(ExamplesTable table) {
        this.table = table;
    }

    public void addExampleResult(ExampleResult exampleResult) {
        exampleResults.add(exampleResult);
    }

    public List<String> getHeaders() {
        return table.getHeaders();
    }

    public List<ExampleResult> getExampleResults() {
        return exampleResults;
    }
}
