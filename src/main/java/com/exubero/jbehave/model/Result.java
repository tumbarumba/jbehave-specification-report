package com.exubero.jbehave.model;

public enum Result {
    SUCCESSFUL(1),
    IGNORABLE(1),
    PENDING(2),
    NOT_PERFORMED(2),
    FAILED(3);

    private final int priority;

    Result(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
