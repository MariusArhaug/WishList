package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/** Controller for RegisterView fxml file. */
public class RegisterViewController extends AbstractController {
  @FXML protected TextField firstNameSignUp;
  @FXML protected TextField lastNameSignUp;
  @FXML protected TextField emailSignUp;
  @FXML protected PasswordField passwordSignUp;
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
      errorMessage.setText("You need to fill out every field!");
      return;
    }

    try {
      this.user = this.httpController.adduser(firstName, lastName, email, password);
      changeToMainView(event);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
