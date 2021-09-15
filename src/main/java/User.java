import java.util.ArrayList;
import java.util.List;

public class User {

  private final String firstName;
  private final String lastName;
  private final String email;

  private final List<WishList> wishLists = new ArrayList<>();

  public User(String firstName, String lastName, String email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }
}
