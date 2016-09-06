package com.exubero.jbehave;

import com.exubero.jbehave.model.ExampleResult;
import com.exubero.jbehave.model.ExamplesTableResult;
import com.exubero.jbehave.model.StoryResultSet;
import com.exubero.jbehave.model.Result;
import com.exubero.jbehave.model.ScenarioResult;
import com.exubero.jbehave.model.StepResult;
import com.exubero.jbehave.model.StoryResult;
import org.jbehave.core.model.ExamplesTable;
import org.jbehave.core.model.GivenStories;
import org.jbehave.core.model.Lifecycle;
import org.jbehave.core.model.Meta;
import org.jbehave.core.model.Narrative;
import org.jbehave.core.model.OutcomesTable;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryDuration;
import org.jbehave.core.reporters.StoryReporter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class SpecificationStoryReporter implements StoryReporter {
    private final StoryResultSet storyResultSet;

    private StoryResult currentStory = null;
    private ScenarioResult currentScenario = null;
    private ExamplesTableResult currentExamplesTable = null;
    private ExampleResult currentExample = null;

    public SpecificationStoryReporter(StoryResultSet storyResultSet) {
        this.storyResultSet = storyResultSet;
    }

    @Override
    public void storyNotAllowed(Story story, String filter) {
    }

    @Override
    public void storyCancelled(Story story, StoryDuration storyDuration) {
    }

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        currentStory = new StoryResult(story);
    }

    @Override
    public void afterStory(boolean givenOrRestartingStory) {
        storyResultSet.addResult(currentStory);
        currentStory = null;
    }

    @Override
    public void narrative(Narrative narrative) {
    }

    @Override
    public void lifecyle(Lifecycle lifecycle) {
    }

    @Override
    public void scenarioNotAllowed(Scenario scenario, String filter) {
    }

    @Override
    public void beforeScenario(String scenarioTitle) {
        Optional<Scenario> maybeScenario = currentStory.getStory().getScenarios().stream().filter(scenario -> scenarioTitle.equals(scenario.getTitle())).findFirst();
        currentScenario = maybeScenario.map(ScenarioResult::new).orElse(null);
    }

    @Override
    public void scenarioMeta(Meta meta) {
    }

    @Override
    public void afterScenario() {
        currentStory.addScenarioResult(currentScenario);
        currentScenario = null;
    }

    @Override
    public void givenStories(GivenStories givenStories) {
    }

    @Override
    public void givenStories(List<String> storyPaths) {
    }

    @Override
    public void beforeExamples(List<String> steps, ExamplesTable table) {
        currentExamplesTable = new ExamplesTableResult(table);
        steps.forEach(step -> {
            currentScenario.addStepResult(new StepResult(step, Result.IGNORABLE));
        });
    }

    @Override
    public void example(Map<String, String> tableRow) {
        currentExample = new ExampleResult(currentExamplesTable.getHeaders(), tableRow);
        currentExamplesTable.addExampleResult(currentExample);
    }

    @Override
    public void afterExamples() {
        currentScenario.addExamplesTableResult(currentExamplesTable);
        currentExamplesTable = null;
        currentExample = null;
    }

    @Override
    public void beforeStep(String step) {
    }

    @Override
    public void successful(String step) {
        saveStepResult(new StepResult(step, Result.SUCCESSFUL));
    }

    @Override
    public void ignorable(String step) {
        saveStepResult(new StepResult(step, Result.IGNORABLE));
    }

    @Override
    public void pending(String step) {
        saveStepResult(new StepResult(step, Result.PENDING));
    }

    @Override
    public void notPerformed(String step) {
        saveStepResult(new StepResult(step, Result.NOT_PERFORMED));
    }

    @Override
    public void failed(String step, Throwable cause) {
        saveStepResult(new StepResult(step, Result.FAILED));
    }

    @Override
    public void failedOutcomes(String step, OutcomesTable table) {
    }

    @Override
    public void restarted(String step, Throwable cause) {
    }

    @Override
    public void restartedStory(Story story, Throwable cause) {
    }

    private void saveStepResult(StepResult stepResult) {
        if (currentExample == null) {
            currentScenario.addStepResult(stepResult);
        } else {
            currentExample.addStepResult(stepResult);
        }
    }

    @Override
    public void dryRun() {
    }

    @Override
    public void pendingMethods(List<String> methods) {
    }
}
