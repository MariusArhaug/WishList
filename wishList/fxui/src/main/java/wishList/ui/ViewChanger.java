package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public interface ViewChanger {

  @FXML
  void initialize();
  void changeToMainView(ActionEvent event) throws IOException;
  void changeToRegisterView(ActionEvent event) throws IOException;
  void changeToLoginView(ActionEvent event) throws IOException;
  void changeToShowListView(ActionEvent event) throws IOException;
}
