package wishList.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;
import wishList.utils.Utils;

import java.io.File;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class JsonHandlerTest {
  static String testFolder =
      Utils.updatePathForAnyOs(
          new File("").getAbsolutePath(),
          "src",
          "test",
          "java",
          "wishList",
          "json",
          "test-resources");
  private User user;
  private WishList wishList;
  private Wish wish;
  private JsonHandler jsonHandler;

  @BeforeEach
  void setUp() throws Exception {
    resetFiles();
    user = new User("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    wishList = new WishList("Wedding", user);
    wish = new Wish("Chair");
    jsonHandler = new JsonHandler(testFolder);
  }

  @AfterEach
  void tearDown() throws Exception {
    resetFiles();
    user = null;
    wishList = null;
    wish = null;
    jsonHandler = null;
  }

  static void resetFiles() throws Exception {
    Utils.resetFile(testFolder, "users.json");
    Utils.resetFile(testFolder, "wishes.json");
    Utils.resetFile(testFolder, "wishLists.json");
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
  void loadUser() throws Exception {
    assertEquals(Optional.empty(), jsonHandler.loadUser("Email2@gmail.com", "Password123!2"));
    User user = jsonHandler.addUser(new User("John", "Smith", "John@email.no", "Password123!"));
    Optional<User> loadedUser = jsonHandler.loadUser("John@email.no", "Password123!");
    assertTrue(loadedUser.isPresent());
    assertEquals(user.getEmail(), loadedUser.get().getEmail());
  }
}
