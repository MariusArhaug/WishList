package wishList.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;

/** controller for main view. */
public class MainViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected Button signOut;
  @FXML private Label mainNameOfUser;
  @FXML protected Label newWishListFeedback;
  @FXML protected Button addNewList;
  @FXML protected Button removeWishList;
  @FXML protected Button enterWishList;
  @FXML protected TextField addNewListField;
  @FXML protected ListView<String> list;

  public MainViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }


  public void initialize() {
    if (this.user != null) {
      mainNameOfUser.setText(this.user.getFirstName());
      this.updateWishListView();
    }
  }

  public void makeNewWishList() throws Exception {
    String wishListName = addNewListField.getText();
    if (wishListName.length() == 0) {
      newWishListFeedback.setText("The wish list must have a name!");
    } else {
      List<WishList> ownedWishLists = new ArrayList<>();
      Iterator<WishList> iteratorWishLists = user.iterator();
      iteratorWishLists.forEachRemaining(ownedWishLists::add);
      WishList alreadyWishList = ownedWishLists.stream().filter(e -> e.getName().equals(wishListName)).findAny().orElse(null);
      if (alreadyWishList == null) {
        addNewListField.clear();
        jsonHandler.makeWishList(wishListName, user);
        newWishListFeedback.setText("New wish list added!");

      } else {
        newWishListFeedback.setText("You already have a wish list with this name!");
      }
    }
    this.updateWishListView();
  }

  public void removeWishList() throws Exception {
    String wishListName = list.getSelectionModel().getSelectedItem();
    if (wishListName == null) {
      newWishListFeedback.setText("You must choose a wish list to remove!");
    } else {
      jsonHandler.removeWishList(wishListName, user);
      newWishListFeedback.setText("The wish list was removed!");
    }
    this.updateWishListView();
  }

  private void updateWishListView() {
    List<WishList> ownedWishLists = new ArrayList<>();
    Iterator<WishList> iteratorWishLists = user.iterator();
    iteratorWishLists.forEachRemaining(ownedWishLists::add);
    List<String> wishListNames = new ArrayList<>();
    for (WishList w : ownedWishLists) {
      wishListNames.add(w.getName());
    }
    list.setItems(FXCollections.observableList(wishListNames));
  }

  public void enterWishList(ActionEvent event) throws IOException {
    String wishListName = list.getSelectionModel().getSelectedItem();
    if (wishListName == null) {
      newWishListFeedback.setText("You must choose a wish list to enter!");
    } else {
      this.changeToShowListView(event, wishListName);
    }
  }


}
