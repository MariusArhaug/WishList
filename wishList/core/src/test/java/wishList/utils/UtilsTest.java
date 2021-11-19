package wishList.utils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UtilsTest {

  @BeforeEach
  void setup() {}

  @AfterEach
  void tearDown() {}

  @Test
  void hasFileSeparatorAtEndTest() {
    assertTrue(Utils.hasFileSeparatorAtEnd("path/"));
    assertFalse(Utils.hasFileSeparatorAtEnd("path"));
  }
}
