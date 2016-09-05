package com.exubero.jbehave.model;

import com.exubero.jbehave.StoryResultSet;

import java.util.List;
import java.util.stream.Collectors;

public final class ScenarioModel {
    private final StoryResultSet.ScenarioResult scenarioResult;

    public ScenarioModel(StoryResultSet.ScenarioResult scenarioResult) {
        this.scenarioResult = scenarioResult;
    }

    public String title() {
        return scenarioResult.getScenario().getTitle();
    }

    public List<StepModel> steps() {
        return scenarioResult.getStepResults().stream().map(StepModel::new).collect(Collectors.toList());
    }
}
