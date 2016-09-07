package com.exubero.jbehave.specification;

import org.jbehave.core.embedder.Embedder;
import org.junit.Test;

import static com.exubero.jbehave.specification.JBehaveSpecificationBuilder.aSpecificationBuilderWithSteps;

public class BuildSpecification {
    private final StorySteps storySteps = new StorySteps();

    @Test(expected = Embedder.RunningStoriesFailed.class)
    public void verifyStoriesAndWriteSpecification() throws Throwable {
        aSpecificationBuilderWithSteps(storySteps)
                .withSpecificationTitle("Example Specifications")
                .run();
    }
}
