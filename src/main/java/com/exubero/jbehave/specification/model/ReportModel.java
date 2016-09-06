package com.exubero.jbehave.specification.model;

import org.jbehave.core.configuration.Keywords;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

public final class ReportModel {
    public final static String TITLE_PROP = "title";
    private final static StoryPathComparator BY_GROUP_THEN_PATH = new StoryPathComparator();

    private final StoryResultSet storyResultSet;
    private final Keywords keywords;
    private final Properties viewResources;

    public ReportModel(StoryResultSet storyResultSet, Keywords keywords, Properties viewResources) {
        this.storyResultSet = storyResultSet;
        this.keywords = keywords;
        this.viewResources = viewResources;
    }

    public String title() {
        return viewResources.getProperty(TITLE_PROP, "Specifications");
    }

    public List<StoryModel> stories() {
        return storyResultSet.storyResults().stream()
                .filter(storyResult -> storyResult.getScenarioResults().size() > 0)
                .map(storyResult -> new StoryModel(storyResult, keywords))
                .sorted(BY_GROUP_THEN_PATH)
                .collect(Collectors.toList());
    }

    public StoryGroup topLevelStoryGroup() {
        Map<String, List<StoryModel>> allGroups = stories().stream().collect(Collectors.groupingBy(StoryModel::group));
        StoryGroup topLevelGroup = new StoryGroup("");
        allGroups.keySet().stream().sorted().forEach( groupPath -> {
            topLevelGroup.addGroupStories(groupPath, allGroups.get(groupPath));
        });

        return topLevelGroup;
    }

    private static final class StoryPathComparator implements Comparator<StoryModel> {
        @Override
        public int compare(StoryModel a, StoryModel b) {
            int result = a.group().compareTo(b.group());
            return (result == 0) ? a.path().compareTo(b.path()) : result;
        }
    };
}
