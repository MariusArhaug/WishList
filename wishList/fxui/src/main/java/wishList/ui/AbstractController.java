package wishList.ui;

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
import wishList.core.WishList;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

/** Abstract controller with changeScene method that all other controllers inherits. */
public abstract class AbstractController {

  final String resourcesPath =
      Paths.get(new File("").getAbsolutePath(), "src", "main", "resources", "wishList", "ui")
          .toString();
  @FXML protected Label errorMessage;
  User user;
  WishList wishListToShare;
  @FXML private Button signOut;

  void updateUser(User user) {
    this.user = user;
  }

  private void changeScene(String fileName, ActionEvent event, User user, WishList wishList)
      throws IOException {
    Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
    this.changeSceneCore(fileName, user, currentWindow, wishList);
  }

  private void changeScene(String fileName, Object source, User user) throws IOException {
    Stage currentWindow = (Stage) ((Node) source).getScene().getWindow();
    this.changeSceneCore(fileName, user, currentWindow, null);
  }

  private void changeSceneCore(
      String fileName, User user, Stage currentWindow, WishList wishListToShare)
      throws IOException {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(AbstractController.class.getResource("/wishList/ui/" + fileName));
    Parent newParent = loader.load();
    Scene newScene = new Scene(newParent);

    AbstractController newController = loader.getController();
    this.user = user;

    if (wishListToShare != null) {
      newController.wishListToShare = wishListToShare;
    }
    newController.updateUser(this.user);
    newController.initialize();
    if (currentWindow != null) {
      currentWindow.setScene(newScene);
      currentWindow.show();
    }
  }

  void initialize() {}

  /**
   * Change scene to MainView.fxml
   *
   * @param event gets state
   * @throws IOException if the file is not found
   */
  public void changeToMainView(ActionEvent event) throws IOException {
    if (this instanceof LoginViewController) {
      if (((LoginViewController) this).checkUser()) {
        this.changeScene("MainView.fxml", event, this.user, this.wishListToShare);
      } else {
        errorMessage.setText("E-mail or password is incorrect");
      }
    } else {
      this.changeScene("MainView.fxml", event, this.user, wishListToShare);
    }
  }

  void changeToMainView(Object source) throws IOException {
    if (this instanceof LoginViewController) {
      if (((LoginViewController) this).checkUser()) {
        this.changeScene("MainView.fxml", source, this.user);
      } else {
        errorMessage.setText("E-mail or password is incorrect");
      }
    } else {
      this.changeScene("MainView.fxml", source, this.user);
    }
  }

  /**
   * Change scene to RegisterView.fxml
   *
   * @param event gets state
   * @throws IOException if the file is not found
   */
  public void changeToRegisterView(ActionEvent event) throws IOException {
    this.changeScene("RegisterView.fxml", event, this.user, this.wishListToShare);
  }

  /**
   * Change scene to LoginView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToLoginView(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event, this.user, this.wishListToShare);
  }

  /**
   * Change scene to ShowListView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToShowListView(ActionEvent event) throws IOException {
    this.changeScene("ShowListView.fxml", event, this.user, this.wishListToShare);
  }

  /**
   * Change scene to ShowListView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  void changeToShowListView(ActionEvent event, String wishListName) throws IOException {
    this.changeScene("ShowListView.fxml", event, this.user, user.getWishList(wishListName).get());
  }

  /**
   * Change scene to FriendsView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToFriendsView(ActionEvent event) throws IOException {
    this.changeScene("FriendsView.fxml", event, this.user, this.wishListToShare);
  }

  /**
   * Change scene to FriendsWishesView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  void changeToFriendsWishesView(ActionEvent event, WishList wishList) throws IOException {
    this.changeScene("FriendsWishesView.fxml", event, this.user, wishList);
  }

  /**
   * Change scene to FriendsWishListsView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToFriendsWishListsView(ActionEvent event) throws IOException {
    this.changeScene("FriendsWishListsView.fxml", event, this.user, this.wishListToShare);
  }

  /**
   * Change scene to GroupsView.fxml
   *
   * @param event gets state
   * @throws IOException if file is not found
   */
  public void changeToGroupsView(ActionEvent event) throws IOException {
    this.changeScene("GroupsView.fxml", event, this.user, this.wishListToShare);
  }

  /** Close app. */
  public void signOut(ActionEvent event) throws IOException {
    this.changeScene("LoginView.fxml", event, null, this.wishListToShare);
  }
}
