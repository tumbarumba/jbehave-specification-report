package com.exubero.jbehave.specification.model;

public final class StepModel {
    private final StepResult stepResult;

    public StepModel(StepResult stepResult) {
        this.stepResult = stepResult;
    }

    public StepResult getStepResult() {
        return stepResult;
    }

    public String getStep() {
        return stepResult.getStep()
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("｟", "<strong>")
                .replace("｠", "</strong>");
    }

    public ResultModel getResult() {
        return new ResultModel(stepResult.getResult());
    }
}
