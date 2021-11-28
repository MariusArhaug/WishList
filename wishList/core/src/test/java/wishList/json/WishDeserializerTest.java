package wishList.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishDeserializerTest {
  private WishDeserializer wishDeserializer;
  private Wish wish;
  private DeserializationContext deserializationContext;
  private JsonParser parser;
  private String json;
  private ObjectMapper mapper;
  private JsonFactory factory;

  @BeforeEach
  public void setUp() throws IOException {
    wish = new Wish("name");
    json = "{\"name\":\"name\"}";
    mapper = new ObjectMapper();
    factory = mapper.getFactory();
    parser = factory.createParser(json);
    wishDeserializer = new WishDeserializer();
  }

  @AfterEach
  public void tearDown() {
    wish = null;
    json = null;
    mapper = null;
    factory = null;
    parser = null;
    wishDeserializer = null;
  }

  @Test
  public void wishDeserializerTest() throws IOException {
    assertEquals(
        (wishDeserializer.deserialize(parser, deserializationContext)).toString(), wish.toString());
  }
}
