package files;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WorkWithPdfTests {
    @Test
    @DisplayName("Чтение из PDF")
    void readPdf() throws Exception {
        open("https://junit.org/junit5/docs/current/user-guide/");
        File downloaded = $("[href='junit-user-guide-5.11.3.pdf']").download();

        PDF pdf = new PDF(downloaded);
        Assertions.assertEquals("JUnit 5 User Guide", pdf.title);
    }

}
