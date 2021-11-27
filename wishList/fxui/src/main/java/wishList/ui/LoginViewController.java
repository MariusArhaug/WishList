package wishList.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import wishList.core.User;

import java.io.IOException;
import java.util.Optional;

/** Controller for LoginView fxml file. */
public class LoginViewController extends AbstractController {
  @FXML protected TextField loginEmailInput;
  @FXML protected PasswordField loginPasswordInput;
  @FXML protected AnchorPane loginPane;

  @Override
  public void initialize() {
    this.loginPane.setOnKeyPressed(
        event -> {
          if (event.getCode().equals(KeyCode.ENTER)) {
            try {
              this.changeToMainView(event.getSource());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
  }

  /**
   * Checks that user is present.
   *
   * @return true or false if user is present
   */
  boolean checkUser() {
    String email = loginEmailInput.getText();
    String password = loginPasswordInput.getText();
    if (email.isBlank() || password.isBlank()) {
      errorMessage.setText("You must fill out both email and password!");
      return false;
    }
    try {
      Optional<User> tryUser = httpController.getUser(email, password);
      if (tryUser.isPresent()) {
        this.user = tryUser.get();
        return true;
      } else {
        errorMessage.setText("E-mail or password is incorrect");
        return false;
      }
    } catch (Exception e) {
      e.printStackTrace();
      errorMessage.setText("E-mail or password incorrect");
      return false;
    }
  }
}
