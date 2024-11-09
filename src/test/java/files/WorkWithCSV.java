package files;

import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class WorkWithCSV {
    private ClassLoader cl = WorkWithCSV.class.getClassLoader();

    @Test
    @DisplayName("Чтение из CSV")
    void readCsv() throws Exception {
        try (InputStream is = cl.getResourceAsStream("example.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {

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