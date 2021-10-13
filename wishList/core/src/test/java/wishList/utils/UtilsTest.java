package wishList.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UtilsTest {

    private static final String os = System.getProperty("os.name");

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void updatePathForAnyOsTest() {
        String cwd = new File("").getAbsolutePath();
        String finalPath = switch (os) {
            case "Windows" -> cwd + "\\some\\folder\\here\\";
            default -> cwd + "/some/folder/here/";
        };
        assertEquals(finalPath, Utils.updatePathForAnyOs(cwd, "some", "folder", "here"));
    }

    @Test
    void hasFileSeparatorAtEndTest() {
        switch (os) {
            case "Windows" -> {
                assertTrue(Utils.hasFileSeparatorAtEnd("path\\"));
                assertFalse(Utils.hasFileSeparatorAtEnd("path"));
            }
            default -> {
                assertTrue(Utils.hasFileSeparatorAtEnd("path/"));
                assertFalse(Utils.hasFileSeparatorAtEnd("path"));
            }
        }

    }
}
