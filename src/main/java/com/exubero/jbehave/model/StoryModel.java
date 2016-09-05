package com.exubero.jbehave.model;

import com.exubero.jbehave.StoryResultSet;
import org.jbehave.core.configuration.Keywords;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.exubero.jbehave.model.TitleConverter.convertToTitle;

public final class StoryModel {
    private final StoryResultSet.StoryResult storyResult;
    private final Keywords keywords;

    public StoryModel(StoryResultSet.StoryResult storyResult, Keywords keywords) {
        this.storyResult = storyResult;
        this.keywords = keywords;
    }

    public String name() {
        return convertToTitle(storyResult.getStory().getName());
    }

    public boolean isTopLevel() {
        return !path().contains("/");
    }

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

    public String pathId() {
        return path().replaceAll("/", "_").replace(".story", "");
    }

    public String description() {
        return storyResult.getStory().getDescription().asString();
    }

    public String narrative() {
        return storyResult.getStory().getNarrative().asString(keywords).replaceAll("\n", "<br>");
    }

    public List<ScenarioModel> scenarios() {
        return storyResult.getScenarioResults().stream()
                .map(ScenarioModel::new)
                .collect(Collectors.toList());
    }

}
