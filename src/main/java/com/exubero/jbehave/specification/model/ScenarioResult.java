package com.exubero.jbehave.specification.model;

import org.jbehave.core.model.Scenario;

import java.util.ArrayList;
import java.util.List;

import static com.exubero.jbehave.specification.model.Result.SUCCESSFUL;

public final class ScenarioResult {
    private final Scenario scenario;
    private final List<StepResult> stepResults = new ArrayList<>();
    private ExamplesTableResult examplesTableResult;

    public ScenarioResult(Scenario scenario) {
        this.scenario = scenario;
    }

    Result getSummaryResult() {
        if (hasExamples()) {
            return reduceExampleResults(getExamplesTableResult());
        }
        return reduceStepResults(getStepResults());
    }

    Scenario getScenario() {
        return scenario;
    }

    public void addStepResult(StepResult stepResult) {
        stepResults.add(stepResult);
    }

    List<StepResult> getStepResults() {
        return stepResults;
    }

    public void addExamplesTableResult(ExamplesTableResult examplesTableResult) {
        this.examplesTableResult = examplesTableResult;
    }

    boolean hasExamples() {
        return examplesTableResult != null;
    }

    ExamplesTableResult getExamplesTableResult() {
        return examplesTableResult;
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

}
