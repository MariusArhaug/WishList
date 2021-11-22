package wishList.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FriendsWishListsViewController extends AbstractController {
    @FXML protected ListView<String> friendsWishListView;
    @FXML protected TableColumn<String, String> wishListNameColumn;
    @FXML protected TableColumn<String, String> ownerColumn;

    public void initialize() {
        if (this.user != null) {
            System.out.println(user.getInvitedWishLists());
            this.updateListView();
        }
    }

    public void enterFriendsWishList(ActionEvent event) throws IOException {
        String wishListAndOwner = friendsWishListView.getSelectionModel().getSelectedItem();
        String[] wishListInfo = wishListAndOwner.split(" : ");
        String email = wishListInfo[0];
        String wishListName = wishListInfo[1];
        User friend = User.getUsers().stream().filter(e -> e.getEmail().equals(email)).findFirst().orElse(null);
        if (friend != null) {
            List<WishList> ownedWishLists = new ArrayList<>();
            Iterator<WishList> iteratorWishLists = friend.iterator();
            iteratorWishLists.forEachRemaining(ownedWishLists::add);
            WishList wishList = ownedWishLists.stream().filter(e -> e.getName().equals(wishListName)).findAny().orElse(null);
            this.changeToFriendsWishesView(event, wishList);
        }
    }

    public void updateListView() {
        System.out.println(user);
        System.out.println(user.getContacts());
        List<WishList> invitedWishLists = user.getInvitedWishLists();
        System.out.println(invitedWishLists);
        List<String> wishListNamesAndOwner = new ArrayList<>();
        for (WishList w : invitedWishLists) {
            User owner = w.getOwner();
            String name = w.getName();
            wishListNamesAndOwner.add(owner.toString() + " : " + name);
        }
        System.out.println(wishListNamesAndOwner);
        friendsWishListView.setItems(FXCollections.observableList(wishListNamesAndOwner));
    }
}
