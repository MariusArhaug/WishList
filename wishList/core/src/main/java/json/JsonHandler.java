package json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;
import core.Wish;
import core.WishList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class JsonHandler {

  private final ObjectMapper mapper;
  private final String path;

  public JsonHandler() {
    this.mapper = new ObjectMapper();
    this.mapper.registerModule(new JsonModule());
    this.path = Paths.get("").toAbsolutePath() + "/src/main/resources/";
  }

  /**
   * Convert string to file name + path
   * @return file object
   */

  private File toFile(String object) {
    if (object.equals("wishLists") || object.equals("wishes") || object.equals("users")) {
      return new File(this.path + "" + object + ".json");
    }
    return null;
  }

  /**
   * Load all users from users.json
   * @return List of users
   * @throws IOException if not able to find file
   */

  private List<User> loadJsonUserList() throws IOException {
    return mapper.readValue(toFile("users"), new TypeReference<List<User>>(){});
  }
  private WishList[] loadJsonWishListList() throws IOException {
    return mapper.readValue(toFile("wishLists"), new TypeReference<WishList[]>(){});
  }
  private Wish[] loadJsonWishList() throws IOException {
    return mapper.readValue(toFile("wishes"), new TypeReference<Wish[]>(){});
  }

  /**
   * Add user to users.json if it has an unique email
   * @param firstname firstname
   * @param lastname lastname
   * @param email email
   * @param password password
   * @return user created
   * @throws IllegalArgumentException if email is not unique
   * @throws Exception if not found file
   */

  //Trenger vi heller array her? og lager den ikke brukeren selv om passord og eller mail er brukt??
  public User addUser(String firstname, String lastname, String email, String password) throws IllegalArgumentException, Exception {
    try {
      List<User> users = loadJsonUserList();
      System.out.println(users);



      for (User user : users) {
        if (user.getEmail().equals(email)) {
          System.out.println(
              "A user with this email already exists, please try another one"
          );
        }
      }
      User newUser = new User(firstname, lastname, email, password);

      users.add(newUser);

      mapper.writeValue(new File(Paths.get("").toAbsolutePath() + "/src/main/resources/users.json"), users);
      return newUser;
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public WishList addWishList(String name, User user) throws Exception {
    try {
      WishList[] wishLists = loadJsonWishListList();
      List<WishList> ownedWishLists = user.getWishLists();
      for (WishList w : ownedWishLists) {
        if (w.getName().equals(name)) {
          System.out.println(
                  "This user already has a wish list with this name!"
          );
        }
      }

      WishList newWishList = new WishList(name);

      ArrayList<WishList> wishListsList = new ArrayList<>(Arrays.asList(wishLists));

      wishListsList.add(newWishList);

      mapper.writeValue(this.toFile("wishLists"), wishListsList);
      return newWishList;
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

  public Wish addWish(String name, WishList wishList) throws Exception {
    try {
      Wish[] wishes = loadJsonWishList();
      List<Wish> ownedWishes = wishList.getWishes();
      for (Wish w : ownedWishes) {
        if (w.getName().equals(name)) {
          System.out.println(
                  "This wish list already has a wish with this name!"
          );
        }
      }

      Wish newWish = new Wish(name);

      ArrayList<Wish> wishesList = new ArrayList<>(Arrays.asList(wishes));

      wishesList.add(newWish);

      mapper.writeValue(this.toFile("wishes"), wishesList);
      return newWish;
    } catch (Exception e) {
      throw new Exception(e);
    }
  }

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
  public Optional<WishList> loadWishList(User user, String name) throws Exception {

    try {
      List<User> users = loadJsonUserList();

      for (User u : users) {
        if (u == user) {
          return Optional.of(u.getWishList(name));
        }
      }
      return Optional.empty();

    } catch (IOException e) {
      throw new Exception(e);
    }
  }
  public Optional<Wish> loadWish(WishList wishList, String name) throws Exception {

    try {
      WishList[] wishLists = loadJsonWishListList();

      for (WishList w : wishLists) {
        if (w == wishList) {
          return Optional.of(w.getWish(name));
        }
      }
      return Optional.empty();

    } catch (IOException e) {
      throw new Exception(e);
    }
  }
}
