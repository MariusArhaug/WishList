package wishList.json;

import org.junit.jupiter.api.AfterAll;
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

  private static void resetFiles() throws Exception {
    Utils.resetFile(testFolder, "gmail@gmailcom.json");
    Utils.resetFile(testFolder, "John@emailno.json");

  }

  @BeforeEach
  void setUp() throws Exception {
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

  @AfterAll
  static void finish() {
    File file1 = new File(testFolder + "gmail@gmailcom.json");
    File file2 = new File(testFolder + "John@emailno.json");
    file1.delete();
    file2.delete();
  }

  @Test
  void getPathTest() {
    assertEquals(testFolder, jsonHandler.getPath());
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

  @Test
  void addWishTest() {
    Wish wishOne = new Wish("Chair");
    WishList wishList = new WishList("Decorations");
    wishList.addWish(wishOne);
    user.addWishList(wishList);

    assertThrows(IllegalArgumentException.class, () -> jsonHandler.addWish("Chair", wishList));
    assertThrows(
        IllegalArgumentException.class, () -> jsonHandler.addWishList("Decorations", user));
    JsonHandler jsonHandler1 = new JsonHandler("");
    assertThrows(Exception.class, () -> jsonHandler1.addWish("throw", wishList));
    assertThrows(Exception.class, () -> jsonHandler1.addWishList("throw2", user));
  }

}
