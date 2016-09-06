package com.exubero.jbehave.specification.model;

import java.util.List;
import java.util.stream.Collectors;

import static com.exubero.jbehave.specification.model.Result.SUCCESSFUL;

public final class ScenarioModel {
    private final ScenarioResult scenarioResult;

    public ScenarioModel(ScenarioResult scenarioResult) {
        this.scenarioResult = scenarioResult;
    }

    public String title() {
        return scenarioResult.getScenario().getTitle();
    }

    public ResultModel summaryResult() {
        return new ResultModel(scenarioResult.getSummaryResult());
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
