package wishList.ui;

import java.io.IOException;
import java.util.Optional;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.core.WishList;
import wishList.json.JsonHandler;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class ShowListViewControllerTest extends AbstractTestFxui {
    private ShowListViewController controller;
    private User user;
    private JsonHandler jsonHandler;

    /**
     * Load test fxml file. Get controller from file
     * @param stage
     * @throws Exception if file is not found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowListViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Set up attributes needed for tests
     * @throws IOException is file is not found
     */
    @BeforeEach
    public void setup() throws IOException {
        this.controller.addWishFeedback.setText("");
        this.jsonHandler = new JsonHandler(this.controller.resourcesPath);
        this.user = jsonHandler.loadJsonUser("jane@doecom");
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
    }

    /**
     * Removes wishlist and contact so that user can be used
     * in other tests
     * @throws Exception if removeWishList throws exception in JsonHandler
     */
    @AfterEach
    public void cleanUp() throws Exception {
        this.jsonHandler.removeWishList("TestList", user);
        this.controller.updateUser(this.user);
    }

    /**
     * Check that controller from fxml file is not null
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
    }

    /**
     * Test that error messages correlated to adding wishes work correctly
     * Test that
     */
    @Test
    public void testAddWish(){
        assertEquals(controller.addWishFeedback.getText(), "");
        clickOn("#addNewWishButton");
        assertEquals(controller.addWishFeedback.getText(), "Wish must have content!");

        clickOn("#addNewWishField");
        write("foo");
        clickOn("#addNewWishButton");
        assertEquals(controller.addWishFeedback.getText(), "Wish was added!");
        assertNotNull(controller.wishListToShare.getWishes());

        clickOn("#addNewWishField");
        write("foo");
        clickOn("#addNewWishButton");
        assertEquals(controller.addWishFeedback.getText(), "This wish list already contains that wish!");
    }

    /**
     * Test that error messages correlated to removing wishes work correctly
     * Test that wish is removed
     */
    @Test
    public void testRemoveWish(){
        //setup because list with foo got removed after test
        clickOn("#addNewWishField");
        write("foo");
        clickOn("#addNewWishButton");

        clickOn("#removeWish");
        assertEquals(controller.addWishFeedback.getText(), "You must choose a wish to remove!");

        clickOn("#wishesListView");
        type(KeyCode.UP);
        type(KeyCode.ENTER);
        clickOn("#removeWish");
        assertEquals(controller.addWishFeedback.getText(), "Wish was removed!");
        assertEquals(String.valueOf(controller.wishListToShare.getWishes()), "[]");
    }

    /**
     * Test that input in text field is correct
     */
    @Test
    public void testTextField(){
        clickOn("#addNewWishField");
        write("test");
        assertEquals(controller.addNewWishField.getText(), "test");
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

    /**
     * Test that backToYourWishLists changes to correct scene
     */
    @Test
    public void testBackToYourWishLists(){
        clickOn("#backToYourWishLists");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("addList"));
    }

    /**
     * Test that shareList changes to correct scene
     */
    @Test
    public void testChangeToGroupsView(){
        clickOn("#shareList");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("groupsView"));
    }

}