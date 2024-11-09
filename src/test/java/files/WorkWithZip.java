package files;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkWithZip {
    private ClassLoader cl = WorkWithZip.class.getClassLoader();

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
}