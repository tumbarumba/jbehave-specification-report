package com.exubero.jbehave.specification.model;

import org.jbehave.core.configuration.Keywords;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.exubero.jbehave.specification.model.TitleConverter.convertToTitle;

@SuppressWarnings("WeakerAccess")
public final class StoryModel {
    private final StoryResult storyResult;
    private final Keywords keywords;

    public StoryModel(StoryResult storyResult, Keywords keywords) {
        this.storyResult = storyResult;
        this.keywords = keywords;
    }

    @SuppressWarnings("unused") // used in template
    public String name() {
        return convertToTitle(storyResult.getStory().getName());
    }

    public boolean isTopLevel() {
        return !path().contains("/");
    }

    @SuppressWarnings("unused") // used in template
    public List<String> breadcrumbs() {
        String[] pathParts = path().split("/");
        return Arrays.stream(pathParts)
                .limit(pathParts.length - 1)
                .map(TitleConverter::convertToTitle)
                .collect(Collectors.toList());
    }

    public String path() {
        return storyResult.getStory().getPath();
    }

    public String group() {
        if (isTopLevel()) {
            return "";
        }
        String path = path();
        return path.substring(0, path.lastIndexOf("/"));
    }

    @SuppressWarnings("unused") // used in template
    public String pathId() {
        return path().replaceAll("/", "_").replace(".story", "");
    }

    @SuppressWarnings("unused") // used in template
    public String description() {
        return storyResult.getStory().getDescription().asString();
    }

    @SuppressWarnings("unused") // used in template
    public String narrative() {
        return storyResult.getStory().getNarrative().asString(keywords).replaceAll("\n", "<br>");
    }

    @SuppressWarnings("unused") // used in template
    public List<ScenarioModel> scenarios() {
        return storyResult.getScenarioResults().stream()
                .map(ScenarioModel::new)
                .collect(Collectors.toList());
    }

}
