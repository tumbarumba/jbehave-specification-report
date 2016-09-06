package com.exubero.jbehave.specification.model;

import org.jbehave.core.model.Story;

import java.util.ArrayList;
import java.util.List;

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

    public List<ScenarioResult> getScenarioResults() {
        return scenarioResults;
    }

    public Result getSummaryResult() {
        return scenarioResults.stream()
                .map(ScenarioResult::getSummaryResult)
                .reduce(SUCCESSFUL, (a, n) -> n.getPriority() > a.getPriority() ? n : a);
    }
}
