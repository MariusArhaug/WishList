package wishList.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.Wish;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class WishSerializerTest {
  private final File wishTestFile = new File(JsonHandlerTest.testFolder, "wishes.json");
  private Wish wish;
  private ObjectMapper mapper;

  @BeforeEach
  void setUp() throws IOException {
    wish = new Wish("Toy");
    mapper = new ObjectMapper();
    mapper.getFactory().createGenerator(wishTestFile, JsonEncoding.UTF8);
  }

  @AfterEach
  void tearDown() {
    wish = null;
    mapper = null;
  }

  @Test
  void wishListSerializerTest() throws Exception {

    List<Wish> wishes = new ArrayList<>();
    wishes.add(wish);
    mapper.writeValue(wishTestFile, wishes);

    Wish[] wishesFromFile = mapper.readValue(wishTestFile, new TypeReference<Wish[]>() {});

    assertEquals(wishesFromFile[0].getName(), "Toy");
    assertNull(wishesFromFile[0].getBelongTo());
  }
}
