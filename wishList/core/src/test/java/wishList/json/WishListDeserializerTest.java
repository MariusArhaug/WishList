package wishList.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishListDeserializerTest {
  private WishListDeserializer wishListDeserializer;
  private DeserializationContext deserializationContext;
  private JsonParser parser;
  private String json;
  private User user;
  private ObjectMapper mapper;
  private JsonFactory factory;

  @BeforeEach
  public void setUp() throws IOException {
    user = new User("1", "2", "3@gmail.com", "12345678");
    user.makeWishList("name");
    json = "{\"owner\":\"3@gmail.com\",\"name\":\"name\"}";
    mapper = new ObjectMapper();
    factory = mapper.getFactory();
    parser = factory.createParser(json);
    wishListDeserializer = new WishListDeserializer();
  }

  @AfterEach
  public void tearDown() {
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
            user.getOwnedWishLists().get(0).toString());
  }
}
