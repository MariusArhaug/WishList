package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wishList.json.JsonHandler;

/** Controller for RegisterView fxml file. */
public class RegisterViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected TextField firstNameSignUp;
  @FXML protected TextField lastNameSignUp;
  @FXML protected TextField emailSignUp;
  @FXML protected TextField passwordSignUp;

  public RegisterViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  @Override
  @FXML
  public void initialize() {}

  /**
   * Sign up user.
   *
   * @param event gets state
   */
  @FXML
  public void registerUser(ActionEvent event) {
    try {
      this.addUser(event);
      changeToMainView(event);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addUser(ActionEvent event) throws Exception {
    String firstName = firstNameSignUp.getText();
    String lastName = lastNameSignUp.getText();
    String email = emailSignUp.getText();
    String password = passwordSignUp.getText();

    try {
      this.updateUser(jsonHandler.addUser(firstName, lastName, email, password));
      changeToMainView(event);
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    }
  }
}
