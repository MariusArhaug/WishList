package wishList.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wishList.core.User;

/** Abstract controller with changeScene method that all other controllers inherits. */
public abstract class AbstractController implements ViewChanger {

  protected User user;

  void updateUser(User user) {
    this.user = user;
  }

  protected void changeScene(String fileName, ActionEvent event, User user) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(AbstractController.class.getResource(fileName));
    Parent newParent = loader.load();
    AbstractController newController = loader.getController();
    this.user = user;
    newController.updateUser(this.user);
    newController.initialize();

    Scene newScene = new Scene(newParent);
    Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    currentWindow.setScene(newScene);
    currentWindow.show();
  }
}
