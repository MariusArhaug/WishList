package wishList.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import wishList.core.User;
import wishList.core.WishList;
import wishList.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** FriendWishList controller. */
public class FriendsWishListsViewController extends AbstractController {
  @FXML protected ListView<String> friendsWishListView;

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
    String[] wishListInfo = wishListAndOwner.split(" : ");
    String email = wishListInfo[0];
    String wishListName = wishListInfo[1];
    if (!Utils.existInList(httpController.getUsers(), e -> e.getEmail().equals(email))) {
      this.changeToFriendsWishesView(
          event,
          Utils.findFirstOrNull(user.getOwnedWishLists(), e -> e.getName().equals(wishListName)));
    }
  }

  private void updateListView() {
    List<WishList> invitedWishLists = user.getInvitedWishLists();
    List<String> wishListNamesAndOwner = new ArrayList<>();
    for (WishList w : invitedWishLists) {
      User owner = w.getOwner();
      String name = w.getName();
      wishListNamesAndOwner.add(owner.toString() + " : " + name);
    }
    friendsWishListView.setItems(FXCollections.observableList(wishListNamesAndOwner));
  }
}
