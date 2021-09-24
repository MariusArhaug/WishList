import javafx.event.ActionEvent;

import java.io.IOException;

public interface ViewChanger {


  void changeToMainView(ActionEvent event) throws IOException;
  void changeToRegisterView(ActionEvent event) throws IOException;
  void changeToLoginView(ActionEvent event) throws IOException;
  void changeToShowListView(ActionEvent event) throws IOException;

}
