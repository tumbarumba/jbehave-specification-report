package com.exubero.jbehave;

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

public class SpecificationStoryReporter implements StoryReporter {
    @Override
    public void storyNotAllowed(Story story, String filter) {
        System.out.println("SpecificationStoryReporter.storyNotAllowed(story = [" + story + "], filter = [" + filter + "])");
    }

    @Override
    public void storyCancelled(Story story, StoryDuration storyDuration) {
        System.out.println("SpecificationStoryReporter.storyCancelled(story = [" + story + "], storyDuration = [" + storyDuration + "])");
    }

    @Override
    public void beforeStory(Story story, boolean givenStory) {
        System.out.println("SpecificationStoryReporter.beforeStory(story = [" + story + "], givenStory = [" + givenStory + "])");
    }

    @Override
    public void afterStory(boolean givenOrRestartingStory) {
        System.out.println("SpecificationStoryReporter.afterStory(givenOrRestartingStory = [" + givenOrRestartingStory + "])");
    }

    @Override
    public void narrative(Narrative narrative) {
        System.out.println("SpecificationStoryReporter.narrative(narrative = [" + narrative + "])");
    }

    @Override
    public void lifecyle(Lifecycle lifecycle) {
        System.out.println("SpecificationStoryReporter.lifecyle(lifecycle = [" + lifecycle + "])");
    }

    @Override
    public void scenarioNotAllowed(Scenario scenario, String filter) {
        System.out.println("SpecificationStoryReporter.scenarioNotAllowed(scenario = [" + scenario + "], filter = [" + filter + "])");
    }

    @Override
    public void beforeScenario(String scenarioTitle) {
        System.out.println("SpecificationStoryReporter.beforeScenario(scenarioTitle = [" + scenarioTitle + "])");
    }

    @Override
    public void scenarioMeta(Meta meta) {
        System.out.println("SpecificationStoryReporter.scenarioMeta(meta = [" + meta + "])");
    }

    @Override
    public void afterScenario() {
        System.out.println("SpecificationStoryReporter.afterScenario()");
    }

    @Override
    public void givenStories(GivenStories givenStories) {
        System.out.println("SpecificationStoryReporter.givenStories(givenStories = [" + givenStories + "])");
    }

    @Override
    public void givenStories(List<String> storyPaths) {
        System.out.println("SpecificationStoryReporter.givenStories(storyPaths = [" + storyPaths + "])");
    }

    @Override
    public void beforeExamples(List<String> steps, ExamplesTable table) {
        System.out.println("SpecificationStoryReporter.beforeExamples(steps = [" + steps + "], table = [" + table + "])");
    }

    @Override
    public void example(Map<String, String> tableRow) {
        System.out.println("SpecificationStoryReporter.example(tableRow = [" + tableRow + "])");
    }

    @Override
    public void afterExamples() {
        System.out.println("SpecificationStoryReporter.afterExamples()");
    }

    @Override
    public void beforeStep(String step) {
        System.out.println("SpecificationStoryReporter.beforeStep(step = [" + step + "])");
    }

    @Override
    public void successful(String step) {
        System.out.println("SpecificationStoryReporter.successful(step = [" + step + "])");
    }

    @Override
    public void ignorable(String step) {
        System.out.println("SpecificationStoryReporter.ignorable(step = [" + step + "])");
    }

    @Override
    public void pending(String step) {
        System.out.println("SpecificationStoryReporter.pending(step = [" + step + "])");
    }

    @Override
    public void notPerformed(String step) {
        System.out.println("SpecificationStoryReporter.notPerformed(step = [" + step + "])");
    }

    @Override
    public void failed(String step, Throwable cause) {
        System.out.println("SpecificationStoryReporter.failed(step = [" + step + "], cause = [" + cause + "])");
    }

    @Override
    public void failedOutcomes(String step, OutcomesTable table) {
        System.out.println("SpecificationStoryReporter.failedOutcomes(step = [" + step + "], table = [" + table + "])");
    }

    @Override
    public void restarted(String step, Throwable cause) {
        System.out.println("SpecificationStoryReporter.restarted(step = [" + step + "], cause = [" + cause + "])");
    }

    @Override
    public void restartedStory(Story story, Throwable cause) {
        System.out.println("SpecificationStoryReporter.restartedStory(story = [" + story + "], cause = [" + cause + "])");
    }

    @Override
    public void dryRun() {
        System.out.println("SpecificationStoryReporter.dryRun()");
    }

    @Override
    public void pendingMethods(List<String> methods) {
        System.out.println("SpecificationStoryReporter.pendingMethods(methods = [" + methods + "])");
    }
}
