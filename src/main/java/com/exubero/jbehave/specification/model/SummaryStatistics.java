package com.exubero.jbehave.specification.model;

import org.jbehave.core.reporters.ReportsCount;

public class SummaryStatistics {
    private int stories = 0;
    private int storiesWithScenarios = 0;
    private int storiesNotAllowed = 0;
    private int storiesPending = 0;
    private int scenarios = 0;
    private int scenariosFailed = 0;
    private int scenariosNotAllowed = 0;
    private int scenariosPending = 0;
    private int steps = 0;
    private int stepsFailed = 0;

    public ReportsCount getReportsCount() {
        return new ReportsCount(stories, storiesNotAllowed, storiesPending, scenarios, scenariosFailed,
                scenariosNotAllowed, scenariosPending, stepsFailed);
    }

    public Result getResult() {
        if (scenariosFailed > 0) {
            return Result.FAILED;
        }
        if (scenariosPending > 0) {
            return Result.PENDING;
        }
        return Result.SUCCESSFUL;
    }

    public void add(SummaryStatistics child) {
        stories = stories + child.stories;
        storiesWithScenarios = storiesWithScenarios + child.storiesWithScenarios;
        storiesNotAllowed = storiesNotAllowed + child.storiesNotAllowed;
        storiesPending = storiesPending + child.storiesPending;
        scenarios = scenarios + child.scenarios;
        scenariosFailed = scenariosFailed + child.scenariosFailed;
        scenariosNotAllowed = scenariosNotAllowed + child.scenariosNotAllowed;
        scenariosPending = scenariosPending + child.scenariosPending;
        stepsFailed = stepsFailed + child.stepsFailed;
    }

    public void setStoryCount(int storyCount) {
        this.stories = storyCount;
    }

    public void setStoriesWithScenariosCount(int storiesWithScenariosCount) {
        this.storiesWithScenarios = storiesWithScenariosCount;
    }

    public void setPendingStoriesCount(int pendingStoriesCount) {
        this.storiesPending = pendingStoriesCount;
    }

    public void setScenarioCount(int scenarioCount) {
        this.scenarios = scenarioCount;
    }

    public void setFailedScenarioCount(int failedScenarioCount) {
        this.scenariosFailed = failedScenarioCount;
    }

    public void setPendingScenarioCount(int pendingScenarioCount) {
        this.scenariosPending = pendingScenarioCount;
    }

    public void incrementStepCount() {
        this.steps++;
    }

    public void incrementStepFailedCount() {
        this.stepsFailed++;
    }

    public int getStoriesWithScenariosCount() {
        return storiesWithScenarios;
    }

    public int getScenarioCount() {
        return scenarios;
    }

    public int getScenariosFailedCount() {
        return scenariosFailed;
    }
}
