import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class SelenideTests  extends BaseTestSelenide{

    private final List<String> offices = List.of("Edinburgh", "London", "New York", "San Francisco", "Tokyo");
    private final List<String> positions = List.of("Developer", "Manager", "Designer", "QA Engineer", "DevOps", "Data Scientist");

    private final Random random = new Random();

    @Test
    void checkPositionColumnExists() {
        $x("//span[@class='dt-column-title' and text()='Position']")
                .shouldBe(text("Position"));
    }

    @Test
    public void fillFormMultipleTimesWithClick() {
        $("select[name='example_length']").selectOption("50");
        // Получаем все строки из tbody
        List<String> rows = $$("tbody tr").filter(Condition.visible).texts(); // Для всех строк таблицы

        // Итерация по всем строкам
        for (int i = 0; i < Math.min(50, rows.size()); i++) {
            // Обрабатываем текущую строку через индексацию
            String rowSelector = "tbody tr:nth-of-type(" + (i + 1) + ")";

            // Селекторы для ячеек внутри строки
            String ageSelector = rowSelector + " input[id$='-age']";
            String positionSelector = rowSelector + " input[id$='-position']";
            String officeSelector = rowSelector + " select[id$='-office']";

            // Генерируем случайные данные
            int age = random.nextInt(33) + 18; // Возраст от 18 до 50
            String position = positions.get(random.nextInt(positions.size()));
            String office = offices.get(random.nextInt(offices.size()));

            // Заполняем данные
            $(ageSelector).setValue(String.valueOf(age)); // Поле возраста
            $(positionSelector).setValue(position); // Поле позиции

            // Работаем с выпадающим списком
            $(officeSelector).click(); // Открываем выпадающий список
            $$(officeSelector + " option").find(Condition.text(office)).click(); // Кликаем по нужному значению

            // Проверяем, что значение выбрано
            $(officeSelector).shouldHave(Condition.value(office));
        }
}
}
