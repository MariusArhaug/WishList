package wishList.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wishList.json.JsonHandler;

/** Controller for RegisterView fxml file. */
public class RegisterViewController extends AbstractController {
  @FXML protected TextField firstNameSignUp;
  @FXML protected TextField lastNameSignUp;
  @FXML protected TextField emailSignUp;
  @FXML protected TextField passwordSignUp;

  private JsonHandler jsonHandler;

  public RegisterViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  @Override
  @FXML
  public void initialize() {
    this.jsonHandler = new JsonHandler("");
  }

  /**
   * Change scene to LoginView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  @FXML
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event, this.user);
  }

  /**
   * Change scene to ShowListView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToShowListView(ActionEvent event) throws IOException {
    this.changeScene("ShowListView.fxml", event, this.user);
  }

  /**
   * Change scene to MainView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("MainView.fxml", event, this.user);
  }

  /**
   * Change scene to RegisterView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException {
    this.changeScene("RegisterView.fxml", event, this.user);
  }

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

  private void addUser(ActionEvent event) {
    String firstName = firstNameSignUp.getText();
    String lastName = lastNameSignUp.getText();
    String email = emailSignUp.getText();
    String password = passwordSignUp.getText();

    try {
      this.updateUser(jsonHandler.addUser(firstName, lastName, email, password));

      changeToMainView(event);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
