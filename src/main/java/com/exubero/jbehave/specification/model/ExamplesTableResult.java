package com.exubero.jbehave.specification.model;

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

    List<ExampleResult> getExampleResults() {
        return exampleResults;
    }
}
