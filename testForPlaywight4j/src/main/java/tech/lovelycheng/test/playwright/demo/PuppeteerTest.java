package tech.lovelycheng.test.playwright.demo;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

/**
 * @author chengtong
 * @date 2022/4/8 16:04
 */
public class PuppeteerTest {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(50));
            Page page = browser.newPage();
            page.navigate("http://10.168.4.21:6070/signin");
            System.out.println(page.title());
        }
    }
}
