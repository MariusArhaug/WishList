package wishList.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.File;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
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
  private List<User> loadJsonUserList() throws IOException {
    return mapper.readValue(toFile("users.json"), new TypeReference<List<User>>() {});
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
      System.out.println(toFile("users.json"));
      users.add(newUser);
      mapper.writeValue(toFile("users.json"), users);
      return newUser;
    } catch (IOException e) {
      throw new Exception(e);
    }
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
      System.out.println(wishLists);

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
