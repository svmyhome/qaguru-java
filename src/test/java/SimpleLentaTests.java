import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

public class SimpleLentaTests {

    @Test
    void simpleTest() {

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("user-agent=Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/131.0.0.0 Safari/537.36");
//        Configuration.browser = "chrome";
//        Configuration.browserCapabilities = options;
        step("ОТкрыть браузер", () -> {
            open("https://lenta.com//");
        });
        step("ОТкрыта главная страница", () -> {
            $("[luautotestlocator='login-button']").shouldHave(text("Войти"));
        });
        System.out.println();
    }
}
