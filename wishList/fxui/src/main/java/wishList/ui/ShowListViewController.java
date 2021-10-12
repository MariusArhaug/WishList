package wishList.ui;

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
    this.changeScene("/wishList/ui/MainView.fxml", event, this.user);
  }

  /**
   * Change scene to RegisterView.fxml
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException {
    this.changeScene("/wishList/ui/RegisterView.fxml", event, this.user);
  }

  /**
   * Change scene to LoginView.fxml
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("/wishList/ui/LoginView.fxml", event, this.user);
  }

  /**
   * Change scene to ShowListView
   * @param event gets state
   * @throws IOException if file is not found
   */
  @Override
  public void changeToShowListView(ActionEvent event) throws IOException {
    this.changeScene("/wishList/ui/ShowListView.fxml", event, this.user);
  }

}
