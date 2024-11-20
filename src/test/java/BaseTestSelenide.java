import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class BaseTestSelenide {
    @BeforeEach
    public void setUp() {
        Configuration.browser = "chrome"; // Указываем браузер
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 10000; // Устанавливаем тайм-аут в миллисекундах

        Selenide.open("https://datatables.net/examples/api/form.html");
    }

    @AfterEach
    public void tearDown() {
        Selenide.closeWebDriver();
    }
}
