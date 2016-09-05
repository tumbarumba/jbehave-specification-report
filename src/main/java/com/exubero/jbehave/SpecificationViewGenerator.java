package com.exubero.jbehave;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.model.Scenario;
import org.jbehave.core.model.Story;
import org.jbehave.core.model.StoryMaps;
import org.jbehave.core.reporters.ReportsCount;
import org.jbehave.core.reporters.ViewGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static com.exubero.jbehave.SpecificationViewGenerator.StoryPathComparator.BY_GROUP_THEN_PATH;

public class SpecificationViewGenerator implements ViewGenerator {
    private final Keywords keywords;

    public SpecificationViewGenerator(Keywords keywords) {
        this.keywords = keywords;
    }

    @Override
    public void generateMapsView(File outputDirectory, StoryMaps storyMaps, Properties viewResources) {
        ReportModel reportModel = new ReportModel(storyMaps);

        outputDirectory.mkdirs();
        File reportFile = new File(outputDirectory, "specification.html");
        try(Writer writer = new FileWriter(reportFile)) {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile("specification.mustache");
            mustache.execute(writer, reportModel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report " + reportFile.getAbsolutePath(), e);
        }
    }

    @Override
    public void generateReportsView(File outputDirectory, List<String> formats, Properties viewResources) {
        throw new UnsupportedOperationException();
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

    public final class ReportModel {
        private final StoryMaps storyMaps;

        public ReportModel(StoryMaps storyMaps) {
            this.storyMaps = storyMaps;
        }

        public List<StoryModel> stories() {
            return storyMaps.getMaps().stream()
                    .flatMap(storyMap -> storyMap.getStories().stream())
                    .map(StoryModel::new)
                    .sorted(BY_GROUP_THEN_PATH)
                    .collect(Collectors.toList());
        }

    }


    public final class StoryModel {
        private final Story story;

        public StoryModel(Story story) {
            this.story = story;
        }

        public String name() {
            return convertToTitle(story.getName());
        }

        public boolean isTopLevel() {
            return !path().contains("/");
        }

        public List<String> breadcrumbs() {
            String[] pathParts = path().split("/");
            return Arrays.stream(pathParts)
                    .limit(pathParts.length - 1)
                    .map(this::convertToTitle)
                    .collect(Collectors.toList());
        }

        public String path() {
            return story.getPath();
        }

        public String group() {
            if (isTopLevel()) {
                return "";
            }
            String path = path();
            return path.substring(0, path.lastIndexOf("/"));
        }

        public String pathId() {
            return path().replaceAll("/", "_").replace(".story", "");
        }

        public String description() {
            return story.getDescription().asString();
        }

        public String narrative() {
            return story.getNarrative().asString(keywords).replaceAll("\n", "<br>");
        }

        public List<ScenarioModel> scenarios() {
            return story.getScenarios().stream()
                    .map(ScenarioModel::new)
                    .collect(Collectors.toList());
        }

        private String convertToTitle(String pathComponent) {
            return pathComponent.replace("_", " ").replace(".story", "");
        }
    }

    public final class ScenarioModel {
        private final Scenario scenario;

        public ScenarioModel(Scenario scenario) {
            this.scenario = scenario;
        }

        public String title() {
            return scenario.getTitle();
        }

        public List<String> steps() {
            return scenario.getSteps();
        }
    }

    static final class StoryPathComparator implements Comparator<StoryModel> {
        public static final StoryPathComparator BY_GROUP_THEN_PATH = new StoryPathComparator();

        @Override
        public int compare(StoryModel a, StoryModel b) {
            int result = a.group().compareTo(b.group());
            return (result == 0) ? a.path().compareTo(b.path()) : result;
        }
    };

}
