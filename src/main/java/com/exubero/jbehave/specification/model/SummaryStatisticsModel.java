package com.exubero.jbehave.specification.model;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static com.exubero.jbehave.specification.model.Result.FAILED;

public class SummaryStatisticsModel {
    private final SummaryStatistics summaryStatistics;

    public SummaryStatisticsModel(SummaryStatistics summaryStatistics) {
        this.summaryStatistics = summaryStatistics;
    }

    public ResultModel result() {
        return new ResultModel(summaryStatistics.getResult());
    }

    public String heading() {
        if (FAILED.equals(summaryStatistics.getResult())) {
            return "Scenarios failed to pass automated checks.";
        }
        return "All scenarios has passed automated checks.";
    }

    public int storyCount() {
        return summaryStatistics.getStoriesWithScenariosCount();
    }

    public int scenarioCount() {
        return summaryStatistics.getScenarioCount();
    }

    public int scenarioFailedCount() {
        return summaryStatistics.getScenariosFailedCount();
    }

    public boolean isScenariosFailed() {
        return summaryStatistics.getScenariosFailedCount() > 0;
    }

    public String creationTime() {
        Clock clock = Clock.systemUTC();
        Instant now = clock.instant();
        ZonedDateTime nowUtc = now.atZone(ZoneId.of("UTC"));
        return DateTimeFormatter.RFC_1123_DATE_TIME.format(nowUtc);
    }
}
