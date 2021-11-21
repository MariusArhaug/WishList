package wishList.ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import wishList.core.User;
import wishList.json.JsonHandler;

import java.io.IOException;
import java.util.Optional;

/** Controller for LoginView fxml file. */
public class LoginViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected TextField loginEmailInput;
  @FXML protected PasswordField loginPasswordInput;
  @FXML protected AnchorPane loginPane;

  public LoginViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  @Override
  public void initialize() {
    this.loginPane.setOnKeyPressed(
        event -> {
          if (event.getCode().equals(KeyCode.ENTER)) {
            try {
              System.out.println(this.resourcesPath);
              this.changeToMainView(event.getSource());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
  }

  @Override
  public void stop() {}

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
