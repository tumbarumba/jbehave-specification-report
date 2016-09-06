package com.exubero.jbehave.specification.model;

import org.jbehave.core.model.Scenario;

import java.util.ArrayList;
import java.util.List;

public final class ScenarioResult {
    private final Scenario scenario;
    private final List<StepResult> stepResults = new ArrayList<>();
    private ExamplesTableResult examplesTableResult;

    public ScenarioResult(Scenario scenario) {
        this.scenario = scenario;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void addStepResult(StepResult stepResult) {
        stepResults.add(stepResult);
    }

    public List<StepResult> getStepResults() {
        return stepResults;
    }

    public void addExamplesTableResult(ExamplesTableResult examplesTableResult) {
        this.examplesTableResult = examplesTableResult;
    }

    public boolean hasExamples() {
        return examplesTableResult != null;
    }

    public ExamplesTableResult getExamplesTableResult() {
        return examplesTableResult;
    }
}
