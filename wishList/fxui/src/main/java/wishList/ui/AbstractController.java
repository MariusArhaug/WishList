package wishList.ui;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import wishList.core.User;
import wishList.utils.Utils;

/** Abstract controller with changeScene method that all other controllers inherits. */
public abstract class AbstractController {

  User user;
  @FXML protected Label errorMessage;
  @FXML private Button signOut;

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
    Scene newScene = new Scene(newParent);
    Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();

    AbstractController newController = loader.getController();
    this.user = user;
    newController.updateUser(this.user);
    newController.initialize();
    if (currentWindow != null) {
      currentWindow.setScene(newScene);
      currentWindow.show();
    }
  }

  void initialize() {

  }

  /**
   * Change scene to MainView.fxml
   *
   * @param event gets state
   * @throws IOException if the file is not found
   */
  public void changeToMainView(ActionEvent event) throws IOException {
    if (this instanceof LoginViewController) {
      if (((LoginViewController) this).checkUser()) {
        this.changeScene("MainView.fxml", event, this.user);
      } else {
        errorMessage.setText("E-mail or password is incorrect");
      }
    }
    else {
      this.changeScene("MainView.fxml", event, this.user);
    }
  }

  /**
   * Change scene to RegisterView.fxml
   *
   * @param event gets state
   * @throws IOException if the file is not found
   */
  public void changeToRegisterView(ActionEvent event) throws IOException {
    this.changeScene("RegisterView.fxml", event, this.user);
  }

  /**
   * Change scene to LoginView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event, this.user);

  }

  /**
   * Change scene to ShowListView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToShowListView(ActionEvent event) throws IOException {
    this.changeScene("ShowListView.fxml", event, this.user);
  }

  /**
   * Change scene to Covered.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToCovered(ActionEvent event) throws IOException {
    this.changeScene("Covered.fxml", event, this.user);
  }

  /**
   * Change scene to FriendsView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToFriendsView(ActionEvent event) throws IOException {
    this.changeScene("FriendsView.fxml", event, this.user);
  }

  /**
   * Change scene to FriendsWishesView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToFriendsWishesView(ActionEvent event) throws IOException {
    this.changeScene("FriendsWishesView.fxml", event, this.user);
  }

  /**
   * Change scene to FriendsWishListsView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToFriendsWishListsView(ActionEvent event) throws IOException {
    this.changeScene("FriendsWishListsView.fxml", event, this.user);
  }

  /**
   * Change scene to GroupsView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToGroupsView(ActionEvent event) throws IOException {
    this.changeScene("GroupsView.fxml", event, this.user);
  }

  /**
   * Change scene to MakeNewGroupView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToMakeNewGroupView(ActionEvent event) throws IOException {
    this.changeScene("MakeNewGroupView.fxml", event, this.user);
  }

  /**
   * Change scene to NotCovered.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToNotCovered(ActionEvent event) throws IOException {
    this.changeScene("NotCovered.fxml", event, this.user);
  }

  /**
   * Change scene to ShareWithGroupView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToShareWithGroupView(ActionEvent event) throws IOException {
    this.changeScene("ShareWithGroupView.fxml", event, this.user);
  }

  /** Close app. */
  public void signOut(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event, this.user);
    this.user = null;
    /*Stage stage = (Stage) signOut.getScene().getWindow();
    stage.close();*/
  }
}
