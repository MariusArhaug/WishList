package wishList.restapi;

import org.springframework.stereotype.Service;
import wishList.core.User;
import wishList.json.JsonHandler;
import wishList.utils.Utils;

import java.io.File;
import java.util.Optional;

/** WishList Service. * */
@Service
public class WishListService {
  private final JsonHandler jsonHandler;
  private User user;

  WishListService() {
    // Pathen må evt endres
    String resourcesPath =
        Utils.updatePathForAnyOs(
            new File("").getAbsolutePath(), "src", "main", "resources", "wishList", "restapi");
    this.jsonHandler = new JsonHandler(resourcesPath);
  }

  User findUser(String email, String password) throws Exception {
    User setUser;
    Optional<User> user = jsonHandler.loadUser(email, password);
    if (user.isEmpty()) {
      // Burde vi ha feilhåndtering her?
      // throw new IllegalArgumentException("jaa");
      return null;
    } else {
      setUser = user.get();
    }
    this.setUser(setUser);
    return this.user;
  }

  // Skal endres
  User createUser(String firstName, String lastName, String email, String password)
      throws Exception {
    User user = new User(firstName, lastName, email, password);
    jsonHandler.addUser(firstName, lastName, email, password);
    return user;
  }

  public User getUser() {
    return this.user;
  }

  private void setUser(User user) {
    this.user = user;
  }

  public JsonHandler getJsonHandler() {
    return jsonHandler;
  }

  /*
  @GetMapping
  public User deserializeUser(){
      //Du kan bruke loadUser her kanskje
      //Her må du somehow få tak i email og passord
      User user = jsonHandler.loadUser();
      return null;
  } */

  void createWishList(String listName) {}
}
