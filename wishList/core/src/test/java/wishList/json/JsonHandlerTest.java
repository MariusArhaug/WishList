package wishList.json;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.core.WishList;
import wishList.utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

public class JsonHandlerTest {
  public static String testFolder =
      Paths.get(
              new File("").getAbsolutePath(),
              "src",
              "test",
              "java",
              "wishList",
              "json",
              "test-resources")
          .toString();
  private User user;
  private JsonHandler jsonHandler;

  private static void resetFiles() throws Exception {
    Utils.resetFile(testFolder, "gmail@gmailcom.json");
    Utils.resetFile(testFolder, "gmail2@gmailcom.json");
    Utils.resetFile(testFolder, "John@emailno.json");
  }

  @BeforeEach
  public void setUp() {
    user = new User("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler = new JsonHandler(testFolder);
  }

  @AfterEach
  public void tearDown() throws Exception {
    resetFiles();
    user = null;
    jsonHandler = null;
  }

  @Test
  public void getPathTest() {
    assertEquals(testFolder, jsonHandler.getPath());
  }

  @Test
  public void addUserTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    assertEquals(user.getFirstName(), jsonUser.getFirstName());
    assertEquals(user.getLastName(), jsonUser.getLastName());
    assertEquals(user.getEmail(), jsonUser.getEmail());
    assertEquals(user.getPassword(), jsonUser.getPassword());

    assertThrows(IllegalArgumentException.class, () -> jsonHandler.addUser(jsonUser));
  }

  @Test
  public void loadUser() throws Exception {
    assertEquals(Optional.empty(), jsonHandler.loadUser("Email2@gmail.com", "Password123!2"));
    User user = jsonHandler.addUser(new User("John", "Smith", "John@email.no", "Password123!"));
    Optional<User> loadedUser = jsonHandler.loadUser("John@email.no", "Password123!");
    assertTrue(loadedUser.isPresent());
    assertEquals(user.getEmail(), loadedUser.get().getEmail());
  }

  @Test
  public void addContactTest() throws Exception {
    jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    User contactUser = jsonHandler.addUser("FirstName2", "LastName2", "gmail2@gmail.com", "Password123!2");
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertTrue(loadedUser.getContacts().isEmpty());
    jsonHandler.addContact("gmail2@gmail.com", loadedUser);
    User loadedUserAgain = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    List<String> contacts = new ArrayList<>();
    contacts.add(contactUser.getEmail());
    assertEquals(loadedUserAgain.getContacts(), contacts);
  }

  @Test
  public void removeContactTest() throws Exception {
    jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler.addUser("FirstName2", "LastName2", "gmail2@gmail.com", "Password123!2");
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertTrue(loadedUser.getContacts().isEmpty());
    jsonHandler.addContact("gmail2@gmail.com", loadedUser);
    jsonHandler.removeContact("gmail2@gmail.com", loadedUser);
    User loadedUserAgain = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertTrue(loadedUserAgain.getContacts().isEmpty());
  }

  @Test
  public void makeWishListTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    assertTrue(jsonUser.getOwnedWishLists().isEmpty());
    jsonHandler.makeWishList("Test", jsonUser);
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertEquals(loadedUser.getOwnedWishLists().toString(), "[Test,gmail@gmail.com,[]]");
  }

  @Test
  public void removeWishListTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler.makeWishList("Test", jsonUser);
    jsonHandler.removeWishList("Test", jsonUser);
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertTrue(loadedUser.getOwnedWishLists().isEmpty());
  }

  @Test
  public void addWishTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler.makeWishList("Test", jsonUser);
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    jsonHandler.addWish("TestItem", loadedUser.getOwnedWishLists().get(0), jsonUser);
    User loadedUserAgain = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertEquals(loadedUserAgain.getOwnedWishLists().toString(), "[Test,gmail@gmail.com,[TestItem]]");
  }

  @Test
  public void removeWishTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler.makeWishList("Test", jsonUser);
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    jsonHandler.addWish("TestItem", loadedUser.getOwnedWishLists().get(0), jsonUser);
    jsonHandler.removeWish("TestItem", loadedUser.getOwnedWishLists().get(0), jsonUser);
    User loadedUserAgain = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    assertEquals(loadedUserAgain.getOwnedWishLists().toString(), "[Test,gmail@gmail.com,[]]");
  }
  @Test
  public void shareWishListTest() throws Exception {
    User jsonUser = jsonHandler.addUser("FirstName", "LastName", "gmail@gmail.com", "Password123!");
    jsonHandler.makeWishList("Test", jsonUser);
    User jsonUser2 = jsonHandler.addUser("FirstName2", "LastName2", "gmail2@gmail.com", "Password123!2");
    User loadedUser = jsonHandler.loadUser("gmail@gmail.com", "Password123!").get();
    List<User> group = new ArrayList<>();
    group.add(jsonUser2);
    jsonHandler.shareWishList(loadedUser, loadedUser.getOwnedWishLists().get(0), group);
    User loadedUser2 = jsonHandler.loadUser("gmail2@gmail.com", "Password123!2").get();
    assertEquals(loadedUser2.getInvitedWishLists().toString(), "[Test,gmail@gmail.com,[]]");
  }
}
