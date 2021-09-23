import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class MainViewController extends AbstractController {

  @Override @FXML
  public void initialize() {
    System.out.println("User passed! " + this.user);
  }

  @Override
  public void changeToMainView(ActionEvent event) throws IOException {

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
