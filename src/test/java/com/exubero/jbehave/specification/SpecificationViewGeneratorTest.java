package com.exubero.jbehave.specification;

import org.jbehave.core.embedder.Embedder;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static com.exubero.jbehave.specification.JBehaveSpecificationBuilder.aSpecificationBuilderWithSteps;
import static com.exubero.jbehave.specification.SpecificationPage.specificationPageFrom;
import static org.junit.Assert.assertTrue;

public class SpecificationViewGeneratorTest {
    private static File specificationFile;

    @BeforeClass
    public static void buildSpecificationHtml() throws Throwable {
        StorySteps storySteps = new StorySteps();
        try {
            aSpecificationBuilderWithSteps(storySteps)
                    .withSpecificationTitle("Example Specifications")
                    .run();
        } catch (Embedder.RunningStoriesFailed ex) {
            // This is not a problem - the example stories have deliberate errors
        }
        specificationFile = new File("build/reports/jbehave/specification.html");
        assertTrue(specificationFile.exists());
    }

    @Test
    public void htmlContainsSummaryStatistics() throws IOException {
        try(SpecificationPage specificationPage = specificationPageFrom(specificationFile)) {
            specificationPage.assertStatisticsExists();
            specificationPage.assertStatisticsStoryCount(7);
            specificationPage.assertStatisticsScenarioCount(17);
            specificationPage.assertStatisticsScenarioFailedCount(2);
        }
    }
}
