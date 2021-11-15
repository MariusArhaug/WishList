package wishList.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UtilsTest {

    @BeforeEach
    void setup() {

    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void updatePathForAnyOsTest() {
        String cwd = new File("").getAbsolutePath();
        String finalPath = "some" + File.pathSeparator + "folder" + File.pathSeparator + "here" + File.pathSeparator;
        assertEquals(finalPath, Utils.updatePathForAnyOs(cwd, "some", "folder", "here"));
    }

    @Test
    void hasFileSeparatorAtEndTest() {
        assertTrue(Utils.hasFileSeparatorAtEnd("path\\"));
        assertTrue(Utils.hasFileSeparatorAtEnd("path/"));
    }
}
