import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class NewTest {

    @Test
    void shouldTest() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
        form.$("[data-test-id='name'] input").setValue("Иван Иванов");
        form.$("[data-test-id='phone'] input").setValue("+79999990909");
        form.$(".checkbox").click();
        form.$("button").click();
        $("[data-test-id]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void shouldNameErrorMessage() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
        form.$("[data-test-id='name'] input").setValue("Ivan Ivanov");
        form.$("[data-test-id='phone'] input").setValue("+79999990909");
        form.$(".checkbox").click();
        form.$("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldNameErrorMessageWithNoTextName() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
        form.$("[data-test-id='name'] input").setValue("");
        form.$("[data-test-id='phone'] input").setValue("+79999990909");
        form.$(".checkbox").click();
        form.$("button").click();
        $("[data-test-id='name'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldPhoneErrorMessage() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
        form.$("[data-test-id='name'] input").setValue("Иван Иванов");
        form.$("[data-test-id='phone'] input").setValue("79999990909");
        form.$(".checkbox").click();
        form.$("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }
    @Test
    void shouldPhoneErrorMessageWithNoTextPhone() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
        form.$("[data-test-id='name'] input").setValue("Иван Иванов");
        form.$("[data-test-id='phone'] input").setValue("");
        form.$(".checkbox").click();
        form.$("button").click();
        $("[data-test-id='phone'].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));
    }
    @Test
    void shouldCheckboxErrorMessage() {
        open("http://localhost:9999/");
        SelenideElement form = $("[action]");
        form.$("[data-test-id='name'] input").setValue("Иван Иванов");
        form.$("[data-test-id='phone'] input").setValue("+79999990909");
        form.$("button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }

}