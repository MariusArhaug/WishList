import core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import json.JsonHandler;

import java.io.IOException;

public class RegisterViewController extends AbstractController {
  @FXML private TextField firstNameSignUp;
  @FXML private TextField lastNameSignUp;
  @FXML private TextField emailSignUp;
  @FXML private TextField passwordSignUp;

  private JsonHandler jsonHandler;

  @FXML
  public void initialize() {
    this.jsonHandler = new JsonHandler();
  }



  @FXML
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event);
  }

  @Override
  public void changeToCreateListView(ActionEvent event) throws IOException {

  }

  @Override
  public void changeToShowListView(ActionEvent event) throws IOException {

  }


  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("MainView.fxml", event);
  }

  @Override
  public void changeToRegisterView(ActionEvent event) {

  }

  /**
   * Sign up user
   */
  @FXML
  public void registerUser(ActionEvent event) {
    String firstName = firstNameSignUp.getText();
    String lastName = lastNameSignUp.getText();
    String email = emailSignUp.getText();
    String password = passwordSignUp.getText();

    try {
      this.updateUser(
          jsonHandler.addUser(firstName, lastName, email, password)
      );


      changeToMainView(event);
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

}
