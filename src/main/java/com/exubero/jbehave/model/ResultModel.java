package com.exubero.jbehave.model;

public class ResultModel {
    private final Result result;

    public ResultModel(Result result) {
        this.result = result;
    }

    public String getName() {
        return result.name();
    }

    public String getBootstrapClass() {
        switch (result) {
            case SUCCESSFUL:    return "success";
            case IGNORABLE:     return "default";
            case PENDING:       return "warning";
            case NOT_PERFORMED: return "info";
            case FAILED:        return "danger";
        }
        return "danger";
    }

    public String getBootstrapIcon() {
        switch (result) {
            case SUCCESSFUL:    return "ok-circle";
            case IGNORABLE:     return "minus";
            case PENDING:       return "time";
            case NOT_PERFORMED: return "ban-circle";
            case FAILED:        return "remove-circle";
        }
        return "fire";
    }

}
