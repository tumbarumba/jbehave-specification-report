package com.exubero.jbehave.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.exubero.jbehave.model.StringConverter.convertToTitle;

public final class StoryGroup {
    private final String path;
    private final List<StoryModel> stories;
    private final SortedMap<String, StoryGroup> childGroups;

    public StoryGroup(String path) {
        this.path = path;
        this.stories = new ArrayList<>();
        this.childGroups = new TreeMap<>();
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return convertToTitle(path);
    }

    public List<StoryModel> getStories() {
        return stories;
    }

    public List<StoryGroup> getChildGroups() {
        return new ArrayList<>(childGroups.values());
    }

    public void addGroupStories(String groupPath, List<StoryModel> storyModels) {
        if (groupPath.isEmpty()) {
            stories.addAll(storyModels);
            return;
        }

        int nextSlashIndex = groupPath.indexOf("/");
        if (nextSlashIndex >= 0) {
            String nextGroupName = groupPath.substring(0, nextSlashIndex);
            String remainingGroups = groupPath.substring(nextSlashIndex + 1, groupPath.length());
            StoryGroup nextGroup = childGroups.computeIfAbsent(nextGroupName, StoryGroup::new);
            nextGroup.addGroupStories(remainingGroups, storyModels);
            return;
        }

        StoryGroup group = childGroups.computeIfAbsent(groupPath, StoryGroup::new);
        group.addGroupStories("", storyModels);
    }

}
