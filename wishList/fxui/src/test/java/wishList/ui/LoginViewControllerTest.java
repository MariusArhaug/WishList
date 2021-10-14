package wishList.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.control.LabeledMatchers;
import wishList.core.User;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class LoginViewControllerTest extends ApplicationTest{
    private LoginViewController controller;
    private User user;

    /**
     *
     * @param stage stage
     * @throws Exception if file is not found
     *
     * Load fxml file, get controller from file
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Check if user is null
     */
    @BeforeEach
    public void setup(){
        this.user = null;
    }

    /**
     * Test that attributes' states are correct
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
        assertNull(this.user);
    }

    /**
     * Check that the label that displays error message works as planned
     * @param robot executes operations in the GUI
     */
    @Test
    public void verifyThatLabelChanges(FxRobot robot){
        FxAssert.verifyThat("#errorMessage", LabeledMatchers.hasText("Label"));
        assertEquals(controller.errorMessage.getText(), "Label");

        robot.clickOn("#login");

        FxAssert.verifyThat("#errorMessage", LabeledMatchers.hasText("E-mail or password is incorrect"));
        FxAssert.verifyThat(controller.errorMessage, LabeledMatchers.hasText("E-mail or password is incorrect"));
        assertEquals(controller.errorMessage.getText(), "E-mail or password is incorrect");
    }

    /**
     * Test if existing user can be found in json file
     * The user was added to file before the test
     * @param robot executes the operations in the GUI
     * @throws Exception
     */
    @Test
    public void verifyThatUserExists(FxRobot robot) throws Exception {
        robot.clickOn("#loginEmailInput");
        robot.write("jane@doe");
        robot.clickOn("#loginPasswordInput");
        robot.write("qwerty");
        robot.clickOn("#login");

        //If errorMessage is "Label" after clicking on login,
        //the user was found in the json file
        assertEquals(controller.errorMessage.getText(), "Label");

        controller.checkUser();
        this.user = controller.user;
        assertNotNull(this.user);
        assertEquals(controller.loginEmailInput.getText(), this.user.getEmail());
        assertEquals(controller.loginPasswordInput.getText(), this.user.getPassword());

    }

    /**
     * Test if text fields work correctly
     * @param robot executes operations in the GUI
     */
    @Test
    public void testTextFields(FxRobot robot){
        assertEquals(controller.loginEmailInput.getText(), "");
        robot.clickOn("#loginEmailInput");
        robot.write("Hello");
        assertEquals(controller.loginEmailInput.getText(), "Hello");
        robot.clickOn("#login");
        assertEquals(controller.loginEmailInput.getText(), "Hello");

        assertEquals(controller.loginPasswordInput.getText(), "");
        robot.clickOn("#loginPasswordInput");
        robot.write("there");
        assertEquals(controller.loginPasswordInput.getText(), "there");
        robot.clickOn("#login");
        assertEquals(controller.loginPasswordInput.getText(), "there");
    }
}
