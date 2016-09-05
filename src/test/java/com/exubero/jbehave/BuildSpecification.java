package com.exubero.jbehave;

import org.junit.Test;

import static com.exubero.jbehave.JBehaveSpecificationBuilder.aSpecificationBuilderWithSteps;

public class BuildSpecification {
    private final StorySteps storySteps = new StorySteps();

    @Test
    public void verifyStoriesAndWriteSpecification() throws Throwable {
        aSpecificationBuilderWithSteps(storySteps).run();
    }
}
