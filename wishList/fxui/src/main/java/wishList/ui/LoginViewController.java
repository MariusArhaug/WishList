package wishList.ui;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import wishList.core.User;
import wishList.json.JsonHandler;

/** Controller for LoginView fxml file. */
public class LoginViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected TextField loginEmailInput;
  @FXML protected TextField loginPasswordInput;

  public LoginViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  public void initialize() {}

  /**
   * Checks that user is present.
   *
   * @return true or false if user is present
   */
  boolean checkUser() {
    String email = loginEmailInput.getText();
    String password = loginPasswordInput.getText();

    try {
      Optional<User> tryUser = jsonHandler.loadUser(email, password);

      if (tryUser.isPresent()) {
        this.user = tryUser.get();
        return true;
      } else {
        errorMessage.setText("E-mail or password is incorrect");
        return false;
      }
    } catch (Exception e) {
      errorMessage.setText("E-mail or password incorrect");
      return false;
    }
  }

}
