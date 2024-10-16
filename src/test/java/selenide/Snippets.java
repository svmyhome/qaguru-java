package selenide;
import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.Selenide.open;

public class Snippets {
    // https://www.youtube.com/@AlexeiVinogradovIT

    void browser_command_examples(){
        open("https://googe.com");
        open("/customer/orders");
        open("/", AuthenticationType.BASIC, new BasicAuthCredentials("user", "password"));


        Selenide.back();
        Selenide.refresh();

        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        executeJavaScript("sessionStorage.clear();");

        Selenide.confirm(); // OK in alert dialogs
        Selenide.dismiss(); // Cancel in alert dialogs

        Selenide.closeWindow(); // Close active tab
        Selenide.closeWebDriver(); //Close browser completely

        Selenide.switchTo().frame("new");
        Selenide.switchTo().defaultContent(); // return from frame back to the main DOM

        Selenide.switchTo().window("The Internet");

        var cookie=new Cookie("bar", "foo");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);

    }

    void selector_examples() {

        $("div").click();
        $("div", 2).click(); //the third div

        $x("//h1/div").click();
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click();
        $(withText("ull text")).click();

        $(byTagAndText("div", "full text"));
        $(withTagAndText("div", "ull text"));

        $("").parent();
        $("").sibling(1);
        $("").preceding(1);
        $("").closest("div");
        $("").ancestor("div");  // the same as closest
        $("div:last-child");

        $("div").$("h1").find(byText("abc")).click();
        // very optional
        $(byAttribute("abc", "x")).click();
        $("[abc=x]").click();

        $(byId("mytext")).click();
        $("#mytext").click();

        $(byClassName("red")).click();
        $(".red").click();



    }

    void actions_examples() {
        $("").click();
        $("").doubleClick();
        $("").contextClick();

        $("").hover();

        $("").setValue("text");
        $("").append("text"); // added text
        $("").clear();
        $("").setValue(""); // clear

        $("div").sendKeys("c"); //hotkey c on element
        actions().sendKeys("c").perform(); //hotkey c on whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "c")).perform(); // CTR + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "c")); // CTR + F

        $("").pressEnter();
        $("").pressTab();
        $("").pressEscape();

        //complex actions with keyboard and mouse, example

        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        //old html actions don't work  with many modern frameworks
        $("").selectOption("dropdown_options");
        $("").selectRadio("radio_options");

    }

    void assertions_examples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);

        //longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30));
    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$"));

        $("").shouldHave((cssClass("red")));
        $("").shouldHave((cssValue("font-size", "12"))); //also for example color

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked); //for checkboxes

        //Warning! Only checks if it is in DOM, not if is visible.
        $("").should(exist);

        //Warning! Checks only the "disabled" attribute!
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);

    }

    void collections_examples() {
        $$("div"); //does nothing!
        $$x("//div"); //by XPath

        $$("div").first().click();
        //$("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); //the second! Start with 0
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("abc")).click(); // find first

        //assertions
        $$("").shouldHave(size(0));
        $$("").shouldBe(CollectionCondition.empty); //the same

        $$("").shouldHave(texts("Alfa", "beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "beta", "Gamma"));

        $$("").shouldHave(textsInAnyOrder("Alfa", "beta", "Gamma"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Alfa", "beta", "Gamma"));

        $$("").shouldHave(itemWithText("Gamma")); // only one text

        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(3));
    }

    void file_operation_examples() throws FileNotFoundException {
        File file1 = $("a.filelink").download(); //only for a href link
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more

        File file =  new File("src/test/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt"); // from /resources
        // don't forget to submit
        $("uploadButton").click();

    }

    void java_script_examples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])","abc", 12);
        long fortytwo= executeJavaScript("return arguments[0]*arguments[1];", 6, 7);
    }

}
