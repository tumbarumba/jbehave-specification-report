package com.exubero.jbehave.model;

import org.jbehave.core.model.Scenario;

import java.util.ArrayList;
import java.util.List;

public final class ScenarioResult {
    private final Scenario scenario;
    private final List<StepResult> stepResults = new ArrayList<>();

    public ScenarioResult(Scenario scenario) {
        this.scenario = scenario;
    }

    public void addStepResult(StepResult stepResult) {
        stepResults.add(stepResult);
    }

    public Scenario getScenario() {
        return scenario;
    }

    public List<StepResult> getStepResults() {
        return stepResults;
    }
}
