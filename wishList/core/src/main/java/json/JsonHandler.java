package json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
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

  private File toFile() {
    return new File(this.path + "users.json");
  }

  /**
   * Load all users from users.json
   * @return List of users
   * @throws IOException if not able to find file
   */

  private List<User> loadJsonUserList() throws IOException {
    return mapper.readValue(toFile(), new TypeReference<List<User>>(){});
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

  public User addUser(String firstname, String lastname, String email, String password) throws IllegalArgumentException, Exception {
    try {
      List<User> users = loadJsonUserList();
      System.out.println(users);



      for (User user : users) {
        if (user.getEmail().equals(email)) {
          throw new IllegalArgumentException(
              "An user with this email already exists, please try another one"
          );
        }
      }
      User newUser = new User(firstname, lastname, email, password);

      users.add(newUser);
      //System.out.println(Paths.get("").toAbsolutePath() + "/src/main/resources/users.json");
      mapper.writeValue(new File(Paths.get("").toAbsolutePath() + "/src/main/resources/users.json"), users);
      return newUser;
    } catch (Exception e) {
      throw new Exception(e);
    }
  }


  public Optional<User> loadUser(String email, String password) {

    try {
      List<User> users = loadJsonUserList();

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
