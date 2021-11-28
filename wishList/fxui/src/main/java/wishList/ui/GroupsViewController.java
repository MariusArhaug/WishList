package wishList.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import wishList.core.User;
import wishList.core.WishList;

/**
 * Controller for GroupsView.fxml
 */
public class GroupsViewController extends AbstractController {
  @FXML protected Button shareWithGroup;
  @FXML protected Button moveToGroup;
  @FXML protected Button moveOutOfGroup;
  @FXML protected ListView<String> yourFriendsList;
  @FXML protected ListView<String> yourGroupList;
  @FXML protected Label shareWithGroupFeedback;

  @Override
  public void initialize() {
    if (this.user != null) {
      this.updateFriendsView();
    }
  }

  /** Move to group. */
  public void moveToGroup() {
    String friend = yourFriendsList.getSelectionModel().getSelectedItem();
    yourFriendsList.getSelectionModel().clearSelection();
    if (friend == null) {
      shareWithGroupFeedback.setText("You have to choose a friend to add to group!");
      return;
    }
    ObservableList<String> listGroup = yourGroupList.getItems();
    ObservableList<String> listFriend = yourFriendsList.getItems();
    listGroup.add(friend);
    yourGroupList.setItems(listGroup);
    listFriend.remove(friend);
    yourFriendsList.setItems(listFriend);
    shareWithGroupFeedback.setText("Moved friend to group!");
  }

  /** Move out of group. */
  public void moveOutOfGroup() {
    String friend = yourGroupList.getSelectionModel().getSelectedItem();
    yourGroupList.getSelectionModel().clearSelection();
    if (friend == null) {
      shareWithGroupFeedback.setText("You have to choose a friend to remove from group!");
      return;
    }
    ObservableList<String> listGroup = yourGroupList.getItems();
    ObservableList<String> listFriend = yourFriendsList.getItems();
    listGroup.remove(friend);
    yourGroupList.setItems(listGroup);
    listFriend.add(friend);
    yourFriendsList.setItems(listFriend);
    shareWithGroupFeedback.setText("Removed friend from group!");
  }

  /**
   * Update items in ListView.
   */
  public void updateFriendsView() {
    yourFriendsList.setItems(FXCollections.observableList(this.user.getContacts()));
  }

  /**
   * Share wishList.
   *
   * @param event action event
   * @throws Exception exception
   */
  public void shareWishList(ActionEvent event) throws Exception {
    ObservableList<String> observableGroup = yourGroupList.getItems();
    List<String> emailGroup = new ArrayList<>(observableGroup);
    List<User> yourGroup = new ArrayList<>();
    for (String email : emailGroup) {
      Optional<User> optionalUser = httpController.getUser(email);
      optionalUser.ifPresent(yourGroup::add);
    }
    if (yourGroup.size() == 0) {
      shareWithGroupFeedback.setText("You can not share list with empty group!");
      return;
    }
    outerLoop:
    for (User u : yourGroup) {
      for (WishList wishList : u.getInvitedWishLists()) {
        if (wishList.getName().equals(wishListToShare.getName()) && wishList.getOwner().equals(this.user.getEmail())) {
          yourGroup.remove(u);
          if (yourGroup.size() == 0) {
            break outerLoop;
          }
          break;
        }
      }
    }
    if (yourGroup.size() == 0) {
      shareWithGroupFeedback.setText("This list has been shared with this group already!");
      return;
    }
    this.user = httpController.shareWishList(user, wishListToShare, yourGroup);
    this.wishListToShare = null;
    changeToMainView(event);
  }
}
