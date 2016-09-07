package com.exubero.jbehave.specification.model;

import org.jbehave.core.reporters.ReportsCount;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static com.exubero.jbehave.specification.model.Result.FAILED;
import static com.exubero.jbehave.specification.model.Result.PENDING;

public class StoryResultSet {
    private final Map<String, StoryResult> pathToStoryResult = new HashMap<>();

    public void addResult(StoryResult currentStory) {
        pathToStoryResult.put(currentStory.getStory().getPath(), currentStory);
    }

    Collection<StoryResult> storyResults() {
        return pathToStoryResult.values();
    }

    public ReportsCount reportsCount() {
        int stories = 0;
        int storiesNotAllowed = 0;
        int storiesPending = 0;
        int scenarios = 0;
        int scenariosFailed = 0;
        int scenariosNotAllowed = 0;
        int scenariosPending = 0;
        int stepsFailed = 0;

        for(StoryResult storyResult : storyResults()) {
            stories++;
            if(PENDING.equals(storyResult.getSummaryResult())) {
                storiesPending++;
            }

            for(ScenarioResult scenarioResult : storyResult.getScenarioResults()) {
                scenarios++;
                switch (scenarioResult.getSummaryResult()) {
                    case FAILED:
                        scenariosFailed++;
                        break;
                    case PENDING:
                        scenariosPending++;
                        break;
                    default:
                        // do nothing
                }

                if (scenarioResult.hasExamples()) {
                    ExamplesTableResult examplesTableResult = scenarioResult.getExamplesTableResult();
                    for(ExampleResult exampleResult : examplesTableResult.getExampleResults()) {
                        for(StepResult stepResult : exampleResult.getSteps()) {
                            if (FAILED.equals(stepResult.getResult())) {
                                stepsFailed++;
                            }
                        }
                    }
                } else {
                    for(StepResult stepResult : scenarioResult.getStepResults()) {
                        if (FAILED.equals(stepResult.getResult())) {
                            stepsFailed++;
                        }
                    }
                }
            }
        }

        return new ReportsCount(stories, storiesNotAllowed, storiesPending, scenarios, scenariosFailed,
                scenariosNotAllowed, scenariosPending, stepsFailed);
    }
}
