package wishList.restapi;

import org.springframework.stereotype.Service;
import wishList.core.User;
import wishList.json.JsonHandler;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/** WishList Service. * */
@Service
public class WishListService {
  private static final String PATH =
      Paths.get(new File("").getAbsolutePath(), "src", "main", "resources", "wishList", "users")
          .toString();
  private static final JsonHandler jsonHandler = new JsonHandler(PATH);

  static List<User> getUsers() throws IOException {
    return jsonHandler.getUsers();
  }

  public static Optional<User> findUser(String email, String password) throws Exception {
    return jsonHandler.loadUser(email, password);
  }

  public static User createUser(String firstName, String lastName, String email, String password)
      throws Exception {
    return jsonHandler.addUser(new User(firstName, lastName, email, password));
  }
}
