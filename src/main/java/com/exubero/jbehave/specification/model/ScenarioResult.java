package com.exubero.jbehave.specification.model;

import org.jbehave.core.model.Scenario;

import java.util.ArrayList;
import java.util.List;

import static com.exubero.jbehave.specification.model.Result.FAILED;
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

    public SummaryStatistics summaryStatistics() {
        SummaryStatistics scenarioStatistics = new SummaryStatistics();

        scenarioStatistics.setScenarioCount(1);
        switch (getSummaryResult()) {
            case FAILED:
                scenarioStatistics.setFailedScenarioCount(1);
                break;
            case PENDING:
                scenarioStatistics.setPendingScenarioCount(1);
                break;
            default:
                // do nothing
        }

        if (hasExamples()) {
            updateStepStatisticsWithExamples(scenarioStatistics);
        } else {
            updateStepStatisticsNoExamples(scenarioStatistics);
        }

        return scenarioStatistics;
    }

    private void updateStepStatisticsWithExamples(SummaryStatistics scenarioStatistics) {
        for(ExampleResult exampleResult : examplesTableResult.getExampleResults()) {
            for(StepResult stepResult : exampleResult.getSteps()) {
                updateStepStatistics(scenarioStatistics, stepResult);
            }
        }
    }

    private void updateStepStatisticsNoExamples(SummaryStatistics scenarioStatistics) {
        for (StepResult stepResult : stepResults) {
            updateStepStatistics(scenarioStatistics, stepResult);
        }
    }

    private void updateStepStatistics(SummaryStatistics scenarioStatistics, StepResult stepResult) {
        scenarioStatistics.incrementStepCount();
        if (FAILED.equals(stepResult.getResult())) {
            scenarioStatistics.incrementStepFailedCount();
        }
    }
}
