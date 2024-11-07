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
    @DisplayName("Товар не должен быть пустым VALUE Source")
    @ParameterizedTest(name = "Товар {0} должен находится")
    @ValueSource(strings = {"молоко", "кефир", "сметана"})
    void searchResultShouldNotBeEmpty(String item) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems();
    }

    @Tag("BLOCKER")
    @DisplayName("Товар не должен быть пустым CSVSOURCE")
    @ParameterizedTest(name = "Товар {0} должен находится")
    @CsvSource(value = {"молоко,  Молоко и сливки", "кефир, Кисломолочные продукты", "сметана, Сметана"})
    void searchResultShouldNotBeEmptyCSV(String item, String tag) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems()
                .getTag(tag);
    }


    @Tag("BLOCKER")
    @DisplayName("Товар не должен быть пустым CSVSOURCE")
    @ParameterizedTest(name = "Товар {0} должен находится")
    @CsvSource(value = {
            "молоко|  Молоко и сливки",
            "кефир| Кисломолочные продукты",
            "сметана| Сметана"},
            delimiter = '|')
    void searchResultShouldNotBeEmptyDelimeter(String item, String tag) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems()
                .getTag(tag);
    }

    @Tag("BLOCKER")
    @DisplayName("Товар не должен быть пустым CSVSOURCE")
    @ParameterizedTest(name = "Товар {0} должен находится")
    @CsvFileSource(resources = "/test_data/searchResultShouldNotBeEmptyCSVFile.csv")
    void searchResultShouldNotBeEmptyCSVFile(String item, String tag) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch().fillItemSearchEnter(item);
        search.getTitle(item)
                .getNumberItems()
                .getTag(tag);
    }


    @Tags({@Tag("SEARCH"), @Tag("ADULT")})
    @DisplayName("Товар для взрослых не должен быть пустым")
    @ParameterizedTest(name = "Товар для взрослых {0} должен находится")
    @EnumSource(AdultGoods.class)
    void adultSearchResultShouldNotBeEmpty(AdultGoods adultGoods) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch()
                .fillItemSearchEnter(adultGoods.name());
        search.getTitle(adultGoods.name())
                .getNumberItems();
    }

    static Stream<Arguments> adultMethod() {
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
    @DisplayName("Товар")
    @ParameterizedTest(name = "Товар для взрослых {0} должен находится")
    void adultMethod(AdultGoods adultGoods, List<String> tags) throws InterruptedException {
        mainPage.openMainPage();
        sleep(3000);
        mainPage.openSearch()
                .fillItemSearchEnter(adultGoods.name());
        search.getTitle(adultGoods.name())
                .getTags(tags);
    }


}
