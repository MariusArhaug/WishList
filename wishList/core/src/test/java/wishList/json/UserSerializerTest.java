package wishList.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.core.WishList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserSerializerTest {
  private final File usersTestFile = new File(JsonHandlerTest.testFolder, "user@gmailcom.json");
  private JsonHandler jsonHandler;
  private User user;
  private ObjectMapper mapper;

  @BeforeEach
  void setUp() throws IOException {
    user = new User("first", "last", "user@gmail.com", "123Password!");
    mapper = new ObjectMapper();
    mapper.getFactory().createGenerator(usersTestFile, JsonEncoding.UTF8);
    jsonHandler = new JsonHandler(JsonHandlerTest.testFolder);
  }

  @AfterEach
  void tearDown() {
    user = null;
    mapper = null;
  }

  @Test
  void userSerializerTest() throws Exception {
    jsonHandler.addUser(user);
    User userFromFile = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    assertEquals(userFromFile.getFirstName(), "first");
    assertEquals(userFromFile.getLastName(), "last");
    assertEquals(userFromFile.getEmail(), "user@gmail.com");
    assertEquals(userFromFile.getPassword(), "123Password!");
    List<WishList> emptyOwnList = new ArrayList<>();
    assertEquals(userFromFile.getOwnedWishLists(), emptyOwnList);
    List<WishList> invitedWishLists = new ArrayList<>();
    assertEquals(userFromFile.getInvitedWishLists(), invitedWishLists);
    List<User> contacts = new ArrayList<>();
    assertEquals(userFromFile.getContacts(), contacts);
  }
}
