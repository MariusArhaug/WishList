package wishList.ui;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.json.JsonHandler;
import wishList.utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class MainViewControllerTest extends AbstractTestFxui {
    private MainViewController controller;
    private User user;
    private JsonHandler jsonHandler;
    static String[] paths = new File("").getAbsolutePath().split("fxui");

    static String directory =
            Paths.get(
                    paths[0],
                    "rest",
                    "src",
                    "main",
                    "resources",
                    "wishList",
                    "users")
            .toString();

    /**
     * Load test fxml file. Get controller from the file
     * @param stage
     * @throws Exception if file is not found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("MainViewTest.fxml"));
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
    public void setup() throws Exception {
        this.controller.newWishListFeedback.setText("");
        Utils.resetFile(directory, "jane@doecom.json");
        this.jsonHandler = new JsonHandler(directory);
        user = new User("Jane", "Doe", "jane@doe.com", "qwerty123");
        this.user = jsonHandler.addUser(user);
        this.controller.updateUser(this.user);
    }
    @AfterEach
    public void tearDown() {
        this.user = null;
        jsonHandler = null;
    }



    /**
     * Check that controller from file is not null
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
    }

    /**
     * Test that the error messages correlated to the new list and remove list methods work correctly
     * Test that list is added and removed
     */
    @Test
    public void testCreateNewAndRemoveWishList(){
        assertEquals(controller.newWishListFeedback.getText(), "");

        clickOn("#addNewList");
        assertEquals(controller.newWishListFeedback.getText(), "The wish list must have a name!");

        clickOn("#addNewListField");
        write("test");
        clickOn("#addNewList");
        assertEquals(controller.newWishListFeedback.getText(), "New wish list added!");

        clickOn("#addNewListField");
        write("test");
        clickOn("#addNewList");
        assertEquals(controller.newWishListFeedback.getText(), "You already have a wish list with this name!");

        clickOn("#removeWishList");
        assertEquals(controller.newWishListFeedback.getText(), "You must choose a wish list to remove!");

        clickOn("#list");
        clickOn("#removeWishList");
        assertEquals(controller.newWishListFeedback.getText(), "You must choose a wish list to remove!");

        clickOn("#list");
        type(KeyCode.UP);
        type(KeyCode.ENTER);
        clickOn("#removeWishList");
        assertEquals(controller.newWishListFeedback.getText(), "The wish list was removed!");
    }

    /**
     * Test that the input in text field is correct
     */
    @Test
    public void testTextFields(){
        clickOn("#addNewListField");
        write("test");
        assertEquals(controller.addNewListField.getText(), "test");
    }

    /**
     * Test the errors correlated with entering wishlist
     */
    @Test
    public void testEnterWishListFault(){
        assertEquals(controller.newWishListFeedback.getText(), "");

        clickOn("#enterList");
        assertEquals(controller.newWishListFeedback.getText(), "You must choose a wish list to enter!");

        clickOn("#list");
        clickOn("#enterList");
        assertEquals(controller.newWishListFeedback.getText(), "You must choose a wish list to enter!");
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
     * Test that friendWishLists changes to correct scene
     */
    @Test
    public void testChangeToFriendsListsView(){
        clickOn("#friendWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("friendsWishListsView"));
    }

}