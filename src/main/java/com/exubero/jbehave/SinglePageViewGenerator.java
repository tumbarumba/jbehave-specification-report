package com.exubero.jbehave;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jbehave.core.model.StoryMaps;
import org.jbehave.core.reporters.ReportsCount;
import org.jbehave.core.reporters.ViewGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

public class SinglePageViewGenerator implements ViewGenerator {
    @Override
    public void generateMapsView(File outputDirectory, StoryMaps storyMaps, Properties viewResources) {

    }

    @Override
    public void generateReportsView(File outputDirectory, List<String> formats, Properties viewResources) {
        File[] xmlFiles = outputDirectory.listFiles((dir, name) -> name.endsWith(".xml"));
        Arrays.stream(xmlFiles).forEach(xmlFile -> System.out.println(xmlFile.getAbsolutePath()));

        ReportModel reportModel = new ReportModel(asList(xmlFiles));

        File reportFile = new File(outputDirectory, "report.html");
        try(Writer writer = new FileWriter(reportFile)) {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile("report.mustache");
            mustache.execute(writer, reportModel);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report " + reportFile.getAbsolutePath(), e);
        }

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

    public static final class ReportModel {
        private final List<File> xmlStoryFiles;

        public ReportModel(List<File> xmlStoryFiles) {
            this.xmlStoryFiles = xmlStoryFiles;
        }

        public Iterable<StoryModel> stories() {
            List<StoryModel> storyModels = xmlStoryFiles.stream()
                    .map(StoryModel::fromXml)
                    .collect(Collectors.toList());

            return storyModels;
        }

    }


    public static final class StoryModel {
        private final File xmlFile;

        public StoryModel(File xmlFile) {
            this.xmlFile = xmlFile;
        }

        public static StoryModel fromXml(File xmlFile) {
            return new StoryModel(xmlFile);
        }

        public String file() throws IOException {
            return xmlFile.getCanonicalPath();
        }
    }

}
