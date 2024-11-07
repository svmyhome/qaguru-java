package com.lenta;

import com.lenta.test_data.AdultGoods;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static java.lang.Thread.sleep;

public class ProductSearchTests extends TestBase {


    @Tags({@Tag("SEARCH"), @Tag("SMOKE")})
    @DisplayName("Товар найден через строку поиска")
    @ParameterizedTest(name = "Товар {0} найден через строку поиска")
    @ValueSource(strings = {"молоко", "кефир", "сметана"})
    void searchResultShouldNotBeEmpty(String item) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems();
    }

    @Tag("BLOCKER")
    @DisplayName("Товар найден через строку поиска и тег существует")
    @ParameterizedTest(name = "Товар {0} найден через строку поиска и тег {1} существует")
    @CsvSource(value = {"молоко,  Молоко и сливки", "кефир, Кисломолочные продукты", "сметана, Сметана"})
    void searchResultShouldNotBeEmptyTagExist(String item, String tag) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems()
                .getTag(tag);
    }


    @Tag("BLOCKER")
    @DisplayName("Товар найден через строку поиска и тег существует")
    @ParameterizedTest(name = "Товар {0} найден через строку поиска и тег {1} существует")
    @CsvSource(value = {
            "молоко|  Молоко и сливки",
            "кефир| Кисломолочные продукты",
            "сметана| Сметана"},
            delimiter = '|')
    void searchResultShouldNotBeEmptyTagExistDelimeter(String item, String tag) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems()
                .getTag(tag);
    }

    @Tag("BLOCKER")
    @DisplayName("Товар найден через строку поиска и тег существует")
    @ParameterizedTest(name = "Товар {0} найден через строку поиска и тег {1} существует")
    @CsvFileSource(resources = "/test_data/searchResultShouldNotBeEmptyCSVFile.csv")
    void searchResultShouldNotBeEmptyTagExistCSVFile(String item, String tag) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems()
                .getTag(tag);
    }


    @Tags({@Tag("SEARCH"), @Tag("ADULT")})
    @DisplayName("Товар для взрослых найден через строку поиска")
    @ParameterizedTest(name = "Товар для взрослых {0} найден через строку поиска")
    @EnumSource(AdultGoods.class)
    void adultSearchResultShouldNotBeEmpty(AdultGoods adultGoods) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch()
                .fillItemSearchEnter(adultGoods.name());
        search.getTitle(adultGoods.name())
                .getNumberItems();
    }

    static Stream<Arguments> adultSearchResultShouldNotBeEmptyTagsExist() {
        return Stream.of(
                Arguments.of(AdultGoods.WHISKY, List.of("Виски", "Напитки", "Посуда для сервировки",
                        "Конфеты", "Конфеты и подарочные наборы", "Для тела", "до -40% на посуду Platinum Choice",
                        "Товары для отдыха и дома")),
                Arguments.of(AdultGoods.BEER, List.of(
                        "Пиво, сидр, медовуха",
                        "Импортное пиво",
                        "Лицензионное пиво")),
                Arguments.of(AdultGoods.VODKA, List.of("Конфеты",
                        "Конфеты и подарочные наборы",
                        "Водка, настойки",
                        "Водка"))
        );
    }

    @MethodSource
    @Tag("SMOKE")
    @DisplayName("Товар для взрослых найден через строку поиска и тег существует")
    @ParameterizedTest(name = "Товар для взрослых {0} найден через строку поиска и теги {1} существует")
    void adultSearchResultShouldNotBeEmptyTagsExist(AdultGoods adultGoods, List<String> tags) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch()
                .fillItemSearchEnter(adultGoods.name());
        search.getTitle(adultGoods.name())
                .getTags(tags);
    }


}
