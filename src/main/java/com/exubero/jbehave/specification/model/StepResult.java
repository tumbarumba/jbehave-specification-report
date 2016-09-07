package com.exubero.jbehave.specification.model;

public final class StepResult {
    private final String step;
    private final Result result;

    public StepResult(String step, Result result) {
        this.step = step;
        this.result = result;
    }

    String getStep() {
        return step;
    }

    Result getResult() {
        return result;
    }
}
