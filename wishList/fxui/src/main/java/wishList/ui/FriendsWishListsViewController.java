package wishList.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import wishList.core.User;
import wishList.core.WishList;
import wishList.utils.Utils;

/** FriendWishList controller. */
public class FriendsWishListsViewController extends AbstractController {
  @FXML protected ListView<String> friendsWishListView;
  @FXML protected Label enterFeedback;
  @FXML protected Button enterFriendsWishList;

  @Override
  public void initialize() {
    if (this.user != null) {
      this.updateListView();
    }
  }

  /**
   * Enter friend wishList.
   *
   * @param event action event
   * @throws IOException file not found
   */
  public void enterFriendsWishList(ActionEvent event) throws IOException, InterruptedException {
    String wishListAndOwner = friendsWishListView.getSelectionModel().getSelectedItem();
    if (wishListAndOwner == null || wishListAndOwner.length() == 0) {
      enterFeedback.setText("You must choose a wish list to enter!");
      return;
    }
    String[] wishListInfo = wishListAndOwner.split(" : ");
    String email = wishListInfo[0];
    String wishListName = wishListInfo[1];
    if (Utils.existInList(httpController.getUsers(), e -> e.getEmail().equals(email))) {
      User friend = Utils.findFirstOrNull(httpController.getUsers(),
              e -> e.getEmail().equals(email));
      this.changeToFriendsWishesView(
          event, Utils.findFirstOrNull(friend.getOwnedWishLists(),
                      e -> e.getName().equals(wishListName)));
    }
  }

  /**
   * Update items in ListView.
   */
  private void updateListView() {
    List<WishList> invitedWishLists = user.getInvitedWishLists();
    List<String> wishListNamesAndOwner = new ArrayList<>();
    for (WishList w : invitedWishLists) {
      String owner = w.getOwner();
      String name = w.getName();
      wishListNamesAndOwner.add(owner + " : " + name);
    }
    friendsWishListView.setItems(FXCollections.observableList(wishListNamesAndOwner));
  }
}
