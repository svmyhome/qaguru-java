package files;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import helpers.WorkWithZip;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkWithZipTests {
    private ClassLoader cl = getClass().getClassLoader();

    private InputStream getFileFromZip(String fileExtension) throws Exception {
        ZipInputStream zis = new ZipInputStream(cl.getResourceAsStream("example.zip"));
        ZipEntry zipEntry;
        while ((zipEntry = zis.getNextEntry()) != null) {
            if (zipEntry.getName().endsWith(fileExtension)) {
                return zis;
            }
        }
        return InputStream.nullInputStream();
    }

    @Test
    @DisplayName("Чтение из ZIP")
    void readZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.zip");
             ZipInputStream zis = new ZipInputStream(is)) {

            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                System.out.println(zipEntry.getName());

            }
        }
    }

    @Test
    @DisplayName("Чтение данных из ZIP")
    void readZipAndAssert() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.zip");
             ZipInputStream zis = new ZipInputStream(is)) {

            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                String name = zipEntry.getName();
                if (name.contains(".pdf")) {
                    System.out.println(name);
                    try (InputStream is1 = cl.getResourceAsStream(name)) {
                        byte[] data = is1.readAllBytes();
                        PDF pdf = new PDF(data);
                        System.out.println(pdf);
                    }
                }

            }
        }
    }

    @Test
    @DisplayName("В архиве есть pdf, xlsx, json")
    void readZipAndAssert1() throws Exception {
        List<String> expectedResult = List.of("example.csv", "example.pdf", "hyperlink.xlsx");
        List<String> actualResult = WorkWithZip.getListNames("example.zip");
        Assertions.assertEquals(expectedResult, actualResult);
    }

    @Test
    @DisplayName("В архиве верный pdf")
    void readPdfFromZip() throws Exception {
        try (InputStream pdfInputStream = getFileFromZip(".pdf")) {
            PDF pdf = new PDF(pdfInputStream);
            Assertions.assertEquals("Adobe PDF Library 15.0", pdf.producer);
        }
    }

    @Test
    @DisplayName("В архиве верный xlsx")
    void readXlsxFromZip() throws Exception {
        try (InputStream xlsxInputStream = getFileFromZip(".xlsx")) {
            XLS xls = new XLS(xlsxInputStream);
            String actual_result = xls.excel.getSheetAt(1).getRow(3).getCell(1).getStringCellValue();
            Assertions.assertEquals("Тобольск", actual_result);
        }
    }

    @Test
    @DisplayName("В архиве верный CSV")
    void readCsvFromZip() throws Exception {
        try (InputStream csvInputStream = getFileFromZip(".csv")) {
            CSVReader csvReader = new CSVReader(new InputStreamReader(csvInputStream));
            List<String[]> data = csvReader.readAll();
            Assertions.assertEquals(2, data.size());
            Assertions.assertArrayEquals(
                    new String[]{"Selenide", "https://www.planetaexcel.ru/techniques/12/2681/"},
                    data.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"Selene", "https://www.planetaexcel.ru/techniques"},
                    data.get(1)
            );
        }
    }
}