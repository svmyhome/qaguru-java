package jenkins;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


public class SytestemPropertyTests {

    @Test
    void EmptySystemPropertyTest() {
        String browser = System.getProperty("browser");
        System.out.println(browser);
    }

    @Test
    void ChromeSystemPropertyTest() {
        System.setProperty("browser", "chrome");
        String browser = System.getProperty("browser");
        System.out.println(browser);
    }

    @Test
    @Tag("property")
        // gradle property_test -Dbrowser=opera
    void DefSystemPropertyTest() {
        String browser = System.getProperty("browser", "mozilla");
        System.out.println(browser);
    }
}
