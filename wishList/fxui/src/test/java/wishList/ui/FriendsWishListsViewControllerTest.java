package wishList.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.json.JsonHandler;

import javax.swing.text.html.ListView;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class FriendsWishListsViewControllerTest extends AbstractTestFxui {
    private FriendsWishListsViewController controller;
    private User user;
    private JsonHandler jsonHandler;

    /**
     * Load test fxml file. Get controller from the file
     * @param stage
     * @throws Exception if file is not found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("FriendsWishListsViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Set up the attributes needed in the tests
     * @throws IOException if file is not found
     */
    @BeforeEach
    public void setup() throws IOException {
        this.controller.enterFeedback.setText("");
        this.jsonHandler = new JsonHandler(MainViewControllerTest.directory);
        this.user = jsonHandler.loadJsonUser("jane@doecom");
        this.controller.updateUser(this.user);
    }

    /**
     * Check that controller from file is not null
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
    }


    /**
     * Test the errors correlated with entering wishlist
     */
    @Test
    public void testEnterWishListFault(){
        assertEquals(controller.enterFeedback.getText(), "");

        clickOn("#enterFriendsWishList");
        assertEquals(controller.enterFeedback.getText(), "You must choose a wish list to enter!");

        clickOn("#friendsWishListView");
        clickOn("#enterFriendsWishList");
        assertEquals(controller.enterFeedback.getText(), "You must choose a wish list to enter!");
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
     * Test that yourWishLists changes to correct scene
     */
    @Test
    public void testYourWishLists() {
        clickOn("#yourWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("addList"));
    }

}