package wishList.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wishList.core.WishList;
import wishList.json.JsonHandler;
import wishList.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** controller for main view. */
public class MainViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected Button signOut;
  @FXML protected Label newWishListFeedback;
  @FXML protected Button addNewList;
  @FXML protected Button removeWishList;
  @FXML protected Button enterWishList;
  @FXML protected TextField addNewListField;
  @FXML protected ListView<String> list;
  @FXML private Label mainNameOfUser;

  public MainViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  /** Initialize. */
  @Override
  public void initialize() {
    if (this.user != null) {
      mainNameOfUser.setText(this.user.getFirstName());
      this.updateWishListView();
    }
  }

  /**
   * Make new wishlist.
   *
   * @throws Exception file not found exception
   */
  public void makeNewWishList() throws Exception {
    String wishListName = addNewListField.getText();
    if (wishListName.length() == 0) {
      newWishListFeedback.setText("The wish list must have a name!");
      return;
    }

    if (Utils.existInList(user.getOwnedWishLists(), e -> e.getName().equals(wishListName))) {
      newWishListFeedback.setText("You already have a wish list with this name!");
      return;
    }
    addNewListField.clear();
    jsonHandler.makeWishList(wishListName, user);
    newWishListFeedback.setText("New wish list added!");
    this.updateWishListView();
  }

  /**
   * Remove wishList.
   *
   * @throws Exception file not found exception.
   */
  public void removeWishList() throws Exception {
    String wishListName = list.getSelectionModel().getSelectedItem();
    if (wishListName == null) {
      newWishListFeedback.setText("You must choose a wish list to remove!");
      return;
    }
    jsonHandler.removeWishList(wishListName, user);
    newWishListFeedback.setText("The wish list was removed!");

    this.updateWishListView();
  }

  private void updateWishListView() {
    List<String> wishListNames = new ArrayList<>();
    for (WishList w : user.getOwnedWishLists()) {
      wishListNames.add(w.getName());
    }
    list.setItems(FXCollections.observableList(wishListNames));
  }

  /**
   * Enter wishList from input.
   *
   * @param event action event.
   * @throws IOException File not found.
   */
  public void enterWishList(ActionEvent event) throws IOException {
    String wishListName = list.getSelectionModel().getSelectedItem();
    if (wishListName == null) {
      newWishListFeedback.setText("You must choose a wish list to enter!");
    } else {
      this.changeToShowListView(event, wishListName);
    }
  }
}
