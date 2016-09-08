package com.exubero.jbehave.specification;

import com.gargoylesoftware.htmlunit.StringWebResponse;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HTMLParser;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.jbehave.core.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

public class HtmlPageWrapper implements AutoCloseable {
    private final WebClient webClient;
    private final HtmlPage htmlPage;

    public HtmlPageWrapper(File htmlFile) throws IOException {
        this.webClient = buildWebClient();
        this.htmlPage = buildHtmlPage(this.webClient, htmlFile);
    }

    public HtmlPage getHtmlPage() {
        return htmlPage;
    }

    private WebClient buildWebClient() {
        WebClient client = new WebClient();
        client.getOptions().setJavaScriptEnabled(false);
        client.getOptions().setCssEnabled(false);
        return client;
    }

    private HtmlPage buildHtmlPage(WebClient webClient, File specificationFile) throws IOException {
        try(BufferedInputStream is = new BufferedInputStream(new FileInputStream(specificationFile))) {
            String html = IOUtils.toString(is, Charset.forName("UTF-8"), false);
            return parse(webClient, html);
        }
    }

    private HtmlPage parse(WebClient webClient, String html) throws IOException {
        URL dummyBaseUrl = new URL("file:///.");
        StringWebResponse webResponse = new StringWebResponse(html, dummyBaseUrl);
        return HTMLParser.parseHtml(webResponse, webClient.getCurrentWindow());
    }

    @Override
    public void close() {
        webClient.close();
    }
}
