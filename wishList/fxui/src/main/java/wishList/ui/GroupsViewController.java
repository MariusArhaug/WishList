package wishList.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;

import java.util.ArrayList;
import java.util.List;

public class GroupsViewController extends AbstractController {
  private final JsonHandler jsonHandler;
  @FXML protected Button shareWithGroup;
  @FXML protected Button moveToGroup;
  @FXML protected Button moveOutOfGroup;
  @FXML protected ListView<String> yourFriendsList;
  @FXML protected ListView<String> yourGroupList;
  @FXML protected Label shareWithGroupFeedback;

  public GroupsViewController() {
    jsonHandler = new JsonHandler(this.resourcesPath);
  }

  @Override
  public void initialize() {
    if (this.user != null) {
      this.updateFriendsView();
    }
  }

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
    this.updateFriendsView();
  }

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
    this.updateFriendsView();
  }

  private void updateFriendsView() {
    List<User> friends = user.getContacts();
    ObservableList<String> group = yourGroupList.getItems();
    for (String member : group) {
      User friend =
          friends.stream().filter(e -> e.getEmail().equals(member)).findFirst().orElse(null);
      if (friend != null) {
        friends.remove(friend);
      }
    }
    List<String> names = new ArrayList<>();
    for (User u : friends) {
      String name = u.getEmail();
      names.add(name);
    }
    yourFriendsList.setItems(FXCollections.observableList(names));
  }

  public void shareWishList(ActionEvent event) throws Exception {
    ObservableList<String> observableGroup = yourGroupList.getItems();
    List<String> stringGroup = new ArrayList<>(observableGroup);
    List<User> group = new ArrayList<>();
    for (String s : stringGroup) {
      User groupMember =
          User.getUsers().stream().filter(e -> e.getEmail().equals(s)).findFirst().orElse(null);
      group.add(groupMember);
    }
    if (group.size() == 0) {
      shareWithGroupFeedback.setText("You can not share list with empty group!");
      return;
    }
    for (User u : group) {
      WishList alreadyShared =
          u.getInvitedWishLists().stream()
              .filter(
                  e ->
                      e.getOwner().toString().equals(user.toString())
                          && e.getName().equals(wishListToShare.getName()))
              .findFirst()
              .orElse(null);
      if (alreadyShared != null) {
        group.remove(u);
      }
    }
    if (group.size() == 0) {
      shareWithGroupFeedback.setText("This list has been shared with this group already!");
      return;
    }
    jsonHandler.shareWishList(user, wishListToShare, group);
    this.wishListToShare = null;
    changeToMainView(event);
  }
}
