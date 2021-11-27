package wishList.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wishList.core.User;
import wishList.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/** Controller for friend actions. * */
public class FriendsViewController extends AbstractController {
  @FXML protected TextField friendEmailField;
  @FXML protected Button addNewFriendButton;
  @FXML protected Button removeFriend;
  @FXML protected ListView<String> yourFriendsList;
  @FXML protected Label yourFriendsFeedback;

  @Override
  public void initialize() {
    if (this.user != null) {
      this.updateListView();
    }
  }

  /** Add friend. */
  public void addFriend() {
    yourFriendsFeedback.setText("");
    String email = friendEmailField.getText();
    if (email.equals(user.getEmail())) {
      yourFriendsFeedback.setText("You can not befriend yourself!");
      return;
    }
    if (Utils.existInList(user.getContacts(), e -> e.equals(email))) {
      yourFriendsFeedback.setText("You are already friends with this user!");
      return;
    }
    if (email.length() == 0){
      yourFriendsFeedback.setText("No user exist with this email");
      return;
    }
    User newFriend;
    try {
      this.user = httpController.addContact(email, user);
      /*
      if (httpController.getUser(email).isEmpty()) {
        throw new IllegalArgumentException("No user exist with this email");
      } */
      newFriend = httpController.getUser(email).get();
    } catch (IllegalArgumentException e) {
      yourFriendsFeedback.setText(e.getMessage());
      return;
    } catch (Exception e) {
      yourFriendsFeedback.setText("Something unexpected happened! :(");
      return;
    }

    String name = newFriend.getFirstName() + " " + newFriend.getLastName();
    friendEmailField.setText("");
    yourFriendsFeedback.setText("Added " + name + " to your contacts!");
    this.updateListView();
  }

  /** Remove friend. */
  public void removeFriend() {
    String userInfo = yourFriendsList.getSelectionModel().getSelectedItem();
    if(userInfo == null){
      yourFriendsFeedback.setText("You must choose a friend to delete!");
      return;
    }
    String[] userInfos = userInfo.split("; ");
    try {
      this.user = httpController.removeContact(userInfos[1], this.user);
    } catch (Exception e) {
      yourFriendsFeedback.setText("Something unexpected happened!");
      return;
    }
    yourFriendsFeedback.setText("Removed " + userInfos[0] + " from your contacts!");
    this.updateListView();
  }

  private void updateListView() {
    try {
      List<String> fxNames = new ArrayList<>();
      List<User> users = httpController.getUsers();
      for (String email : this.user.getContacts()) {
        for (User u : users) {
          if (u.getEmail().equals(email)) {
            fxNames.add("" + u.getFirstName() + " " + u.getLastName() + "; " + u.getEmail());
          }
        }
      }
      yourFriendsList.setItems(FXCollections.observableList(fxNames));
    } catch (IOException | InterruptedException e) {
      yourFriendsFeedback.setText("Something unexpected happened!");
    }
  }
}
