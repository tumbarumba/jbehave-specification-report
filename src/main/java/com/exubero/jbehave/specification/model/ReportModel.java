package com.exubero.jbehave.specification.model;

import org.jbehave.core.configuration.Keywords;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

@SuppressWarnings("WeakerAccess")
public final class ReportModel {
    public static final String TITLE_PROP = "title";

    private static final StoryPathComparator BY_GROUP_THEN_PATH = new StoryPathComparator();

    private final StoryResultSet storyResultSet;
    private final Keywords keywords;
    private final Properties viewResources;

    public ReportModel(StoryResultSet storyResultSet, Keywords keywords, Properties viewResources) {
        this.storyResultSet = storyResultSet;
        this.keywords = keywords;
        this.viewResources = viewResources;
    }

    @SuppressWarnings("unused") // used in template
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

    @SuppressWarnings("unused") // used in template
    public StoryGroupModel topLevelStoryGroup() {
        Map<String, List<StoryModel>> allGroups = stories().stream().collect(Collectors.groupingBy(StoryModel::group));
        StoryGroupModel topLevelGroup = new StoryGroupModel("");
        allGroups.keySet().stream()
                .sorted()
                .forEach(groupPath ->  topLevelGroup.addGroupStories(groupPath, allGroups.get(groupPath)));

        return topLevelGroup;
    }

    private static final class StoryPathComparator implements Comparator<StoryModel> {
        @Override
        public int compare(StoryModel a, StoryModel b) {
            int result = a.group().compareTo(b.group());
            return (result == 0) ? a.path().compareTo(b.path()) : result;
        }
    }
}
