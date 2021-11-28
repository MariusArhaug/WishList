package wishList.ui;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import wishList.core.Wish;


/**
 * Controller for FriendsWishesView.fxml
 */
public class FriendsWishesViewController extends AbstractController {
  @FXML protected ListView<String> friendsWishListWishes;
  @FXML protected Label friendsWishListName;

  @Override
  public void initialize() {
    if (this.user != null) {
      this.updateListView();
      friendsWishListName.setText(this.wishListToShare.getName());
    }
  }

  /**
   * Update the items in the ListView.
   */
  private void updateListView() {
    List<Wish> wishes = wishListToShare.getWishes();
    List<String> wishNames = new ArrayList<>();
    for (Wish w : wishes) {
      wishNames.add(w.getName());
    }
    friendsWishListWishes.setItems(FXCollections.observableList(wishNames));
  }
}
