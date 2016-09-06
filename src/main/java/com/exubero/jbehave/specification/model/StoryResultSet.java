package com.exubero.jbehave.specification.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class StoryResultSet {
    private final Map<String, StoryResult> pathToStoryResult = new HashMap<>();

    public void addResult(StoryResult currentStory) {
        pathToStoryResult.put(currentStory.getStory().getPath(), currentStory);
    }

    public Collection<StoryResult> storyResults() {
        return pathToStoryResult.values();
    }
}
