package wishList.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.*;

import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;

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

  String getPath() {
    return this.path;
  }

  /**
   * Convert string to file name + path.
   *
   * @return file object
   */
  private File toFile(String fileName) throws NoSuchFileException {
    return new File(this.path, fileName);
  }

  /**
   * Load all users from users.wishList.json.
   *
   * @return List of users
   * @throws IOException if not able to find file
   */
  public List<User> loadJsonUserList() throws Exception {
    try  {
      List<User> users = new ArrayList<>();
      File dir = new File(getPath());
      File[] directoryListing = dir.listFiles();
      if (directoryListing == null) {
        return null;
      }
      for (File child : directoryListing) {
        if ((child.length() > 2) && (mapper.readValue(child, User.class) != null)) {
          users.add(mapper.readValue(child, User.class));
        }
      }
      return users;
    } catch (IOException e) {

      throw new Exception(e);
    }

  }

  /**
   * Load user from file
   *
   * @return User
   * @throws IOException if not able to find file
   */
  public User loadJsonUser(String filename) throws IOException {
    return mapper.readValue(toFile(filename + ".json"), new TypeReference<User>() {});
  }

  private List<WishList> loadJsonWishLists() throws IOException {
    return mapper.readValue(toFile("wishLists.json"), new TypeReference<List<WishList>>() {});
  }

  private List<Wish> loadJsonWishes() throws IOException {
    return mapper.readValue(toFile("wishes.json"), new TypeReference<List<Wish>>() {});
  }

  User addUser(User user) throws Exception {
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
   * @throws Exception if not found file
   */
  public User addUser(String firstname, String lastname, String email, String password)
      throws IllegalArgumentException, Exception {
    try {
      List<User> users = loadJsonUserList();
      for (User user : users) {
        if (user.getEmail().equals(email)) {
          throw new IllegalArgumentException(
              "A user with this email already exists, please try another one");
        }
      }
      User newUser = new User(firstname, lastname, email, password);
      users.add(newUser);
      mapper.writeValue(toFile("users.json"), users);
      return newUser;
    } catch (IOException e) {
      throw new Exception(e);
    }
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
    while(!checkUniqueFileName(fileName)) {
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
   * Add wishList to JSON file.
   *
   * @param name name of wishlist
   * @param user owner of wishlist
   * @return wishList object created
   * @throws Exception If user already has wishlist with that name or IO Exception
   */
  WishList addWishList(String name, User user) throws Exception {
    try {

      for (WishList w : user) {
        if (w.getName().equals(name)) {
          throw new IllegalArgumentException("This user already has a wish list with this name!");
        }
      }
      List<WishList> wishLists = loadJsonWishLists();
      WishList newWishList = new WishList(name, user);

      wishLists.add(newWishList);

      mapper.writeValue(this.toFile("wishList.json"), wishLists);
      return newWishList;
    } catch (IOException e) {
      throw new Exception();
    }
  }

  /**
   * Add wish to wishList file.
   *
   * @param name name of wish
   * @param wishList wishList to add wish in
   * @return wish
   * @throws Exception file not found
   */
  Wish addWish(String name, WishList wishList) throws Exception {
    try {
      List<Wish> ownedWishes = wishList.getWishes();
      for (Wish w : ownedWishes) {
        if (w.getName().equals(name)) {
          throw new IllegalArgumentException("This wish list already has a wish with this name!");
        }
      }

      List<Wish> wishes = this.loadJsonWishes();
      Wish newWish = new Wish(name);
      wishList.addWish(newWish);

      wishes.add(newWish);

      mapper.writeValue(this.toFile("wishList.json"), wishes);
      return newWish;
    } catch (IOException e) {
      throw new Exception(e);
    }
  }


  /**
   * Add contact to user file.
   *
   * @param newContact contact to add
   * @param user add to this users friends list
   * @throws Exception file not found
   */

  public void addContact(User newContact, User user) throws Exception {
    try {
      List<User> friends = user.getContacts();
      for (User u : friends) {
        if (u == newContact) {
          throw new IllegalArgumentException("This user is already friends with that user!");
        }
      }
      user.addContact(newContact);
      this.remakeUserInFile(user);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * remove contact from user file.
   *
   * @param email email of contact to remove
   * @param user remove from this users friends list
   * @throws Exception file not found
   */

  public void removeContact(String email, User user) throws Exception {
    try {
      user.removeContact(email);
      this.remakeUserInFile(user);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * add wish list to users file.
   *
   * @param wishListName name of new wish list
   * @param user user that makes wish list
   * @throws Exception file not found
   */

  public void makeWishList(String wishListName, User user) throws Exception {
    try {
      user.makeWishList(wishListName);
      System.out.println("serializer fault?");
      this.remakeUserInFile(user);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * remove wish list from users file.
   *
   * @param wishListName name of wish list to remove
   * @param user user that removes wish list
   * @throws Exception file not found
   */

  public void removeWishList(String wishListName, User user) throws Exception {
    try {
      user.removeWishList(wishListName);
      this.remakeUserInFile(user);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * Add wish to wish list in users file.
   *
   * @param wishName name of wish to add
   * @param wishList wish list to add to
   * @param user user to add to
   * @throws Exception file not found
   */

  public void addWish(String wishName, WishList wishList, User user) throws Exception {
    try {
      user.addWish(wishList, new Wish(wishName, wishList));
      this.remakeUserInFile(user);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * Remove wish from wish list in users file.
   *
   * @param wishName name of wish to remove
   * @param wishList wish list to remove from
   * @param user user to remove from
   * @throws Exception file not found
   */

  public void removeWish(String wishName, WishList wishList, User user) throws Exception {
    try {
      user.removeWish(wishList.getName(), wishName);
      this.remakeUserInFile(user);
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * Share wish list with users.
   *
   * @param user user that shares wish list
   * @param wishList wish list to share
   * @param group group to share with
   * @throws Exception file not found
   */

  public void shareWishList(User user, WishList wishList, List<User> group) throws Exception {
    try {
      user.shareWishList(wishList, group);
      this.remakeUserInFile(user);
      for (User u : group) {
        this.remakeUserInFile(u);
      }
    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * Update user in file
   *
   * @param user user to remake
   * @return returns the remade user
   * @throws IOException if file not found
   */
  private User remakeUserInFile(User user) throws IOException {
    mapper.writeValue(new File(this.path + userFileName(user) + ".json"), user);
    return user;
  }

  /**
   * Load user with given email and password.
   *
   * @param email email of user
   * @param password password of user
   * @return user if he exists
   * @throws Exception could not load file.
   */
  public Optional<User> loadUser(String email, String password) throws Exception {
    try {
      List<User> users = loadJsonUserList();
      for (User user : users) {
        if (user.checkCredentials(email, password)) {
          return Optional.of(user);
        }
      }
      return Optional.empty();

    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  Optional<WishList> loadWishList(String name, User user) throws Exception {
    try {
      List<WishList> wishLists = loadJsonWishLists();

      for (WishList w : wishLists) {
        if (user.equals(w.getOwner()) && w.getName().equals(name)) {
          return Optional.of(w);
        }
      }
      return Optional.empty();

    } catch (IOException e) {
      throw new Exception(e);
    }
  }

  /**
   * Load wish from given file in "wishLists" in the this' path.
   *
   * @param wishList wishList to compare
   * @param name name of wish
   * @return wish if it exists
   * @throws Exception file not found
   */
  Optional<Wish> loadWish(WishList wishList, String name) throws Exception {

    try {
      List<WishList> wishLists = this.loadJsonWishLists();

      for (WishList w : wishLists) {
        if (w.getOwner().getEmail().equals(wishList.getOwner().getEmail())
            && w.getName().equals(wishList.getName())) {
          return w.getWish(name);
        }
      }
      return Optional.empty();

    } catch (IOException e) {
      throw new Exception(e);
    }
  }
}
