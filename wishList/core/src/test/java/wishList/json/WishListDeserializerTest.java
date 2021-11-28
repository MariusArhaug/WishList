package wishList.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.WishList;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishListDeserializerTest {
  private WishListDeserializer wishListDeserializer;
  private WishList wishList;
  private DeserializationContext deserializationContext;
  private JsonParser parser;
  private String json;
  private ObjectMapper mapper;
  private JsonFactory factory;

  @BeforeEach
  public void setUp() throws IOException {
    wishList = new WishList("name");
    json = "{\"name\":\"name\"}";
    mapper = new ObjectMapper();
    factory = mapper.getFactory();
    parser = factory.createParser(json);
    wishListDeserializer = new WishListDeserializer();
  }

  @AfterEach
  public void tearDown() {
    wishList = null;
    json = null;
    mapper = null;
    factory = null;
    parser = null;
    wishListDeserializer = null;
  }

  @Test
  public void wishListDeserializerTest() throws IOException {
    assertEquals(
        (wishListDeserializer.deserialize(parser, deserializationContext)).toString(),
        wishList.toString());
  }
}
