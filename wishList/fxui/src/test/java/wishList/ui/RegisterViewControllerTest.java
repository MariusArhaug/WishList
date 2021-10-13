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
import wishList.core.User;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class RegisterViewControllerTest extends ApplicationTest {
    private RegisterViewController controller;
    private User user;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    @Test
    public void testController(){
        assertNotNull(this.controller);
        assertNull(this.user);
    }

    @Test
    public void verifyTextFields(FxRobot robot){
        assertEquals(controller.emailSignUp.getText(), "");
        robot.clickOn("#emailSignUp");
        robot.write("abc");
        assertEquals(controller.emailSignUp.getText(), "abc");

        assertEquals(controller.firstNameSignUp.getText(), "");
        robot.clickOn("#firstNameSignUp");
        robot.write("def");
        assertEquals(controller.firstNameSignUp.getText(), "def");

        assertEquals(controller.lastNameSignUp.getText(), "");
        robot.clickOn("#lastNameSignUp");
        robot.write("ghi");
        assertEquals(controller.lastNameSignUp.getText(), "ghi");

        assertEquals(controller.passwordSignUp.getText(), "");
        robot.clickOn("#passwordSignUp");
        robot.write("jkl");
        assertEquals(controller.passwordSignUp.getText(), "jkl");

        robot.clickOn("#signup");
        assertEquals(controller.emailSignUp.getText(), "abc");
        assertEquals(controller.firstNameSignUp.getText(), "def");
        assertEquals(controller.lastNameSignUp.getText(), "ghi");
        assertEquals(controller.passwordSignUp.getText(), "jkl");
    }
}
