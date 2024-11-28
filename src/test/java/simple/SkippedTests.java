package simple;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("SIMPLE")
@Tag("regress")
public class SkippedTests {

    @Disabled
    @Test
    public void NegativeTest1() {
        assertTrue(true);
    }

    @Disabled
    @Test
    public void NegativeTest2() {
        assertTrue(true);
    }

    @Disabled
    @Test
    public void NegativeTest3() {
        assertTrue(true);
    }
}
