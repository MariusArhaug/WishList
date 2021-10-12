package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import wishList.json.JsonHandler;

import java.io.IOException;

public class RegisterViewController extends AbstractController {
  @FXML private TextField firstNameSignUp;
  @FXML private TextField lastNameSignUp;
  @FXML private TextField emailSignUp;
  @FXML private TextField passwordSignUp;

  private JsonHandler jsonHandler;

  @Override
  @FXML
  public void initialize() {
    this.jsonHandler = new JsonHandler();
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
    this.changeScene("/wishList/ui/LoginView.fxml", event, this.user);
  }

  /**
   * Change scene to ShowListView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToShowListView(ActionEvent event) throws IOException {
    this.changeScene("/wishList/ui/ShowListView.fxml", event, this.user);
  }

  /**
   * Change scene to MainView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("/wishList/ui/MainView.fxml", event, this.user);
  }

  /**
   * Change scene to RegisterView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException{
    this.changeScene("/wishList/ui/RegisterView.fxml", event, this.user);
  }

  /**
   * Sign up user
   *
   * @param event gets state
   */
  @FXML
  public void registerUser(ActionEvent event) {
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
