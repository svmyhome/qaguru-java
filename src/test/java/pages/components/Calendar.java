package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class Calendar {
    public void setDate(String month, String year, String date) {
        $(".react-datepicker__month-select").selectOptionByValue(month);
        $(".react-datepicker__year-select").selectOptionByValue(year);
        $(".react-datepicker__day--0" + date).click();
    }
}
