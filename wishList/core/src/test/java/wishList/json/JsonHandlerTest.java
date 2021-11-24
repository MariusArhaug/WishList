package wishList.json;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
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
  private JsonHandler jsonHandler;

  private static void resetFiles() throws Exception {
    Utils.resetFile(testFolder, "gmail@gmailcom.json");
    Utils.resetFile(testFolder, "John@emailno.json");
  }

  @BeforeEach
  void setUp() {
    user = new User("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler = new JsonHandler(testFolder);
  }

  @AfterEach
  void tearDown() throws Exception {
    resetFiles();
    user = null;
    jsonHandler = null;
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
}
