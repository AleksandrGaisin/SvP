import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestPlaywright {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;

    @BeforeEach
    public void setUp() {
        Playwright playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions()
                .setHeadless(false)
        );
        context = browser.newContext(new Browser.NewContextOptions()
                .setViewportSize(1920, 1080));
        page = context.newPage();
        page.navigate("https://datatables.net/examples/api/form.html");
        page.setDefaultTimeout(5000);
    }

    @AfterEach
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }

        // Закрываем Playwright
        if (playwright != null) {
            playwright.close();
        }
    }
}
