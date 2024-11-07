package com.lenta.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.util.List;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ProductSearchPage {
    private final static SelenideElement searchTitle = $(".head h1");
    private final static ElementsCollection findGoods = $$(".lu-grid .lu-grid__item");
    private final static SelenideElement productTag = $(".product-categories-chips a");
    private final static ElementsCollection productTags = $$(".product-categories-chips a");

    public ProductSearchPage getTitle(String value) {
        searchTitle.shouldHave(text(value));
        return this;
    }

    public ProductSearchPage getNumberItems() {
        findGoods.shouldBe(sizeGreaterThan(0));
        return this;
    }

    public ProductSearchPage getTag(String value) {
        productTag.shouldHave(text(value));
        return this;
    }

    public ProductSearchPage getTags(List<String> value) {
        productTags.shouldHave(texts(value));
        return this;
    }
}
