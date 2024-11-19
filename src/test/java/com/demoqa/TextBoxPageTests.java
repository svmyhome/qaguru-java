package com.demoqa;

import helpers.RandomUtils;
import org.junit.jupiter.api.Test;
import pages.TextBoxPage;


public class TextBoxPageTests extends TestBase {
    TextBoxPage textBoxPage = new TextBoxPage();
    RandomUtils randomData = new RandomUtils();

    String name = randomData.getRandomFirstName();
    String email = randomData.getRandomEmail();
    String current_address = randomData.getRandomAddress();
    String permanent_address = randomData.getRandomAddress();

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
