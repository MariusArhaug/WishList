package wishList.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class FriendsWishesViewControllerTest extends AbstractTestFxui {
    private FriendsWishesViewController controller;
    private User user;
    private JsonHandler jsonHandler;

    /**
     * Load test fxml file. Get controller from file
     * @param stage
     * @throws Exception if file is not found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendsWishesViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Check that controller from fxml file is not null
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
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
     * Test that yourFriends changes to correct scene
     */
    @Test
    public void testChangeToFriendsView() {
        clickOn("#yourFriends");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("friendsView"));
    }

    /**
     * Test that testBackToFriendsWishLists changes to correct scene
     */
    @Test
    public void testBackToFriendsWishLists(){
        clickOn("#backToFriendsWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("friendsWishListsView"));
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
}
