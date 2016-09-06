package com.exubero.jbehave.specification;

import com.exubero.jbehave.specification.model.ReportModel;
import com.exubero.jbehave.specification.model.StoryResultSet;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.model.StoryMaps;
import org.jbehave.core.reporters.ReportsCount;
import org.jbehave.core.reporters.ViewGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Properties;

import static com.exubero.jbehave.specification.model.ReportModel.TITLE_PROP;

public class SpecificationViewGenerator implements ViewGenerator {

    private final StoryResultSet storyResultSet;
    private final Keywords keywords;

    public SpecificationViewGenerator(StoryResultSet storyResultSet, Keywords keywords) {
        this.storyResultSet = storyResultSet;
        this.keywords = keywords;
    }

    @Override
    public void generateMapsView(File outputDirectory, StoryMaps storyMaps, Properties viewResources) {
        throw new UnsupportedOperationException("generateMapsView");
    }

    @Override
    public void generateReportsView(File outputDirectory, List<String> formats, Properties viewResources) {
        ReportModel reportModel = new ReportModel(storyResultSet, keywords, viewResources);

        outputDirectory.mkdirs();
        File specificationFile = new File(outputDirectory, "specification.html");
        try(Writer writer = new FileWriter(specificationFile)) {
            MustacheFactory mustacheFactory = new DefaultMustacheFactory();
            Mustache mustache = mustacheFactory.compile("specification.mustache");
            mustache.execute(writer, reportModel);

            System.out.println("Specification written to " + specificationFile.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Failed to write report " + specificationFile.getAbsolutePath(), e);
        }
    }

    @Override
    public ReportsCount getReportsCount() {
        return storyResultSet.reportsCount();
    }

    @Override
    public Properties defaultViewProperties() {
        Properties properties = new Properties();
        properties.setProperty(TITLE_PROP, "Specifications");
        return properties;
    }


}
