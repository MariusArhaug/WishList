package wishList.restapi;

import org.springframework.stereotype.Service;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;
import wishList.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/** WishList Service. * */
@Service
public class WishListService {
  private static final String RESOURCES_PATH =
      Paths.get(new File("").getAbsolutePath(), "src", "main", "resources", "wishList", "users")
          .toString();
  private static final JsonHandler jsonHandler = new JsonHandler(RESOURCES_PATH);

  public static List<User> getUsers() throws IOException {
    return new ArrayList<>(jsonHandler.getUsers());
  }

  public static List<WishList> getWishLists() throws IOException {
    return getUsers().stream()
        .flatMap(u -> u.getOwnedWishLists().stream())
        .collect(Collectors.toList());
  }

  public static Optional<User> findUser(String email) throws IOException {
    User user = Utils.findFirstOrNull(getUsers(), e -> e.getEmail().equals(email));
    if (user == null) {
      return Optional.empty();
    }
    return Optional.of(user);
  }

  public static Optional<User> findUser(String email, String password) throws IOException {
    return jsonHandler.loadUser(email, password);
  }

  public static User createUser(String firstName, String lastName, String email, String password)
      throws IOException {
    return jsonHandler.addUser(new User(firstName, lastName, email, password));
  }

  public static User createWishList(WishList wishList, User owner) throws IOException {
    return jsonHandler.makeWishList(wishList.getName(), owner);
  }

  public static User removeWishList(String wishListName, User owner) throws IOException {
    return jsonHandler.removeWishList(wishListName, owner);
  }

  public static User addContact(String newContactEmail, User user)
      throws IOException, IllegalArgumentException {
    return jsonHandler.addContact(newContactEmail, user);
  }

  public static User removeContact(String removeEmail, User user) throws IOException {
    return jsonHandler.removeContact(removeEmail, user);
  }

  public static User addWish(String wishListName, WishList wishList, User user) throws IOException {
    return jsonHandler.addWish(wishListName, wishList, user);
  }

  public static User removeWish(String wishName, WishList wishList, User user) throws IOException {
    return jsonHandler.removeWish(wishName, wishList, user);
  }

  public static User shareWishList(User user, WishList wishList, List<User> group) throws IOException {
    return jsonHandler.shareWishList(user, wishList, group);
  }
}
