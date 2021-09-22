package json;

import core.User;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class JsonLoader extends JsonHandler {

  private static final String JSON_FILENAME = "users.json";
  private final String COMPLETE_PATH = getClass().getResource(JSON_FILENAME).toString();


  public User addUser(String firstname, String lastname, String email, String password) throws IllegalArgumentException, Exception {
    try  {

      List<User> users = this.loadJsonList(COMPLETE_PATH);
      User newUser = new User(firstname, lastname, email, password);

      for (User user : users) {
        if (user.getEmail().equals(email)) {
          throw new IllegalArgumentException(
               "An user with this email already exists, please try another one"
          );
        }
      }

      users.add(newUser);
      this.mapper.writeValue(new File(COMPLETE_PATH), users);
      return newUser;

    } catch (IOException e) {
      e.printStackTrace();
      throw new Exception("Something went wrong with the file!");
    }
  }

  public Optional<User> loadUser(String email, String password) {
    try {
      List<User> users = this.loadJsonList(COMPLETE_PATH);

      for (User user : users) {
        if (user.checkCredentials(email, password)) {
          return Optional.of(user);
        }
      }
      return Optional.empty();

    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }
}
