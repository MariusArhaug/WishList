package wishList.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wishList.core.Wish;
import wishList.core.WishList;
import wishList.json.JsonHandler;

/**
 * Controller for show list view.
 */
public class ShowListViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected ListView<String> wishesListView;
  @FXML protected Button shareList;
  @FXML protected Button addNewWishButton;
  @FXML protected TextField addNewWishField;
  @FXML protected Button removeWish;
  @FXML protected Label wishListName;
  @FXML protected Label addWishFeedback;

  public ShowListViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }


  public void initialize() {
    if (this.user != null && this.wishListToShare != null) {
      wishListName.setText(this.wishListToShare.getName());
      this.updateWishesView();
    }
  }

  private void updateWishesView() {
    List<Wish> wishes = wishListToShare.getWishes();
    System.out.println(wishes);
    List<String> wishNames = new ArrayList<>();
    for (Wish w : wishes) {
      wishNames.add(w.getName());
    }
    wishesListView.setItems(FXCollections.observableList(wishNames));

  }

  public void addWish() throws Exception {
    String wishName = addNewWishField.getText();
    if (wishName.length() == 0) {
      addWishFeedback.setText("Wish must have content!");
    } else {
      Wish wish = this.wishListToShare.getWishes().stream().filter(e -> e.getName().equals(wishName)).findFirst().orElse(null);
      if (wish != null) {
        addWishFeedback.setText("This wish list already contains that wish!");
      } else {
        jsonHandler.addWish(wishName, wishListToShare, user);
        addWishFeedback.setText("Wish was added!");
        addNewWishField.clear();
      }

    }
    this.updateWishesView();
  }

  public void removeWish() throws Exception {
    String wishName = wishesListView.getSelectionModel().getSelectedItem();
    if (wishName == null) {
      addWishFeedback.setText("You must choose a wish to remove!");
    } else {
      jsonHandler.removeWish(wishName, wishListToShare, user);
      addWishFeedback.setText("Wish was removed!");
    }
    this.updateWishesView();
  }
}
