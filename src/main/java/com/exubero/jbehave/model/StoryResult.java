package com.exubero.jbehave.model;

import org.jbehave.core.model.Story;

import java.util.ArrayList;
import java.util.List;

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
}
