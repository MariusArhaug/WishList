package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import wishList.core.User;
import wishList.utils.Utils;

import java.io.File;
import java.io.IOException;

/** Abstract controller with changeScene method that all other controllers inherits. */
public abstract class AbstractController implements ViewChanger {

  User user;
  public final String resourcesPath =
      Utils.updatePathForAnyOs(
          new File("").getAbsolutePath(), "src", "main", "resources", "wishList", "ui");

  void updateUser(User user) {
    this.user = user;
  }

  void changeScene(String fileName, ActionEvent event, User user) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(AbstractController.class.getResource("/wishList/ui/" + fileName));
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
