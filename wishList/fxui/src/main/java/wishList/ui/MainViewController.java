package wishList.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wishList.core.WishList;
import wishList.json.JsonHandler;

/** controller for main view. */
public class MainViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML private TextField wishListNameField;
  @FXML private Button signOut;
  @FXML private Label mainNameOfUser;
  @FXML private ListView wishListList;

  public MainViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  @Override
  public void initialize() {
    if (this.user != null) {
      mainNameOfUser.setText(this.user.getFirstName());
    }
  }

  @FXML
  public void createNewWishList() {
    String wishListName = wishListNameField.getText();
    if (this.user.wishListsExist(wishListName)) {
      // set error
      return;
    }
    this.user.addWishList(new WishList(wishListName));
    this.updateWishListsUI();
  }

  @FXML
  private void updateWishListsUI() {
    wishListList.getItems().removeAll();
    for (WishList wishList : this.user) {
      wishListList.getItems().add(new Label(wishList.getName()));
    }
  }
}
