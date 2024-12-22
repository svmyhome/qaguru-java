package api.demowebshop;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;


public class DemoWebTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1000x1500";
        Configuration.baseUrl = "https://demowebshop.tricentis.com/";
    }

    @AfterEach
    public void afterEach() {
        Selenide.closeWebDriver();
    }

    public static final String EMAIL = "voldemarpetrov@mail.ru";
    public static final String PASSWORD = "123456";

    // First name: voldemar
// Last name: petrov
//Email: voldemarpetrov@mail.ru
// password 123456
    @Test
    public void AuthorizeFromWebDemoTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        step("Открываем главную страницу", () -> {
            open("/");
        });
        step("Открываем форму авторизации", () -> {
            $("[href='/login']").click();
        });
        step("Заполняем Email", () -> {
            $("#Email").setValue(EMAIL);
        });
        step("Заполняем пароль", () -> {
            $("#Password").setValue(PASSWORD);
        });

        step("Авторизуемся", () -> {
            $(".login-button").click();
        });

        step("Авторизация выполнена", () -> {
            $(".header-links .account").shouldHave(text("voldemarpetrov@mail.ru"));
        });
    }

    @Test
    public void AuthorizeFromApiDemoTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        String NOPCOMMERCE_AUTH = "NOPCOMMERCE.AUTH";
        step("Авторизоваться через API", () -> {
            String cookies_value = given()
                    .contentType("application/x-www-form-urlencoded")
                    .formParam("Email", "voldemarpetrov@mail.ru")
                    .formParam("Password", "123456")
                    .formParam("RememberMe", false)
                    .log().all()
                    .when().post("https://demowebshop.tricentis.com/login")
                    .then().log().all().statusCode(302).extract().cookie(NOPCOMMERCE_AUTH);
            open("/favicon.ico");
            Cookie authCookie = new Cookie(NOPCOMMERCE_AUTH, cookies_value);
            getWebDriver().manage().addCookie(authCookie);
        });

        step("Open main page", () ->
                open(""));

        step("Авторизация выполнена", () ->
                $(".header-links .account").shouldHave(text("voldemarpetrov@mail.ru")));
    }

    @Test
    public void OrderNotebookFromApiDemoTest() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        String NOPCOMMERCE_AUTH = "NOPCOMMERCE.AUTH";
        String data = "product_attribute_74_5_26=81&product_attribute_74_6_27=83&product_attribute_74_3_28=86&addtocart_74.EnteredQuantity=1";

        String cookies_value = step("Авторизоваться через API", () ->
                given()
                        .contentType("application/x-www-form-urlencoded")
                        .formParam("Email", "voldemarpetrov@mail.ru")
                        .formParam("Password", "123456")
                        .formParam("RememberMe", false)
                        .log().all()
                        .when().post("https://demowebshop.tricentis.com/login")
                        .then().log().all().statusCode(302).extract().cookie(NOPCOMMERCE_AUTH));

        // TODO не работает так как не понятно откуда взять removefromcart нужно чистить рукаим
        step("Очистить корзину", () -> {
            given()
                    .formParam("removefromcart", 4821191)
                    .formParam("itemquantity4821191", 1)
                    .formParam("updatecart", "Update shopping cart")
                    .formParam("discountcouponcode", "")
                    .formParam("giftcardcouponcode", "")
                    .formParam("CountryId", 0)
                    .formParam("StateProvinceId", 0)
                    .formParam("ZipPostalCode", "")
                    .cookie(NOPCOMMERCE_AUTH, cookies_value)
                    .log().all()
                    .when().post("https://demowebshop.tricentis.com/cart")
                    .then().log().all()
                    .statusCode(200);
        });

        step("Заказать компьютер", () -> {
            given()
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .body(data)
                    .cookie(NOPCOMMERCE_AUTH, cookies_value)
                    .log().all()
                    .when().post("https://demowebshop.tricentis.com/addproducttocart/details/74/1")
                    .then().log().all()
                    .statusCode(200)
                    .body("success", is(true))
                    .body("message", containsString("shopping cart"))
                    .body("updatetopcartsectionhtml", is("(1)"));
        });

        step("Open main page", () -> {
            open("/favicon.ico");
            Cookie authCookie = new Cookie(NOPCOMMERCE_AUTH, cookies_value);
            getWebDriver().manage().addCookie(authCookie);
            open("/cart");
        });

        step("В корзине есть заказ", () ->
                $(".qty-input").shouldHave(value("1")));


    }
}
