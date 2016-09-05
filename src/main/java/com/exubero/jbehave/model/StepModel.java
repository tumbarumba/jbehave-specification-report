package com.exubero.jbehave.model;

import com.exubero.jbehave.StoryResultSet;

public final class StepModel {
    private final StoryResultSet.StepResult stepResult;

    public StepModel(StoryResultSet.StepResult stepResult) {
        this.stepResult = stepResult;
    }

    public String getStep() {
        return stepResult.getStep();
    }

    public String getResult() {
        return stepResult.getResult().toString();
    }

    public String getResultClass() {
        switch (stepResult.getResult()) {
            case SUCCESSFUL:    return "success";
            case IGNORABLE:     return "default";
            case PENDING:       return "warning";
            case NOT_PERFORMED: return "info";
            case FAILED:        return "danger";
        }
        return "fire";
    }

    public String getResultIcon() {
        switch (stepResult.getResult()) {
            case SUCCESSFUL:    return "ok-circle";
            case IGNORABLE:     return "minus";
            case PENDING:       return "time";
            case NOT_PERFORMED: return "ban-circle";
            case FAILED:        return "remove-circle";
        }
        return "fire";
    }
}
