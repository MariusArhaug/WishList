package wishList.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.core.WishList;
import wishList.utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSerializerTest {
  private JsonHandler jsonHandler;
  private User user;

  private static void resetFiles() throws Exception {
    Utils.resetFile(JsonHandlerTest.testFolder, "2user@gmailcom.json");
  }

  @BeforeEach
  public void setUp() throws IOException {
    user = new User("first", "last", "2user@gmail.com", "123Password!");
    jsonHandler = new JsonHandler(JsonHandlerTest.testFolder);
  }

  @AfterEach
  public void tearDown() throws Exception {
    resetFiles();
    user = null;
  }

  @Test
  public void userSerializerTest() throws Exception {
    jsonHandler.addUser(user);
    User userFromFile = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    assertEquals(userFromFile.getFirstName(), "first");
    assertEquals(userFromFile.getLastName(), "last");
    assertEquals(userFromFile.getEmail(), "2user@gmail.com");
    assertEquals(userFromFile.getPassword(), "123Password!");
    List<WishList> emptyOwnList = new ArrayList<>();
    assertEquals(userFromFile.getOwnedWishLists(), emptyOwnList);
    List<WishList> invitedWishLists = new ArrayList<>();
    assertEquals(userFromFile.getInvitedWishLists(), invitedWishLists);
    List<String> contacts = new ArrayList<>();
    assertEquals(userFromFile.getContacts(), contacts);
  }
}
