package com.exubero.jbehave;

import org.jbehave.core.ConfigurableEmbedder;
import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.CandidateSteps;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.jbehave.core.steps.ProvidedStepsFactory;

import java.net.URL;
import java.util.List;
import java.util.Objects;

import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;
import static org.jbehave.core.reporters.Format.XML;

public final class JBehaveEmbedder extends ConfigurableEmbedder {
    private final URL codeLocation;

    private JBehaveEmbedder(Object... steps) {
        Objects.requireNonNull(steps);
        Objects.requireNonNull(steps[0]);

        this.codeLocation = codeLocationFromClass(steps[0].getClass());
        List<CandidateSteps> candidateSteps = new InstanceStepsFactory(configuration(), steps).createCandidateSteps();
        useStepsFactory(new ProvidedStepsFactory(candidateSteps));
    }

    public static JBehaveEmbedder aJBehaveEmbedderWithSteps(Object... steps) {
        return new JBehaveEmbedder(steps);
    }

    @Override
    public Configuration configuration() {
        if (super.hasConfiguration()) {
            return super.configuration();
        }

        MostUsefulConfiguration mostUsefulConfiguration = new MostUsefulConfiguration();
        return mostUsefulConfiguration
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(codeLocation)
                        .withRelativeDirectory("../reports/jbehave")
                        .withDefaultFormats()
                        .withFormats(CONSOLE, XML)
                        .withReporters(new SpecificationStoryReporter())
                        .withFailureTrace(true))
                .useViewGenerator(new SpecificationViewGenerator(mostUsefulConfiguration.keywords()));
    }

    private List<String> storyPaths() {
        return new StoryFinder().findPaths(codeLocation, "**/*.story", null);
    }

    @Override
    public void run() throws Throwable {
        List<String> storyPaths = storyPaths();
//        configuredEmbedder().runStoriesAsPaths(storyPaths);
        configuredEmbedder().mapStoriesAsPaths(storyPaths);
    }
}
