package com.exubero.jbehave.specification;

import com.exubero.jbehave.specification.model.ReportModel;
import com.exubero.jbehave.specification.model.ResultModel;
import com.exubero.jbehave.specification.model.StoryResultSet;
import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ProvidedStepsFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.HTML;
import static org.jbehave.core.reporters.Format.HTML_TEMPLATE;
import static org.jbehave.core.reporters.Format.XML;

public final class JBehaveSpecificationBuilder extends ConfigurableEmbedder {
    private final URL codeLocation;
    private final List<String> customStoryPaths = new ArrayList<>();
    private final Object[] steps;
    private String specificationTitle;

    private JBehaveSpecificationBuilder(Object... steps) {
        Objects.requireNonNull(steps);
        Objects.requireNonNull(steps[0]);

        this.steps = steps;
        this.codeLocation = codeLocationFromClass(steps[0].getClass());
    }

    public static JBehaveSpecificationBuilder aSpecificationBuilderWithSteps(Object... steps) {
        return new JBehaveSpecificationBuilder(steps);
    }

    public JBehaveSpecificationBuilder withStoryPath(String storyPath) {
        this.customStoryPaths.add(storyPath);
        return this;
    }

    public JBehaveSpecificationBuilder withStoryPaths(List<String> storyPaths) {
        this.customStoryPaths.addAll(storyPaths);
        return this;
    }

    public JBehaveSpecificationBuilder withSpecificationTitle(String title) {
        this.specificationTitle = title;
        return this;
    }

    @Override
    public Configuration configuration() {
        if (super.hasConfiguration()) {
            return super.configuration();
        }


        MostUsefulConfiguration mostUsefulConfiguration = new MostUsefulConfiguration();

        StoryResultSet storyResultSet = new StoryResultSet();
        SpecificationStoryReporter storyReporter = new SpecificationStoryReporter(storyResultSet);
        SpecificationViewGenerator viewGenerator = new SpecificationViewGenerator(storyResultSet, mostUsefulConfiguration.keywords());
        Properties viewProperties = viewGenerator.defaultViewProperties();
        if (specificationTitle != null) {
            viewProperties.setProperty(ReportModel.TITLE_PROP, specificationTitle);
        }
        StoryReporterBuilder storyReporterBuilder = new StoryReporterBuilder()
                .withCodeLocation(codeLocation)
                .withRelativeDirectory("../reports/jbehave")
                .withDefaultFormats()
                .withFormats(CONSOLE, XML)
                .withReporters(storyReporter)
                .withFailureTrace(true)
                .withViewResources(viewProperties);
        return mostUsefulConfiguration
                .useStoryReporterBuilder(storyReporterBuilder)
                .useViewGenerator(viewGenerator);
    }

    private List<String> defaultStoryPaths() {
        return new StoryFinder().findPaths(codeLocation, "**/*.story", null);
    }

    private List<String> storyPaths() {
        return customStoryPaths.isEmpty() ? defaultStoryPaths() : customStoryPaths;
    }

    @Override
    public void run() throws Throwable {
        List<CandidateSteps> candidateSteps = new InstanceStepsFactory(configuration(), steps).createCandidateSteps();
        useStepsFactory(new ProvidedStepsFactory(candidateSteps));

        configuredEmbedder().runStoriesAsPaths(storyPaths());
    }

}
