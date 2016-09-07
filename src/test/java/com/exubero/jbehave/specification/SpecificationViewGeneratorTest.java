package com.exubero.jbehave.specification;

import org.jbehave.core.embedder.Embedder;
import org.jbehave.core.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.exubero.jbehave.specification.JBehaveSpecificationBuilder.aSpecificationBuilderWithSteps;

public class SpecificationViewGeneratorTest {
    private static String specificationHtml;

    @BeforeClass
    static void buildSpecificationHtml() throws Throwable {
        StorySteps storySteps = new StorySteps();
        try {
            aSpecificationBuilderWithSteps(storySteps)
                    .withSpecificationTitle("Example Specifications")
                    .run();
            
            specificationHtml = IOUtils.toString()
        } catch (Embedder.RunningStoriesFailed) {
            // This is not a problem - the example stories have deliberate errors
        }

    }

    @Test(expected = Embedder.RunningStoriesFailed.class)
    public void verifyStoriesAndWriteSpecification() throws Throwable {
    }
}
