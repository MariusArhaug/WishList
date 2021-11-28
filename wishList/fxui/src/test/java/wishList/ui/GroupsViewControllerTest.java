package wishList.ui;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class GroupsViewControllerTest extends AbstractTestFxui {
    private GroupsViewController controller;
    private User user;
    private User testFriend;
    private JsonHandler jsonHandler;

    /**
     * Opens scene, gets controller from file and saves as attribute
     *
     * @param stage the stage that will open
     * @throws Exception if file isn't found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("GroupsViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Set up attributes that will be used in tests
     *
     * @throws IOException if files aren't found
     */
    @BeforeEach
    public void setup() throws IOException {
        this.controller.shareWithGroupFeedback.setText("");
        this.jsonHandler = new JsonHandler(MainViewControllerTest.directory);

        this.user = jsonHandler.loadJsonUser("jane@doecom");
        this.testFriend = jsonHandler.loadJsonUser("james@doecom");
        this.user.addContact("james@doe.com");

        this.user.makeWishList("TestList");
        this.controller.updateUser(this.user);
        Optional<WishList> foo = user.getWishList("TestList");
        WishList testList;
        if (foo.isEmpty()) {
            throw new IllegalArgumentException("List doesn't exist");
        } else {
            testList = foo.get();
        }
        this.controller.wishListToShare = testList;
        controller.updateFriendsView();
    }

    /**
     * Removes wishlist and contact so that user can be used in other tests
     *
     * @throws Exception if file isn't found
     */
    @AfterEach
    public void cleanUp() throws Exception {
        this.jsonHandler.removeWishList("TestList", user);
        this.jsonHandler.removeContact("james@doe.com", user);
        this.controller.updateUser(this.user);
    }

    /** Tests that error messages are displayed correctly */
    @Test
    public void testErrorMessages() {
        clickOn("#moveToGroup");
        Assertions.assertEquals(
                this.controller.shareWithGroupFeedback.getText(),
                "You have to choose a friend to add to group!");

        clickOn("#moveOutOfGroup");
        Assertions.assertEquals(
                this.controller.shareWithGroupFeedback.getText(),
                "You have to choose a friend to remove from group!");

        clickOn("#yourFriendsList");
        clickOn("#moveToGroup");
        Assertions.assertEquals(
                this.controller.shareWithGroupFeedback.getText(),
                "You have to choose a friend to add to group!");

        clickOn("#yourGroupList");
        clickOn("#moveOutOfGroup");
        Assertions.assertEquals(
                this.controller.shareWithGroupFeedback.getText(),
                "You have to choose a friend to remove from group!");

        Assertions.assertEquals(
                String.valueOf(this.controller.yourFriendsList.getItems()), "[james@doe.com]");
        Assertions.assertEquals(String.valueOf(this.controller.yourGroupList.getItems()), "[]");

        clickOn("#shareWithGroup");
        Assertions.assertEquals(
                this.controller.shareWithGroupFeedback.getText(),
                "You can not share list with empty group!");
    }

    /** Test that signOut changes to correct scene */
    @Test
    public void testSignOut() {
        clickOn("#signOut");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("loginPane"));
    }

    /** Test that yourFriends changes to correct scene */
    @Test
    public void testChangeToFriendsView() {
        clickOn("#yourFriends");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("friendsView"));
    }

    /** Test that friendWishLists changes to correct scene */
    @Test
    public void testChangeToFriendsListsView() {
        clickOn("#friendWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("friendsWishListsView"));
    }

    /** Test that backToShowListView changes to correct scene */
    @Test
    public void testBackToYourWishLists() {
        clickOn("#backToShowListView");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("showListView"));
    }
}
