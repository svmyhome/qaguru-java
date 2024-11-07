package com.lenta;

import com.codeborne.selenide.Configuration;
import com.lenta.pages.MainPage;
import com.lenta.pages.ProductSearchPage;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1200";
        Configuration.baseUrl = "https://lenta.com/";
//        Configuration.pageLoadStrategy = "eager";
//        Configuration.holdBrowserOpen = true;
//        Configuration.timeout = 10000;
    }


    MainPage mainPage = new MainPage();
    ProductSearchPage search = new ProductSearchPage();

}
