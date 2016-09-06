package com.exubero.jbehave.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.exubero.jbehave.model.Result.SUCCESSFUL;

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

    public List<String> getValues() {
        return headerRow.stream().map(header -> tableRow.get(header)).collect(Collectors.toList());
    }

    public Result getSummaryResult() {
        return stepResults.stream()
                .map(StepResult::getResult)
                .reduce(SUCCESSFUL, (a, n) -> n.getPriority() > a.getPriority() ? n : a);
    }
}
