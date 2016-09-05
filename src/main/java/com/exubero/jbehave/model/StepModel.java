package com.exubero.jbehave.model;

import com.exubero.jbehave.StoryResultSet.StepResult;

public final class StepModel {
    private final StepResult stepResult;

    public StepModel(StepResult stepResult) {
        this.stepResult = stepResult;
    }

    public StepResult getStepResult() {
        return stepResult;
    }

    public String getStep() {
        return stepResult.getStep();
    }

    public ResultModel getResult() {
        return new ResultModel(stepResult.getResult());
    }
}
