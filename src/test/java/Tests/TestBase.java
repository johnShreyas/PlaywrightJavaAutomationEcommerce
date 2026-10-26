package Tests;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Paths;
import java.util.Properties;

public class TestBase {
    protected Page page;
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected String base_url;

    @BeforeMethod
    public void setUp() throws IOException {
        Properties prop = new Properties();
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            prop.load(fis);
        }
        String browserName = prop.getProperty("browser");
        base_url = prop.getProperty("qa.base_url");

        playwright = Playwright.create();

        if (browserName.equalsIgnoreCase("chrome")) {
            browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                    .setChannel("chrome")
                    .setHeadless(false));
        } else if (browserName.equalsIgnoreCase("firefox")) {
            browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else if (browserName.equalsIgnoreCase("safari") || browserName.equalsIgnoreCase("webkit")) {
            browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browserName);
        }

        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1280, 720)
                .setRecordVideoDir(Paths.get("target/videos/")));

        context.tracing().start(new Tracing.StartOptions()
                .setScreenshots(true)
                .setSnapshots(true)
                .setSources(true));

        page = context.newPage();
    }

    @AfterMethod
    public void tearDown(Method method) {
        if (context != null) {
            context.tracing().stop(new Tracing.StopOptions()
                    .setPath(Paths.get("target/" + method.getName() + "-trace.zip")));
        }

        if (page != null && page.video() != null) {
            page.video().saveAs(Paths.get("target/videos/" + method.getName() + ".webm"));
            page.video().delete();
        }

        if (context != null) {
            context.close();
        }
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
    }
}