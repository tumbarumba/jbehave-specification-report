package com.exubero.jbehave.specification;

import org.junit.Test;

import static com.exubero.jbehave.specification.JBehaveSpecificationBuilder.aSpecificationBuilderWithSteps;

public class BuildSpecification {
    private final StorySteps storySteps = new StorySteps();

    @Test
    public void verifyStoriesAndWriteSpecification() throws Throwable {
        aSpecificationBuilderWithSteps(storySteps)
                .withSpecificationTitle("Example Specifications")
//                .withStoryPath("A_High_Level_Feature.story")
//                .withStoryPath("First_Category/Amazing_Capability.story")
                .run();
    }
}
