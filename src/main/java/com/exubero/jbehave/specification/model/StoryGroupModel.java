package com.exubero.jbehave.specification.model;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import static com.exubero.jbehave.specification.model.TitleConverter.convertToTitle;

@SuppressWarnings("WeakerAccess")
public final class StoryGroupModel {
    private final String path;
    private final List<StoryModel> stories;
    private final SortedMap<String, StoryGroupModel> childGroups;

    public StoryGroupModel(String path) {
        this.path = path;
        this.stories = new ArrayList<>();
        this.childGroups = new TreeMap<>();
    }

    @SuppressWarnings("unused") // used in template
    public String getPath() {
        return path;
    }

    @SuppressWarnings("unused") // used in template
    public String getTitle() {
        return convertToTitle(path);
    }

    @SuppressWarnings("unused") // used in template
    public List<StoryModel> getStories() {
        return stories;
    }

    @SuppressWarnings("unused") // used in template
    public List<StoryGroupModel> getChildGroups() {
        return new ArrayList<>(childGroups.values());
    }

    void addGroupStories(String groupPath, List<StoryModel> storyModels) {
        if (groupPath.isEmpty()) {
            stories.addAll(storyModels);
            return;
        }

        int nextSlashIndex = groupPath.indexOf("/");
        if (nextSlashIndex >= 0) {
            String nextGroupName = groupPath.substring(0, nextSlashIndex);
            String remainingGroups = groupPath.substring(nextSlashIndex + 1, groupPath.length());
            StoryGroupModel nextGroup = childGroups.computeIfAbsent(nextGroupName, StoryGroupModel::new);
            nextGroup.addGroupStories(remainingGroups, storyModels);
            return;
        }

        StoryGroupModel group = childGroups.computeIfAbsent(groupPath, StoryGroupModel::new);
        group.addGroupStories("", storyModels);
    }

}
