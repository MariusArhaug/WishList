import core.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/** Abstract controller with changeScene method that all other controllers inherits. */
public abstract class AbstractController implements ViewChanger {

  private User user;

  void updateUser(User user) {
    this.user = user;
  }

  private User getUser() {
    return this.user;
  }

  void changeScene(String fileName, ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(AbstractController.class.getResource(fileName));
    Parent newParent = loader.load();

    AbstractController newController = loader.getController();
    newController.updateUser(this.getUser());

    Scene newScene = new Scene(newParent);
    Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    currentWindow.setScene(newScene);
    currentWindow.show();
  }
}
