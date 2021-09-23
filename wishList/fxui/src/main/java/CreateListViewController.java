import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class CreateListViewController extends AbstractController {
  @FXML private ListView currentList;
  @FXML private TextField newItem;
  @FXML private Button addNewItem;

  @Override @FXML
  public void initialize() {

  }

  public void changeToMainView(ActionEvent event) throws IOException {
    this.changeScene("MainView.fxml", event);
  }

  @Override
  public void changeToRegisterView(ActionEvent event) throws IOException {

  }

  @Override
  public void changeToLoginView(ActionEvent event) throws IOException {

  }

  @Override
  public void changeToCreateListView(ActionEvent event) throws IOException {

  }

  @Override
  public void changeToShowListView(ActionEvent event) throws IOException {

  }

}
