package com.exubero.jbehave.specification.model;

import java.util.List;
import java.util.stream.Collectors;

import static com.exubero.jbehave.specification.model.Result.SUCCESSFUL;

public final class ScenarioModel {
    private final ScenarioResult scenarioResult;
    private final Result summaryResult;

    public ScenarioModel(ScenarioResult scenarioResult) {
        this.scenarioResult = scenarioResult;
        this.summaryResult = reduceChildResults(scenarioResult);
    }

    private Result reduceChildResults(ScenarioResult scenarioResult) {
        if (scenarioResult.hasExamples()) {
            return reduceExampleResults(scenarioResult.getExamplesTableResult());
        }
        return reduceStepResults(scenarioResult.getStepResults());
    }

    private Result reduceExampleResults(ExamplesTableResult examplesTableResult) {
        return examplesTableResult.getExampleResults().stream()
                .map(ExampleResult::getSummaryResult)
                .reduce(SUCCESSFUL, (a, n) -> n.getPriority() > a.getPriority() ? n : a);
    }

    private Result reduceStepResults(List<StepResult> stepResults) {
        return stepResults.stream()
                .map(StepResult::getResult)
                .reduce(SUCCESSFUL, (a, n) -> n.getPriority() > a.getPriority() ? n : a);
    }

    public String title() {
        return scenarioResult.getScenario().getTitle();
    }

    public ResultModel summaryResult() {
        return new ResultModel(summaryResult);
    }

    public List<StepModel> steps() {
        return scenarioResult.getStepResults().stream().map(StepModel::new).collect(Collectors.toList());
    }

    public boolean hasExamples() {
        return scenarioResult.hasExamples();
    }

    public ExamplesTableModel examplesTable() {
        return new ExamplesTableModel(scenarioResult.getExamplesTableResult());
    }
}
