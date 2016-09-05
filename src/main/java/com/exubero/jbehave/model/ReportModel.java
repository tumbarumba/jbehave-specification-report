package com.exubero.jbehave.model;

import com.exubero.jbehave.StoryResultSet;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.model.StoryMaps;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class ReportModel {
    private static final StoryPathComparator BY_GROUP_THEN_PATH = new StoryPathComparator();

    private final StoryMaps storyMaps;
    private final StoryResultSet storyResultSet;
    private final Keywords keywords;

    public ReportModel(StoryMaps storyMaps, StoryResultSet storyResultSet, Keywords keywords) {
        this.storyMaps = storyMaps;
        this.storyResultSet = storyResultSet;
        this.keywords = keywords;
    }

    public List<StoryModel> stories() {
        return storyMaps.getMaps().stream()
                .flatMap(storyMap -> storyMap.getStories().stream())
                .map(storyResultSet::resultFor)
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

    static final class StoryPathComparator implements Comparator<StoryModel> {
        @Override
        public int compare(StoryModel a, StoryModel b) {
            int result = a.group().compareTo(b.group());
            return (result == 0) ? a.path().compareTo(b.path()) : result;
        }
    };
}
