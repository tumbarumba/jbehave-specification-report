package com.exubero.jbehave.specification.model;

import org.jbehave.core.model.Story;

import java.util.ArrayList;
import java.util.List;

import static com.exubero.jbehave.specification.model.Result.PENDING;
import static com.exubero.jbehave.specification.model.Result.SUCCESSFUL;

public final class StoryResult {
    private final Story story;
    private final List<ScenarioResult> scenarioResults = new ArrayList<>();

    public StoryResult(Story story) {
        this.story = story;
    }

    public Story getStory() {
        return story;
    }

    public void addScenarioResult(ScenarioResult scenarioResult) {
        scenarioResults.add(scenarioResult);
    }

    List<ScenarioResult> getScenarioResults() {
        return scenarioResults;
    }

    Result getSummaryResult() {
        return scenarioResults.stream()
                .map(ScenarioResult::getSummaryResult)
                .reduce(SUCCESSFUL, (a, n) -> n.getPriority() > a.getPriority() ? n : a);
    }

    public SummaryStatistics summaryStatistics() {
        SummaryStatistics storyStatistics = new SummaryStatistics();
        storyStatistics.setStoryCount(1);
        if (scenarioResults.size() > 0) {
            storyStatistics.setStoriesWithScenariosCount(1);
        }
        if (getSummaryResult().equals(PENDING)) {
            storyStatistics.setPendingStoriesCount(1);
        }

        for (ScenarioResult scenarioResult : scenarioResults) {
            storyStatistics.add(scenarioResult.summaryStatistics());
        }

        return storyStatistics;
    }
}
