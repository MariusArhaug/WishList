import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ShowListViewController extends AbstractController {

  @Override
  public void initialize() {

  }

  /**
   * Change scene to MainView.fxml
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("MainView.fxml", event);
  }

  /**
   * Change scene to RegisterView.fxml
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException {
    this.changeScene("RegisterView.fxml", event);
  }

  /**
   * Change scene to LoginView.fxml
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("LogInView.fxml", event);
  }

  /**
   * Change scene to ShowListView
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToShowListView(ActionEvent event) throws IOException {
    this.changeScene("ShowListView.fxml", event);
  }

}
