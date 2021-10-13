package json;

import core.User;
import core.Wish;
import core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JsonHandlerTest {
  static String testFolder =
      Utils.updatePathForAnyOs(
          new File("").getAbsolutePath(), "src", "test", "java", "json", "test-resources");
  private User user;
  private WishList wishList;
  private Wish wish;
  private JsonHandler jsonHandler;

  @BeforeEach
  void setUp() throws Exception {
    user = new User("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    wishList = new WishList("Wedding", user);
    wish = new Wish("Chair");
    wishList.addWish(wish);
    jsonHandler = new JsonHandler(testFolder);
  }

  @AfterEach
  void tearDown() throws Exception {
    Utils.resetFile(testFolder, "users.json");
    Utils.resetFile(testFolder, "wishes.json");
    // Utils.resetFile(testFolder, "wishLists.json");
    user = null;
    wishList = null;
    wish = null;
    jsonHandler = null;
  }

  @Test
  void addUserTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    assertEquals(user.getFirstName(), jsonUser.getFirstName());
    assertEquals(user.getLastName(), jsonUser.getLastName());
    assertEquals(user.getEmail(), jsonUser.getEmail());
    assertEquals(user.getPassword(), jsonUser.getPassword());

    assertThrows(IllegalArgumentException.class, () -> jsonHandler.addUser(jsonUser));
  }

  @Test
  void addWishListTest() throws Exception {
    WishList wishList = jsonHandler.addWishList("Wedding", user);
    assertEquals(user, wishList.getOwner());
  }

  @Test
  void addWishTest() throws Exception {
    assertEquals(jsonHandler.addWish("Chair", wishList).getName(), wish.getName());
    assertEquals(jsonHandler.addWish("Chair", wishList).getBelongTo(), wish.getBelongTo());
  }

  @Test
  void loadUser() throws Exception {
    assertEquals(Optional.empty(), jsonHandler.loadUser("Email2@gmail.com", "Password123!2"));
    User user = jsonHandler.addUser(new User("John", "Smith", "John@email.no", "Password123!"));
    Optional<User> loadedUser = jsonHandler.loadUser("John@email.no", "Password123!");
    assertTrue(loadedUser.isPresent());
    assertEquals(user.getEmail(), loadedUser.get().getEmail());
  }
}
