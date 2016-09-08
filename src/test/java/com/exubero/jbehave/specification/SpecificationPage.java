package com.exubero.jbehave.specification;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;

public class SpecificationPage implements AutoCloseable {
    private final HtmlPageWrapper htmlPageWrapper;
    private final HtmlPage htmlPage;

    public SpecificationPage(HtmlPageWrapper htmlPageWrapper) {
        this.htmlPageWrapper = htmlPageWrapper;
        this.htmlPage = htmlPageWrapper.getHtmlPage();
    }

    public static SpecificationPage specificationPageFrom(File specificationFile) throws IOException {
        HtmlPageWrapper htmlPageWrapper = new HtmlPageWrapper(specificationFile);
        return new SpecificationPage(htmlPageWrapper);
    }

    public void assertStatisticsExists() {
        assertNotNull(htmlPage.getElementById("summary-statistics"));
    }

    public void assertStatisticsStoryCount(int expectedCount) {
        HtmlSpan storyCountSpan = (HtmlSpan) htmlPage.getByXPath("//div[contains(@id, 'summary-statistics')]/p/span[contains(@class, 'story-count')]").get(0);
        int actualCount = Integer.parseInt(storyCountSpan.asText());
        Assert.assertEquals(expectedCount, actualCount);
    }

    public void assertStatisticsScenarioCount(int expectedCount) {
    }

    public void assertStatisticsScenarioFailedCount(int expectedCount) {
    }

    @Override
    public void close() {
        htmlPageWrapper.close();
    }
}
