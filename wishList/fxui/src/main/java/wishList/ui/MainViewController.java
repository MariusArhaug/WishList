package wishList.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/** controller for main view. */
public class MainViewController extends AbstractController {
  @FXML private Button signOut;
  @FXML private Label mainNameOfUser;

  @Override
  public void initialize() {
    if (this.user != null) {
      mainNameOfUser.setText(this.user.getFirstName());
    }
  }

  /**
   * Change scene to MainView.fxml
   *
   * @param event gets state
   * @throws IOException if the file is not found
   */
  @Override
  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("MainView.fxml", event, this.user);
  }

  /**
   * Change scene to RegisterView.fxml
   *
   * @param event gets state
   * @throws IOException if the file is not found
   */
  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException {
    this.changeScene("RegisterView.fxml", event, this.user);
  }

  /**
   * Change scene to LoginView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event, this.user);
  }

  // Change scene to ShowListView.fxml

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

  /** Close app. */
  public void closeApp() {
    Stage stage = (Stage) signOut.getScene().getWindow();
    stage.close();
  }
}
