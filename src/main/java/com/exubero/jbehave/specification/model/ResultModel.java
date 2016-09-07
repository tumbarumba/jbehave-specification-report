package com.exubero.jbehave.specification.model;

@SuppressWarnings("WeakerAccess")
public class ResultModel {
    private final Result result;

    @SuppressWarnings("unused") // used in template
    public ResultModel(Result result) {
        this.result = result;
    }

    @SuppressWarnings("unused") // used in template
    public String getName() {
        return result.name();
    }

    @SuppressWarnings("unused") // used in template
    public String getBootstrapClass() {
        switch (result) {
            case SUCCESSFUL:
                return "success";
            case IGNORABLE:
                return "default";
            case PENDING:
                return "warning";
            case NOT_PERFORMED:
                return "info";
            case FAILED:
                return "danger";
            default:
                return "danger";
        }
    }

    @SuppressWarnings("unused") // used in template
    public String getBootstrapIcon() {
        switch (result) {
            case SUCCESSFUL:
                return "ok-circle";
            case IGNORABLE:
                return "minus";
            case PENDING:
                return "time";
            case NOT_PERFORMED:
                return "ban-circle";
            case FAILED:
                return "remove-circle";
            default:
                return "fire";
        }
    }

}
