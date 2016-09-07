package com.exubero.jbehave.specification.model;

@SuppressWarnings("WeakerAccess")
public final class StepModel {
    private final StepResult stepResult;

    public StepModel(StepResult stepResult) {
        this.stepResult = stepResult;
    }

    @SuppressWarnings("unused") // used in template
    public StepResult getStepResult() {
        return stepResult;
    }

    @SuppressWarnings("unused") // used in template
    public String getStep() {
        return stepResult.getStep()
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("｟", "<strong>")
                .replace("｠", "</strong>");
    }

    @SuppressWarnings("unused") // used in template
    public ResultModel getResult() {
        return new ResultModel(stepResult.getResult());
    }
}
