package com.lenta.test_data;

public enum AdultGoods {
    WHISKY("JACK DANIEL'S"),
    VODKA("TUNDRA"),
    BEER("Пиво светлое безалкогольное БАЛТИКА");

    private final String itemName;

    AdultGoods(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }
}
