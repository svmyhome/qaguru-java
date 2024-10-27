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
    void fillAllFieldTest() {

        textBoxPage.openPage()
                .fillFullName(name)
                .fillEmail(email)
                .fillCurrentAddress(current_address)
                .fillPermanentAddress(permanent_address)
                .clickSubmit();

        textBoxPage.checkSubmitForm(name, email, current_address, permanent_address);
    }
}
