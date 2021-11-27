package wishList.restapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonModule;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** REST controller. * */
@RestController
@RequestMapping(RESTController.wishListPath)
public class RESTController {

  static final String wishListPath = "wishList/api/v1";
  private final ObjectMapper objectMapper = new ObjectMapper();

  RESTController() {
    objectMapper.registerModule(new JsonModule());
  }

  @GetMapping("/users/")
  @ResponseBody()
  public List<User> getUsers() throws IOException {
    return WishListService.getUsers();
  }

  @GetMapping("/wishLists/")
  @ResponseBody()
  public List<WishList> getWishLists() throws IOException {
    return WishListService.getWishLists();
  }

  @GetMapping("/user/{email}/")
  @ResponseBody()
  public Optional<User> getUser(@PathVariable String email) throws IOException {
    return WishListService.findUser(email);
  }

  @GetMapping("/user/{email}/{password}/")
  @ResponseBody()
  public Optional<User> loginUser(@PathVariable String email, @PathVariable String password)
      throws Exception {
    return WishListService.findUser(email, password);
  }

  /**
   * Create user from HTTP request.
   *
   * @param user user to be created in json file
   * @return user object
   * @throws IOException file not found exception.
   */
  @PostMapping(value = "/user/add/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User createUser(@RequestBody User user) throws IOException {
    return WishListService.createUser(
        user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
  }

  /**
   * Add contact.
   *
   * @param requestBody body with new contact and user to update
   * @return updated user
   * @throws IOException file not found
   */
  @PostMapping(value = "/user/contact/add/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User addContact(@RequestBody Map<String, String> requestBody) throws IOException {
    if (requestBody.get("newContact").isBlank() || requestBody.get("user").isBlank()) {
      throw new IllegalArgumentException(
          "Request body is missing one of these parameters: newContact, user");
    }
    return WishListService.addContact(
        requestBody.get("newContact"), objectMapper.readValue(requestBody.get("user"), User.class));
  }

  /**
   * Remove contact.
   *
   * @param requestBody body with contact to be removed and user to update.
   * @return updated user
   * @throws IOException file not found.
   */
  @PostMapping(value = "/user/contact/remove/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User removeContact(@RequestBody Map<String, String> requestBody) throws IOException {
    if (requestBody.get("removeEmail").isBlank() || requestBody.get("user").isBlank()) {
      throw new IllegalArgumentException(
          "Request body is missing one of these parameters: removeEmail, user");
    }
    return WishListService.removeContact(
        requestBody.get("removeEmail"),
        objectMapper.readValue(requestBody.get("user"), User.class));
  }

  /**
   * Add wish.
   *
   * @param requestBody body with wishName, user and wishList
   * @return updated user
   * @throws IOException file not found
   */
  @PostMapping(value = "/user/wish/add/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User addWish(@RequestBody Map<String, String> requestBody) throws IOException {
    if (requestBody.get("wishName").isBlank()
        || requestBody.get("user").isBlank()
        || requestBody.get("wishList").isBlank()) {
      throw new IllegalArgumentException(
          "Request body is missing one of these parameters: wishName, user, wishList");
    }
    return WishListService.addWish(
        requestBody.get("wishName"),
        objectMapper.readValue(requestBody.get("wishList"), WishList.class),
        objectMapper.readValue(requestBody.get("user"), User.class));
  }

  /**
   * Remove wish.
   *
   * @param requestBody body with wishName, user and wishList
   * @return updated user
   * @throws IOException file not found.
   */
  @PostMapping(value = "/user/wish/remove/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User removeWish(@RequestBody Map<String, String> requestBody) throws IOException {
    if (requestBody.get("wishName").isBlank()
        || requestBody.get("user").isBlank()
        || requestBody.get("wishList").isBlank()) {
      throw new IllegalArgumentException(
          "Request body is missing one of these parameters: wishName, user, wishList");
    }
    return WishListService.removeWish(
        requestBody.get("wishName"),
        objectMapper.readValue(requestBody.get("wishList"), WishList.class),
        objectMapper.readValue(requestBody.get("user"), User.class));
  }

  /**
   * Add wishList to user.
   *
   * @param requestBody body with user and wishListName
   * @return updated user
   * @throws IOException file not found exception.
   */
  @PostMapping(value = "/user/wishList/add/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User addWishList(@RequestBody Map<String, String> requestBody)
      throws IOException, IllegalArgumentException {
    if (requestBody.get("name").isBlank() || requestBody.get("user").isBlank()) {
      throw new IllegalArgumentException("You cannot create wishlist without owner");
    }
    return WishListService.createWishList(
        new WishList(requestBody.get("name")),
        objectMapper.readValue(requestBody.get("user"), User.class));
  }

  /**
   * Add wishList to user.
   *
   * @param requestBody body with user and wishListName
   * @return updated user
   * @throws IOException file not found exception.
   */
  @PostMapping(value = "/user/wishList/remove/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User removeWishList(@RequestBody Map<String, String> requestBody)
      throws IOException, IllegalArgumentException {
    if (requestBody.get("name").isBlank() || requestBody.get("user").isBlank()) {
      throw new IllegalArgumentException(
          "Request body is missing one of these parameters: name, user");
    }
    return WishListService.removeWishList(
        requestBody.get("name"), objectMapper.readValue(requestBody.get("user"), User.class));
  }

  /**
   * Add wishList to user.
   *
   * @param requestBody body with user and wishListName
   * @return updated user
   * @throws IOException file not found exception.
   */
  @PostMapping(value = "/user/wishList/share/", headers = "Content-Type=application/json")
  @ResponseBody()
  public User shareWishList(@RequestBody Map<String, String> requestBody)
      throws IOException, IllegalArgumentException {
    if (requestBody.get("user").isBlank()
        || requestBody.get("wishList").isBlank()
        || requestBody.get("group").isBlank()) {
      throw new IllegalArgumentException(
          "Request body is missing one of these parameters: user, wishList, group");
    }
    return WishListService.shareWishList(
        objectMapper.readValue(requestBody.get("user"), User.class),
        objectMapper.readValue(requestBody.get("wishList"), WishList.class),
        objectMapper.readValue(requestBody.get("group"), new TypeReference<>() {}));
  }
}
