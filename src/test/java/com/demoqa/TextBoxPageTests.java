package com.demoqa;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("REGRESS")
public class TextBoxPageTests extends TestBase {

    String name = fakerRu.name().firstName();
    String email = fakerEng.internet().emailAddress();
    String current_address = fakerRu.address().streetAddress();
    String permanent_address = fakerRu.address().streetAddress();

    @DisplayName("")
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
