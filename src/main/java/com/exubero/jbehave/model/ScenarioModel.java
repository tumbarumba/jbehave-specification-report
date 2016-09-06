package com.exubero.jbehave.model;

import java.util.List;
import java.util.stream.Collectors;

import static com.exubero.jbehave.model.Result.SUCCESSFUL;

public final class ScenarioModel {
    private final ScenarioResult scenarioResult;
    private final Result summaryResult;

    public ScenarioModel(ScenarioResult scenarioResult) {
        this.scenarioResult = scenarioResult;
        this.summaryResult = scenarioResult.getStepResults().stream()
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
}
