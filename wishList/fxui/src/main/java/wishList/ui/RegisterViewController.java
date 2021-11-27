package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import wishList.core.User;
import wishList.json.JsonHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Controller for RegisterView fxml file. */
public class RegisterViewController extends AbstractController {
  @FXML protected TextField firstNameSignUp;
  @FXML protected TextField lastNameSignUp;
  @FXML protected TextField emailSignUp;
  @FXML protected PasswordField passwordSignUp;
  @FXML protected Label registerFeedback;
  @FXML protected AnchorPane registerPane;

  /**
   * Sign up user.
   *
   * @param event gets state
   */
  @FXML
  public void registerUser(ActionEvent event) {
    try {
      this.addUser(event);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addUser(ActionEvent event) throws Exception {
    String firstName = firstNameSignUp.getText();
    String lastName = lastNameSignUp.getText();
    String email = emailSignUp.getText();
    String password = passwordSignUp.getText();

    if (firstName.isBlank() || lastName.isBlank() || email.isBlank() || password.isBlank()) {
      registerFeedback.setText("You need to fill out every field!");
      return;
    }

    Pattern emailPattern =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    Matcher emailMatcher = emailPattern.matcher(email);
    boolean validEmail = emailMatcher.find();
    if (!validEmail) {
      registerFeedback.setText("This is not a valid email address!");
      return;
    }

    if (password.length() < 8) {
      registerFeedback.setText("The password must be at least eight characters!");
      return;
    }

    try {
      List<User> users = httpController.getUsers();
      List<String> emails = new ArrayList<>();
      for(User u : users){
        emails.add(u.getEmail());
      }
      if(!emails.contains(email)){
        this.user = this.httpController.adduser(firstName, lastName, email, password);
        changeToMainView(event);
      }
      this.registerFeedback.setText("A user with this email already exists!");
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
