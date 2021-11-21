package wishList.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import wishList.core.WishList;
import wishList.json.JsonHandler;

import java.io.IOException;

/** controller for main view. */
public class MainViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML private TextField wishListNameField;
  @FXML private Button signOut;
  @FXML private Label mainNameOfUser;
  @FXML private ListView<Label> wishListList;

  public MainViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  @Override
  public void initialize() {
    if (this.user != null) {
      mainNameOfUser.setText(this.user.getFirstName());
      this.updateWishListsUi();
    }
  }

  @Override
  public void stop() {
    this.save();
  }

  @FXML
  public void createNewWishList() {
    String wishListName = wishListNameField.getText();
    if (this.user.wishListsExist(wishListName)) {
      // set error
      return;
    }
    this.user.addWishList(new WishList(wishListName));
    this.updateWishListsUi();
  }

  @FXML
  private void updateWishListsUi() {
    wishListList.getItems().removeAll();
    for (WishList wishList : this.user) {
      Label label = new Label(wishList.getName());
      label.setOnMouseClicked(
          mouseEvent -> {
            if (mouseEvent.getButton().equals(MouseButton.PRIMARY)
                && mouseEvent.getClickCount() == 2) {
              try {
                this.changeToWishListView(mouseEvent.getSource());
              } catch (IOException e) {
                e.printStackTrace();
              }
            }
            // TOOD: make it selectable for delete or move delete button to wishList view

          });

      wishListList.getItems().add(label);
    }
  }
}
