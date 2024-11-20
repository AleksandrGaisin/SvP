import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlaywrightTests extends BaseTestPlaywright {

    private final List<String> positions = List.of("Developer", "Manager", "Designer", "QA Engineer", "DevOps", "Data Scientist");
    private final List<String> offices = List.of("Edinburgh", "London", "New York", "San Francisco", "Tokyo");
    private final Random random = new Random();

    @Test
    void testExample() {
        page.locator("h1").isVisible(); // Пример: проверяем, что заголовок видим

        // Дополнительные действия на странице
        System.out.println("Test executed successfully!");
    }

    @Test
    void fillFormMultipleTimesWithClick() {
        Locator selectDropdown = page.locator("select[name='example_length']");
        selectDropdown.selectOption("50");

        Locator rows = page.locator("tbody tr");
        assertTrue(rows.count() >= 20, "Expected at least 20 rows to fill");

        for (int i = 0; i < Math.min(50, rows.count()); i++) {
            Locator row = rows.nth(i);
            Locator ageField = row.locator("input[id$='-age']");
            Locator positionField = row.locator("input[id$='-position']");
            Locator officeDropdown = row.locator("select[id$='-office']");

            int age = random.nextInt(33) + 18; // Возраст от 18 до 50
            String position = positions.get(random.nextInt(positions.size()));
            String office = offices.get(random.nextInt(offices.size()));

            ageField.fill(String.valueOf(age));
            positionField.fill(position);

            officeDropdown.click(); // Открываем выпадающий список
            officeDropdown.selectOption(office);

            assertEquals(office, officeDropdown.inputValue(), "Expected office value to be selected");
        }
    }
}
