package com.exubero.jbehave.specification.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.exubero.jbehave.specification.model.Result.SUCCESSFUL;

public class ExampleResult {
    private final List<String> headerRow;
    private final Map<String, String> tableRow;
    private final List<StepResult> stepResults = new ArrayList<>();

    public ExampleResult(List<String> headerRow, Map<String, String> tableRow) {
        this.headerRow = headerRow;
        this.tableRow = tableRow;
    }

    public void addStepResult(StepResult stepResult) {
        stepResults.add(stepResult);
    }

    List<String> getValues() {
        return headerRow.stream().map(tableRow::get).collect(Collectors.toList());
    }

    Result getSummaryResult() {
        return stepResults.stream()
                .map(StepResult::getResult)
                .reduce(SUCCESSFUL, (a, n) -> n.getPriority() > a.getPriority() ? n : a);
    }

    List<StepResult> getSteps() {
        return stepResults;
    }
}
