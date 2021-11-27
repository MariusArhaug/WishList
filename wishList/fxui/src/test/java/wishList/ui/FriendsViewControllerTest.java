package wishList.ui;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.json.JsonHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class FriendsViewControllerTest extends AbstractTestFxui {
    private FriendsViewController controller;
    private User user;
    private JsonHandler jsonHandler;
    private User testFriend;

    /**
     * Opens scene, gets controller from file and saves as attribute
     *
     * @param stage the stage that will open
     * @throws Exception if file isn't found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendsViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Set up attributes that will be used in tests
     * @throws IOException if files aren't found
     */
    @BeforeEach
    public void setup() throws IOException {
        this.controller.yourFriendsFeedback.setText("");
        this.jsonHandler = new JsonHandler(this.controller.resourcesPath);
        this.user = jsonHandler.loadJsonUser("jane@doecom");
        this.controller.updateUser(this.user);
        this.testFriend = jsonHandler.loadJsonUser("james@doecom");
    }

    /**
     * Test that controller isn't null
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
    }

    /**
     * Test that adding and removing friends works as planned
     * Test that correct errorMessages are sent
     */
    @Test
    public void testAddAndRemoveFriend(){
        clickOn("#friendEmailField");
        write(this.user.getEmail());
        clickOn("#addNewFriendButton");
        assertEquals(controller.yourFriendsFeedback.getText(), "You can not befriend yourself!");
        controller.friendEmailField.setText("");

        clickOn("#friendEmailField");
        write("");
        clickOn("#addNewFriendButton");
        assertEquals(controller.yourFriendsFeedback.getText(), "No user exist with this email");

        clickOn("#friendEmailField");
        write(testFriend.getEmail());
        clickOn("#addNewFriendButton");
        assertEquals(controller.yourFriendsFeedback.getText(), "Added James Doe to your contacts!");

        clickOn("#removeFriend");
        assertEquals(controller.yourFriendsFeedback.getText(), "You must choose a friend to delete!");

        clickOn("#yourFriendsList");
        type(KeyCode.UP);
        type(KeyCode.ENTER);
        clickOn("#removeFriend");
        assertEquals(controller.yourFriendsFeedback.getText(), "Removed James Doe from your contacts!");
    }

    /**
     * Test that input in text field is correct
     */
    @Test
    public void testTextField(){
        clickOn("#friendEmailField");
        write("test");
    }

    /**
     * Test that signOut changes to correct scene
     */
    @Test
    public void testSignOut() {
        clickOn("#signOut");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("loginPane"));
    }

    /**
     * Test that yourWishLists changes to correct scene
     */
    @Test
    public void testYourWishLists() {
        clickOn("#yourWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("addList"));
    }

    /**
     * Test that friendsWishLists changes to correct scene
     */
    @Test
    public void testChangeToFriendsListsView(){
        clickOn("#friendsWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("friendsWishListsView"));
    }
}
