package com.exubero.jbehave;

import org.junit.Test;

import static com.exubero.jbehave.JBehaveEmbedder.aJBehaveEmbedderWithSteps;

public class VerifyStories {
    private final StorySteps storySteps = new StorySteps();

    @Test
    public void verifyStories() throws Throwable {
        aJBehaveEmbedderWithSteps(storySteps).run();
    }
}
