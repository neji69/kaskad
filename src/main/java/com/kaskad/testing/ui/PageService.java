package com.kaskad.testing.ui;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PageService {

    private final Playwright playwright;
    private final Browser browser;

    @Autowired
    public PageService(Playwright playwright, Browser browser) {
        this.playwright = playwright;
        this.browser = browser;
    }

    public Page createPage() {
        return browser.newPage();
    }

    public void closePage(Page page) {
        if (page != null) {
            page.close();
        }
    }
}