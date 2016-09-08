package com.exubero.jbehave.specification;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

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

    @Override
    public void close() {
        htmlPageWrapper.close();
    }
}
