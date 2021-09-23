import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ShowListViewController extends AbstractController {
  public ListView chosenList;
  public TextField addItemField;

  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("MainView.fxml", event);
  }

  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException {

  }

  @Override
  public void changeToLoginView(ActionEvent event) throws IOException {

  }

  public void addNewItem(ActionEvent event) {
  }
}
