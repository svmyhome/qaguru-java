package com.lenta.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class CatalogPanel {
    public static final SelenideElement mainCat = $("[automation-id=mainCat]");

    public void clickMainCat() {
        mainCat.click();
    }
}
