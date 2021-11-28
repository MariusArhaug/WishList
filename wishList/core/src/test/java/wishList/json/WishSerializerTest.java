package wishList.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WishSerializerTest {
  private JsonHandler jsonHandler;
  private User user;

  private static void resetFiles() throws Exception {
    Utils.resetFile(JsonHandlerTest.testFolder, "user5@gmailcom.json");
  }

  @BeforeEach
  public void setUp() {
    user = new User("first", "last", "user5@gmail.com", "123Password!");
    jsonHandler = new JsonHandler(JsonHandlerTest.testFolder);
  }

  @AfterEach
  public void tearDown() throws Exception {
    resetFiles();
    user = null;
  }

  @Test
  public void wishSerializerTest() throws Exception {
    jsonHandler.addUser(user);
    User userFromFile = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    jsonHandler.makeWishList("Test", userFromFile);
    User loadedUser = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    jsonHandler.addWish("TestItem", loadedUser.getOwnedWishLists().get(0), loadedUser);
    User loadedUserAgain = jsonHandler.loadUser(user.getEmail(), user.getPassword()).get();
    assertEquals(loadedUserAgain.getOwnedWishLists().get(0).getWishes().toString(),"[TestItem]");
  }
}
