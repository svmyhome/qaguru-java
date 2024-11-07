package com.lenta;

import com.lenta.pages.CatalogPanel;
import com.lenta.pages.MainPage;
import org.junit.jupiter.api.Test;

import static java.lang.Thread.sleep;

public class Catalog extends TestBase {
    MainPage mainPage = new MainPage();
    CatalogPanel catalogPanel = new CatalogPanel();

    @Test
    void openCatalog() throws InterruptedException {
        mainPage.openMainPage();
        sleep(5000);

        mainPage.openCatalog();
        catalogPanel.clickMainCat();
    }
}
