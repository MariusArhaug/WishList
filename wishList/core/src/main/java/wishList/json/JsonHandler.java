package wishList.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Handle JSON requests. */
public class JsonHandler {

  private final ObjectMapper mapper;
  private final String path;

  /**
   * Create jsonHandler with a given path.
   *
   * @param path path to wishList.json files
   */
  public JsonHandler(String path) {
    this.mapper = new ObjectMapper();
    this.mapper.registerModule(new JsonModule());
    this.path = path;
  }

  public String getPath() {
    return this.path;
  }

  /**
   * Load all users from users.wishList.json.
   *
   * @return List of users
   * @throws IOException if not able to find file
   */
  public List<User> loadJsonUserList() throws IOException {
    List<User> users = new ArrayList<>();
    File dir = new File(getPath());
    File[] directoryListing = dir.listFiles();
    if (directoryListing == null) {
      return null;
    }
    for (File child : directoryListing) {
      if ((child.length() > 2)) {
        User user = mapper.readValue(child, User.class);
        if (user != null) {
          users.add(user);
        }
      }
    }
    return users;
  }

  public List<User> getUsers() throws IOException {
    return this.loadJsonUserList();
  }

  public User addUser(User user) throws IOException {
    return this.addUser(
        user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
  }

  /**
   * Add user to users.wishList.json if it has a unique email
   *
   * @param firstname firstname
   * @param lastname lastname
   * @param email email
   * @param password password
   * @return user created
   * @throws IllegalArgumentException if email is not unique
   * @throws IOException if not found file
   */
  public User addUser(String firstname, String lastname, String email, String password)
      throws IllegalArgumentException, IOException {

    List<User> users = loadJsonUserList();
    if (users == null) {
      throw new IOException("Couldn't load users from file");
    }
    for (User user : users) {
      if (user.getEmail().equals(email)) {
        throw new IllegalArgumentException(
            "A user with this email already exists, please try another one");
      }
    }
    User newUser = new User(firstname, lastname, email, password);

    mapper.writeValue(new File(this.path + "/" + userFileName(newUser) + ".json"), newUser);
    return newUser;
  }

  /**
   * Make fileName from user's email.
   *
   * @param user user to make fileName for
   * @return filename for user as string
   * @throws IOException if file not found
   */
  private String userFileName(User user) throws IOException {
    String email = user.getEmail();
    String fileName = email.replace(".", "");
    int x = 1;
    while (!checkUniqueFileName(fileName)) {
      fileName = fileName + x;
      x += 1;
    }
    return fileName;
  }

  /**
   * Check if filename is unique.
   *
   * @param fileName File name to check uniqueness of
   * @return boolean, true if filename is unique
   */
  private boolean checkUniqueFileName(String fileName) {
    List<String> fileNames = new ArrayList<>();
    File[] files = new File(getPath()).listFiles();
    if (files == null) {
      return true;
    }
    for (File file : files) {
      if (file.isFile()) {
        fileNames.add(file.getName());
      }
    }
    return !fileNames.contains(fileName);
  }

  /**
   * Add contact to user file.
   *
   * @param newContactEmail contact to add
   * @param user add to this users friends list
   * @return updated user
   * @throws IOException file not found
   * @throws IllegalArgumentException user has that contact as friend already.
   */
  public User addContact(String newContactEmail, User user)
      throws IOException, IllegalArgumentException {
    for (String email : user.getContacts()) {
      if (email.equals(newContactEmail)) {
        throw new IllegalArgumentException("This user is already friends with that user!");
      }
    }
    user.addContact(newContactEmail);
    return this.remakeUserInFile(user);
  }

  /**
   * remove contact from user file.
   *
   * @param email email of contact to remove
   * @param user remove from this' users friends list
   * @return updated user
   * @throws IOException file not found
   */
  public User removeContact(String email, User user) throws IOException {
    user.removeContact(email);
    return this.remakeUserInFile(user);
  }

  /**
   * add wish list to users file.
   *
   * @param wishListName name of new wish list
   * @param user user that makes wish list
   * @return updated user.
   * @throws IOException file not found
   */
  public User makeWishList(String wishListName, User user) throws IOException {
    user.makeWishList(wishListName);
    return this.remakeUserInFile(user);
  }

  /**
   * remove wish list from users file.
   *
   * @param wishListName name of wish list to remove
   * @param user user that removes wish list
   * @throws IOException file not found
   */
  public User removeWishList(String wishListName, User user) throws IOException {
    user.removeWishList(wishListName);
    return this.remakeUserInFile(user);
  }

  /**
   * Add wish to wish list in users file.
   *
   * @param wishName name of wish to add
   * @param wishList wish list to add to
   * @param user user to add to
   * @throws IOException file not found
   */
  public User addWish(String wishName, WishList wishList, User user) throws IOException {
    user.addWish(wishList, new Wish(wishName));
    return this.remakeUserInFile(user);
  }

  /**
   * Remove wish from wish list in users file.
   *
   * @param wishName name of wish to remove
   * @param wishList wish list to remove from
   * @param user user to remove from
   * @return updated user.
   * @throws IOException file not found
   */
  public User removeWish(String wishName, WishList wishList, User user) throws IOException {
    user.removeWish(wishList.getName(), wishName);
    return this.remakeUserInFile(user);
  }

  /**
   * Share wish list with users.
   *
   * @param user user that shares wish list
   * @param wishList wish list to share
   * @param group group to share with
   * @return updated user.
   * @throws IOException file not found
   */
  public User shareWishList(User user, WishList wishList, List<User> group) throws IOException {
    user.shareWishList(wishList, group);
    this.remakeUserInFile(user);
    for (User u : group) {
      this.remakeUserInFile(u);
    }
    return user;
  }

  /**
   * Update user in file.
   *
   * @param user user to remake
   * @return updated user from file.
   * @throws IOException if file not found
   */
  private User remakeUserInFile(User user) throws IOException {
    mapper.writeValue(new File(this.path, userFileName(user) + ".json"), user);
    return user;
  }

  /**
   * Load user with given email and password.
   *
   * @param email email of user
   * @param password password of user
   * @return user if he exists
   * @throws IOException could not load file.
   */
  public Optional<User> loadUser(String email, String password) throws IOException {
    List<User> users = loadJsonUserList();
    if (users == null) {
      throw new Error("Something wrong happened!");
    }
    for (User user : users) {
      if (user.checkCredentials(email, password)) {
        return Optional.of(user);
      }
    }
    return Optional.empty();
  }

  /**
   * load user from filename.
   *
   * @param filename filename
   * @return User
   * @throws IOException file not found
   */
  public User loadJsonUser(String filename) throws IOException {
    return mapper.readValue(new File(this.path, filename + ".json"), new TypeReference<>() {});
  }
}
