package wishList.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
public class RegisterViewControllerTest extends AbstractTestFxui {
    private RegisterViewController controller;

    /**
     * Load the test fxml file that is connected to RegisterViewController
     * Get controller, save as attribute
     * @param stage
     * @throws Exception if file is not found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterViewTest.fxml"));
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
     * Check that test fields work when input is correct
     */
    @Test
    public void testCorrectUser(){
        assertEquals(controller.emailSignUp.getText(), "");
        clickOn("#emailSignUp");
        write("abc");
        assertEquals(controller.emailSignUp.getText(), "abc");

        assertEquals(controller.firstNameSignUp.getText(), "");
        clickOn("#firstNameSignUp");
        write("def");
        assertEquals(controller.firstNameSignUp.getText(), "def");

        assertEquals(controller.lastNameSignUp.getText(), "");
        clickOn("#lastNameSignUp");
        write("ghi");
        assertEquals(controller.lastNameSignUp.getText(), "ghi");

        assertEquals(controller.passwordSignUp.getText(), "");
        clickOn("#passwordSignUp");
        write("jkl");
        assertEquals(controller.passwordSignUp.getText(), "jkl");

        clickOn("#signup");
        assertEquals(controller.emailSignUp.getText(), "abc");
        assertEquals(controller.firstNameSignUp.getText(), "def");
        assertEquals(controller.lastNameSignUp.getText(), "ghi");
        assertEquals(controller.passwordSignUp.getText(), "jkl");
    }

    /**
     * Test that backToLogIn changes to correct scene
     */
    @Test
    public void testBackToLogIn() {
        clickOn("#backToLogIn");
        WaitForAsyncUtils.waitForFxEvents();
        assertNotNull(findSceneRootWithId("loginPane"));
    }
}
