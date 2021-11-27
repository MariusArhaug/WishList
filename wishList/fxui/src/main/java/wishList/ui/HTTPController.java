package wishList.ui;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonModule;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Control HTTP requests. * */
class HTTPController {
  private static final String ENDPOINT_URI = "http://localhost:8080/wishList/api/v1";
  private final ObjectMapper objectMapper = new ObjectMapper();
  private final HttpClient client = HttpClient.newHttpClient();

  HTTPController() {
    this.objectMapper.registerModule(new JsonModule());
  }

  private static URI getEndpointUri(String endpoint) {
    return URI.create(ENDPOINT_URI + endpoint);
  }

  private static HttpRequest POST_REQUEST(String endpoint, String requestBodyString) {
    return HttpRequest.newBuilder()
        .uri(getEndpointUri(endpoint))
        .header("Accept", "application/json")
        .header("Content-Type", "application/json")
        .POST(HttpRequest.BodyPublishers.ofString(requestBodyString))
        .build();
  }

  @SuppressWarnings("checkstyle:AbbreviationAsWordInName")
  private static HttpRequest GET_REQUEST(String endpoint) {
    return HttpRequest.newBuilder()
        .uri(getEndpointUri(endpoint))
        .header("Accept", "application/json")
        .GET()
        .build();
  }

  private User handleResponse(HttpResponse<String> response) throws JsonProcessingException {
    if (response.statusCode() >= 400) {
      throw new Error("Something went wrong!");
    }

    if(objectMapper.readValue(response.body(), User.class) == null){
      return null;
    }
    return objectMapper.readValue(response.body(), User.class);
  }

  /**
   * get users.
   *
   * @return list of all users
   * @throws IOException file not found
   * @throws InterruptedException json error
   */
  List<User> getUsers() throws IOException, InterruptedException {
    HttpRequest request = GET_REQUEST("/users/");
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return objectMapper.readValue(response.body(), new TypeReference<List<User>>() {});
  }

  /**
   * Get user based on email.
   *
   * @param email email for user.
   * @return optional user if user with email exist
   * @throws IOException file not found
   * @throws InterruptedException json error
   */
  Optional<User> getUser(String email) throws IOException, InterruptedException {
    HttpRequest request = GET_REQUEST("/user/" + email + "/");

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response) != null
        ? Optional.of(handleResponse(response))
        : Optional.empty();
  }

  /**
   * Get user from REST API.
   *
   * @param email email of user
   * @param password password of user
   * @return Optional user
   */
  Optional<User> getUser(String email, String password) throws IOException, InterruptedException {
    HttpRequest request = GET_REQUEST("/user/" + email + "/" + password + "/");

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return Optional.ofNullable(handleResponse(response));
  }

  /**
   * Add user to persistance.
   *
   * @param firstname fistname
   * @param lastName lastname
   * @param email email
   * @param password password
   * @return created user
   * @throws IOException file not found error.
   * @throws InterruptedException json error
   */
  User adduser(String firstname, String lastName, String email, String password)
      throws IOException, InterruptedException {
    String requestBodyString =
        objectMapper.writeValueAsString(new User(firstname, lastName, email, password));
    HttpRequest request = POST_REQUEST("/user/add/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return handleResponse(response);
  }

  /**
   * add contact to user.
   *
   * @param newContactEmail new contact email
   * @param user current user
   * @return updated current user
   * @throws IOException file not found
   * @throws InterruptedException json error
   */
  User addContact(String newContactEmail, User user) throws IOException, InterruptedException {
    Map<String, String> requestBody =
        new HashMap<>() {
          {
            put("newContact", newContactEmail);
            put("user", objectMapper.writeValueAsString(user));
          }
        };
    String requestBodyString = objectMapper.writeValueAsString(requestBody);

    HttpRequest request = POST_REQUEST("/user/contact/add/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return handleResponse(response);
  }

  /**
   * remove contact from user.
   *
   * @param userEmailToBeRemoved email of user to remove
   * @param user user
   * @return updated user
   * @throws IOException file not found
   * @throws InterruptedException json error
   */
  User removeContact(String userEmailToBeRemoved, User user)
      throws IOException, InterruptedException {
    Map<String, String> requestBody =
        new HashMap<>() {
          {
            put("removeEmail", userEmailToBeRemoved);
            put("user", objectMapper.writeValueAsString(user));
          }
        };
    String requestBodyString = objectMapper.writeValueAsString(requestBody);

    HttpRequest request = POST_REQUEST("/user/contact/remove/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response);
  }

  /**
   * @param userToSave user to save
   * @return updated user
   * @throws IOException file not found
   * @throws InterruptedException json error
   */
  public User addUser(User userToSave) throws IOException, InterruptedException {
    return this.adduser(
        userToSave.getFirstName(),
        userToSave.getLastName(),
        userToSave.getEmail(),
        userToSave.getPassword());
  }

  /**
   * @param wishName
   * @param wishList
   * @param user
   * @return
   * @throws IOException
   * @throws InterruptedException
   */
  User addWish(String wishName, WishList wishList, User user)
      throws IOException, InterruptedException {

    String requestBodyString = getUpdateWishBody(wishName, wishList, user);

    HttpRequest request = POST_REQUEST("/user/wish/add/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response);
  }

  /**
   * @param wishName remove wish with name
   * @param wishList wishlist to remove it
   * @param user user's wishlist
   * @return updated user
   * @throws IOException file not found
   * @throws InterruptedException json file error.
   */
  User removeWish(String wishName, WishList wishList, User user)
      throws IOException, InterruptedException {
    String requestBodyString = getUpdateWishBody(wishName, wishList, user);

    HttpRequest request = POST_REQUEST("/user/wish/remove/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response);
  }

  private String getUpdateWishBody(String wishName, WishList wishList, User user)
      throws JsonProcessingException {
    Map<String, String> requestBody =
        new HashMap<>() {
          {
            put("wishName", wishName);
            put("user", objectMapper.writeValueAsString(user));
            put("wishList", objectMapper.writeValueAsString(wishList));
          }
        };
    return objectMapper.writeValueAsString(requestBody);
  }

  /**
   * Add wish list via http request.
   *
   * @param wishListName name of wishList
   * @param user owner of wishList
   * @throws Exception from server.
   */
  User addWishList(String wishListName, User user) throws Exception {
    Map<String, String> requestBody =
        new HashMap<>() {
          {
            put("name", wishListName);
            put("user", objectMapper.writeValueAsString(user));
          }
        };
    String requestBodyString = objectMapper.writeValueAsString(requestBody);
    HttpRequest request = POST_REQUEST("/user/wishList/add/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response);
  }

  /**
   * Remove wishList from user.
   *
   * @param wishListName name of wishList
   * @param user owner
   * @throws Exception from server.
   */
  User removeWishList(String wishListName, User user) throws Exception {
    Map<String, String> requestBody =
        new HashMap<>() {
          {
            put("name", wishListName);
            put("user", objectMapper.writeValueAsString(user));
          }
        };
    String requestBodyString = objectMapper.writeValueAsString(requestBody);

    HttpRequest request = POST_REQUEST("/user/wishList/remove/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response);
  }

  /**
   * @param user share wishlist
   * @param wishList wishlist to be share
   * @param group group to share it to
   * @return updated user
   * @throws Exception error
   */
  User shareWishList(User user, WishList wishList, List<User> group) throws Exception {
    Map<String, String> requestBody =
        new HashMap<>() {
          {
            put("user", objectMapper.writeValueAsString(user));
            put("wishList", objectMapper.writeValueAsString(wishList));
            put("group", objectMapper.writeValueAsString(group));
          }
        };
    String requestBodyString = objectMapper.writeValueAsString(requestBody);

    HttpRequest request = POST_REQUEST("/user/wishList/share/", requestBodyString);

    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return handleResponse(response);
  }
}
