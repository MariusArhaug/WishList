package wishList.json;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishListSerializerTest {
  private JsonHandler jsonHandler;
  private User user;
  private User user2;

  private static void resetFiles() throws Exception {
    Utils.resetFile(JsonHandlerTest.testFolder, "user2@gmailcom.json");
    Utils.resetFile(JsonHandlerTest.testFolder, "user3@gmailcom.json");

  }

  @BeforeEach
  public void setUp() {
    user = new User("first", "last", "user2@gmail.com", "123Password!");
    user2 = new User("first", "last", "user3@gmail.com", "123Password!");
    jsonHandler = new JsonHandler(JsonHandlerTest.testFolder);
  }

  @AfterEach
  public void tearDown() throws Exception {
    resetFiles();
    user = null;
    user2 = null;
  }

  @Test
  public void wishListSerializerTest() throws Exception {
    jsonHandler.addUser(user);
    jsonHandler.addUser(user2);
    User userFromFile = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    jsonHandler.makeWishList("Test", userFromFile);
    User loadedUser = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    assertEquals(loadedUser.getOwnedWishLists().toString(), "[Test,first,last,user2@gmail.com,123Password!,[]]");
    List<User> group = new ArrayList<>();
    group.add(user2);
    jsonHandler.shareWishList(loadedUser, loadedUser.getOwnedWishLists().get(0), group);
    User loadedUser2 = jsonHandler.loadUser(user2.getEmail(), user2.getPassword()).get();
    assertEquals(loadedUser2.getNthInvitedWishList(0).toString(), "Test,null,[]");
  }
}
