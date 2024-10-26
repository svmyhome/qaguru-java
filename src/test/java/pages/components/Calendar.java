package pages.components;

import static com.codeborne.selenide.Selenide.$;

public class Calendar {
    public void setDate(String month, String year, String date) {
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0" + date + ":not(.react-datepicker__day--outside-month)").click();

    }
}
