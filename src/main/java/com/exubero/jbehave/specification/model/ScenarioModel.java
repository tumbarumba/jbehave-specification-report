package com.exubero.jbehave.specification.model;

import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public final class ScenarioModel {
    private final ScenarioResult scenarioResult;

    public ScenarioModel(ScenarioResult scenarioResult) {
        this.scenarioResult = scenarioResult;
    }

    @SuppressWarnings("unused") // used in template
    public String title() {
        return scenarioResult.getScenario().getTitle();
    }

    @SuppressWarnings("unused") // used in template
    public ResultModel summaryResult() {
        return new ResultModel(scenarioResult.getSummaryResult());
    }

    @SuppressWarnings("unused") // used in template
    public List<StepModel> steps() {
        return scenarioResult.getStepResults().stream().map(StepModel::new).collect(Collectors.toList());
    }

    @SuppressWarnings("unused") // used in template
    public boolean hasExamples() {
        return scenarioResult.hasExamples();
    }

    @SuppressWarnings("unused") // used in template
    public ExamplesTableModel examplesTable() {
        return new ExamplesTableModel(scenarioResult.getExamplesTableResult());
    }
}
