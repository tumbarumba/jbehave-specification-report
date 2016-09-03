package com.exubero.jbehave;

import org.jbehave.core.model.StoryMaps;
import org.jbehave.core.reporters.ReportsCount;
import org.jbehave.core.reporters.ViewGenerator;

import java.io.File;
import java.util.List;
import java.util.Properties;

public class SinglePageViewGenerator implements ViewGenerator {
    @Override
    public void generateMapsView(File outputDirectory, StoryMaps storyMaps, Properties viewResources) {

    }

    @Override
    public void generateReportsView(File outputDirectory, List<String> formats, Properties viewResources) {
        System.out.printf("SinglePageViewGenerator.generateReportsView");
    }

    @Override
    public ReportsCount getReportsCount() {
        int stories = 0;
        int storiesNotAllowed = 0;
        int storiesPending = 0;
        int scenarios = 0;
        int scenariosFailed = 0;
        int scenariosNotAllowed = 0;
        int scenariosPending = 0;
        int stepsFailed = 0;
        return new ReportsCount(stories, storiesNotAllowed, storiesPending, scenarios, scenariosFailed,
                scenariosNotAllowed, scenariosPending, stepsFailed);
    }

    @Override
    public Properties defaultViewProperties() {
        return new Properties();
    }
}
