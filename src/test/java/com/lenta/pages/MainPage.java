package com.lenta.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class MainPage {

    public static final SelenideElement catalog = $(".header__top .catalog-button");
    public static final SelenideElement searchInput = $("#header-search-input");

    public MainPage openMainPage() {
        open("/");
        return this;
    }

    public MainPage openCatalog() {
        catalog.click();
        return this;
    }

    public MainPage openSearch() {
        searchInput.click();
        return this;
    }

    public MainPage fillItemSearchEnter(String value) {
        searchInput.setValue(value).pressEnter();
        return this;
    }

}
