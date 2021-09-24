import core.User;
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

  protected User getUser() {
    return this.user;
  }

  protected void changeScene(String fileName, ActionEvent event) throws IOException  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource(fileName));
    //Parent newParent = FXMLLoader.load(getClass().getResource(fileName));
    Parent newParent = loader.load();

    AbstractController newController = loader.getController();
    newController.updateUser(this.getUser());


    Scene newScene = new Scene(newParent);
    Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    currentWindow.setScene(newScene);
    currentWindow.show();
  }
}
