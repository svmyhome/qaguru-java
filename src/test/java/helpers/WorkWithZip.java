package helpers;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class WorkWithZip {
    private ClassLoader cl = getClass().getClassLoader();

    public List<String> getListNames(String name) throws Exception {
        List<String> listFileNames = new ArrayList<>();
        try (InputStream is = cl.getResourceAsStream(name);
             ZipInputStream zis = new ZipInputStream(is)) {

            ZipEntry zipEntry;

            while ((zipEntry = zis.getNextEntry()) != null) {
                System.out.println(zipEntry.getName());
                listFileNames.add(zipEntry.getName());
            }
        }
        return listFileNames;
    }
}
