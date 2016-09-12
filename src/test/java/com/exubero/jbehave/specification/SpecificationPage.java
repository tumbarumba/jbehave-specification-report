package com.exubero.jbehave.specification;

import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import org.junit.Assert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
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
        int actualCount = getActualStatisticsCountFrom("story-count");
        assertEquals(expectedCount, actualCount);
    }

    public void assertStatisticsScenarioCount(int expectedCount) {
        int actualCount = getActualStatisticsCountFrom("scenario-count");
        assertEquals(expectedCount, actualCount);
    }

    public void assertStatisticsScenarioFailedCount(int expectedCount) {
        int actualCount = getActualStatisticsCountFrom("scenario-failed-count");
        assertEquals(expectedCount, actualCount);
    }

    private int getActualStatisticsCountFrom(String spanClass) {
        HtmlSpan span = findStatisticsSpanWithClass(spanClass);
        return Integer.parseInt(span.asText());
    }

    private HtmlSpan findStatisticsSpanWithClass(String spanClass) {
        String xpathExpr = "//div[contains(@id, 'summary-statistics')]/p/span[contains(@class, '" + spanClass + "')]";
        List<?> matchingSpans = htmlPage.getByXPath(xpathExpr);
        if (matchingSpans.size() == 0) {
            Assert.fail("Unable to find statistics span with class '" + spanClass + "'");
        }
        return (HtmlSpan) matchingSpans.get(0);
    }

    @Override
    public void close() {
        htmlPageWrapper.close();
    }
}
