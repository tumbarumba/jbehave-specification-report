package com.exubero.jbehave;

import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryResultSet {
    private final Map<String, StoryResult> pathToStoryResult = new HashMap<>();

    public void addResult(StoryResult currentStory) {
        pathToStoryResult.put(currentStory.getStory().getPath(), currentStory);
    }

    public StoryResult resultFor(Story story) {
        StoryResult storyResult = pathToStoryResult.get(story.getPath());
        if (storyResult == null) {
            storyResult = notPerformedResultFor(story);
        }
        return storyResult;
    }

    private StoryResult notPerformedResultFor(Story story) {
        StoryResult storyResult = new StoryResult(story);
        story.getScenarios().forEach(scenario -> {
            ScenarioResult scenarioResult = new ScenarioResult(scenario);
            scenario.getSteps().forEach(step -> {
                StepResult stepResult = new StepResult(step, Result.NOT_PERFORMED);
                scenarioResult.addStepResult(stepResult);
            });
            storyResult.addScenarioResult(scenarioResult);
        });
        return storyResult;
    }

    public Collection<StoryResult> storyResults() {
        return pathToStoryResult.values();
    }

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

    public static final class StoryResult {
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

    public static final class ScenarioResult {
        private final Scenario scenario;
        private final List<StepResult> stepResults = new ArrayList<>();

        public ScenarioResult(Scenario scenario) {
            this.scenario = scenario;
        }

        public void addStepResult(StepResult stepResult) {
            stepResults.add(stepResult);
        }

        public Scenario getScenario() {
            return scenario;
        }

        public List<StepResult> getStepResults() {
            return stepResults;
        }
    }

    public static final class StepResult {
        private final String step;
        private final Result result;

        public StepResult(String step, Result result) {
            this.step = step;
            this.result = result;
        }

        public String getStep() {
            return step;
        }

        public Result getResult() {
            return result;
        }
    }
}
