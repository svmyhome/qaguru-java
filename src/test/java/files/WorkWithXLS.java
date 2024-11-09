package files;

import com.codeborne.xlstest.XLS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WorkWithXLS {
    @Test
    @DisplayName("Чтение из XLS")
    void readXlsx() throws Exception {
        open("https://www.planetaexcel.ru/techniques/12/2681/");
        File downloaded = $("[href='/upload/iblock/58f/hyperlink.xlsx']").download();

        XLS xls = new XLS(downloaded);
        String actual_result = xls.excel.getSheetAt(1).getRow(3).getCell(1).getStringCellValue();
        Assertions.assertEquals("Тобольск", actual_result);
    }

}
