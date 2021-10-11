package wishList.ui;

import wishList.core.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class AbstractController implements ViewChanger {

  protected User user;

  protected void updateUser(User user) {
    this.user = user;
  }

  protected void changeScene(String fileName, ActionEvent event, User user) throws IOException  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(fileName));
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
