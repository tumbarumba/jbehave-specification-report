package com.exubero.jbehave;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.thoughtworks.xstream.XStream;
import org.jbehave.core.model.Story;
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

public class SpecificationViewGenerator implements ViewGenerator {
    @Override
    public void generateMapsView(File outputDirectory, StoryMaps storyMaps, Properties viewResources) {
        System.out.println("generateMapsView");
    }

    @Override
    public void generateReportsView(File outputDirectory, List<String> formats, Properties viewResources) {
        File[] xmlFiles = outputDirectory.listFiles((dir, name) -> name.endsWith(".xml"));
        Arrays.stream(xmlFiles).forEach(xmlFile -> System.out.println(xmlFile.getAbsolutePath()));

        ReportModel reportModel = new ReportModel(asList(xmlFiles));

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
//        private final Story story;

        public StoryModel(File xmlFile) {
            this.xmlFile = xmlFile;
//            this.story = readStoryFrom(xmlFile);
        }

//        private Story readStoryFrom(File xmlFile) {
//            XStream xstream = new XStream();
//            xstream.alias("story", Story.class);
//
//            Story story = (Story)xstream.fromXML(xmlFile);
//            return story;
//        }
//
        public static StoryModel fromXml(File xmlFile) {
            return new StoryModel(xmlFile);
        }

        public String file() throws IOException {
            return xmlFile.getCanonicalPath();
        }

//        public String name() {
//            return story.getName();
//        }
    }

}
