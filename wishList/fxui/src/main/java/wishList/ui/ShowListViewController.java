package wishList.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wishList.core.Wish;
import wishList.core.WishList;
import wishList.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/** Controller for show list view. */
public class ShowListViewController extends AbstractController {
  @FXML protected ListView<String> wishesListView;
  @FXML protected Button shareList;
  @FXML protected Button addNewWishButton;
  @FXML protected TextField addNewWishField;
  @FXML protected Button removeWish;
  @FXML protected Label wishListName;
  @FXML protected Label addWishFeedback;

  @Override
  public void initialize() {
    if (this.user != null && this.wishListToShare != null) {
      wishListName.setText(this.wishListToShare.getName());
      this.updateWishesView();
    }
  }

  private void updateWishesView() {
    WishList wishList = this.user.getWishList(wishListToShare.getName()).get();
    List<String> wishNames = new ArrayList<>();
    for (Wish w : wishList) {
      wishNames.add(w.getName());
    }
    wishesListView.setItems(FXCollections.observableList(wishNames));
  }

  /** Add wish. */
  public void addWish() {
    String wishName = addNewWishField.getText();
    if (wishName.length() == 0) {
      addWishFeedback.setText("Wish must have content!");
      return;
    }
    Wish wish =
        Utils.findFirstOrNull(this.wishListToShare.getWishes(), e -> e.getName().equals(wishName));

    if (wish != null) {
      addWishFeedback.setText("This wish list already contains that wish!");
      return;
    }

    try {
      this.user = httpController.addWish(wishName, wishListToShare, user);
    } catch (Exception e) {
      errorMessage.setText("Something unexpected happened!");
      return;
    }
    addWishFeedback.setText("Wish was added!");
    addNewWishField.clear();

    this.updateWishesView();
  }

  /** Remove wish. */
  public void removeWish() {
    String wishName = wishesListView.getSelectionModel().getSelectedItem();
    if (wishName == null) {
      addWishFeedback.setText("You must choose a wish to remove!");
      return;
    }
    try {
      this.user = httpController.removeWish(wishName, wishListToShare, user);
    } catch (Exception e) {
      errorMessage.setText("Something unexpected happened!");
      return;
    }
    addWishFeedback.setText("Wish was removed!");

    this.updateWishesView();
  }
}
