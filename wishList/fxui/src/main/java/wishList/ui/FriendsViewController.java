package wishList.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import wishList.core.User;
import wishList.json.JsonHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FriendsViewController extends AbstractController {
    private final JsonHandler jsonHandler;
    @FXML protected TextField friendEmailField;
    @FXML protected Button addNewFriendButton;
    @FXML protected Button removeFriend;
    @FXML protected ListView<String> yourFriendsList;
    @FXML protected Label yourFriendsFeedback;

    public FriendsViewController() {
        jsonHandler = new JsonHandler(this.resourcesPath);
    }

    public void initialize() {
        if (this.user != null) {
            this.updateListView();
        }
    }

    public void addFriend() throws Exception {
        yourFriendsFeedback.setText("");
        String email = friendEmailField.getText();
        if (email.equals(user.getEmail())) {
            yourFriendsFeedback.setText("You can not befriend yourself!");
            return;
        }
        List<User> friends = user.getContacts();
        User alreadyFriends = friends.stream().filter(e -> e.getEmail().equals(email)).findFirst().orElse(null);
        if (alreadyFriends != null) {
            yourFriendsFeedback.setText("You are already friends with this user!");
            return;
        }
        User newFriend = User.getUsers().stream().filter(e -> e.getEmail().equals(email)).findFirst().orElse(null);
        if(newFriend == null) {
            yourFriendsFeedback.setText("No user with this email exists!");
            return;
        }
        jsonHandler.addContact(newFriend, user);
        String name = newFriend.getFirstName() + " " + newFriend.getLastName();
        friendEmailField.setText("");
        yourFriendsFeedback.setText("Added " + name + " to your contacts!");
        this.updateListView();
    }

    public void removeFriend() throws Exception {
        String userInfo = yourFriendsList.getSelectionModel().getSelectedItem();
        String[] userInfos = userInfo.split("; ");
        jsonHandler.removeContact(userInfos[1], user);
        yourFriendsFeedback.setText("Removed " + userInfos[0] + " from your contacts!");
        this.updateListView();
    }

    public void updateListView() {
        List<User> friends = user.getContacts();
        List<String> names = new ArrayList<>();
        if (friends.size() > 0) {
            for (User u : friends) {
                User friend = User.getUsers().stream().filter(e -> e.getEmail().equals(u.getEmail())).findFirst().orElse(null);
                if (friend != null) {
                    String firstName = friend.getFirstName();
                    String lastName = friend.getLastName();
                    String email = friend.getEmail();
                    String fullName = "" + firstName + " " + lastName + "; " + email;
                    names.add(fullName);
                }
            }
            yourFriendsList.setItems(FXCollections.observableList(names));
        }
    }
}
