package com.demoqa;

import org.junit.jupiter.api.Test;
import pages.TextBoxPage;


public class TextBoxPageTests extends TestBase {

    TextBoxPage textBoxPage = new TextBoxPage();

    String name = "Иванов Иван";
    String email = "afdsdas@mail.ru";
    String current_address = "Spb nevsky 19";
    String permanent_address = "Spb nevsky 19";

    @Test
    void fillAllField1Test() {

        textBoxPage.openPage()
                .fillFullName(name)
                .fillEmail(email)
                .fillCurrentAddress(current_address)
                .fillPermanentAddress(permanent_address)
                .clickSubmit();

        textBoxPage.checkTextBoxString(name)
                .checkTextBoxString(email)
                .checkTextBoxString(current_address)
                .checkTextBoxString(permanent_address);
    }
}
