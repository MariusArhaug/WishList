package wishList.ui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.base.NodeMatchers;
import org.testfx.matcher.control.ButtonMatchers;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.matcher.control.TextMatchers;
import org.testfx.toolkit.impl.ToolkitServiceImpl;
import wishList.core.User;
import wishList.json.JsonHandler;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class LoginViewControllerTest extends ApplicationTest{
    private LoginViewController controller;
    private JsonHandler jsonHandler= new JsonHandler();
    private User user;

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }


    @BeforeEach
    public void setup(FxRobot robot){
        //TextField inputEmail = (TextField) lookup("#loginEmailInput");
        //System.out.println(inputEmail);
    }

    @Test
    public void testController(){
        assertNotNull(this.controller);
    }

    @Test
    public void testTextOnButtons(){
        FxAssert.verifyThat("#login", LabeledMatchers.hasText("Log in"));
        FxAssert.verifyThat("#signup", LabeledMatchers.hasText("Sign up"));
    }


    @Test
    public void verifyThatLabelChanges(FxRobot robot){
        FxAssert.verifyThat("#errorMessage", LabeledMatchers.hasText("Label"));
        robot.clickOn("#login");
        FxAssert.verifyThat("#errorMessage", LabeledMatchers.hasText("E-mail or password is incorrect"));
        FxAssert.verifyThat(controller.errorMessage, LabeledMatchers.hasText("E-mail or password is incorrect"));
    }

    //Problemet er at den accesser feil json-fil

    /*
    @Test
    public void verifyThatUserExists(FxRobot robot){
        robot.clickOn("#loginEmailInput");
        robot.write("jane@doe");
        robot.clickOn("#loginPasswordInput");
        robot.write("qwerty");
        robot.clickOn("#login");

        assertNotNull(user);
    } */

    @Test
    public void verifyTextFields(FxRobot robot){
        assertEquals(controller.loginEmailInput.getText(), "");
        robot.clickOn("#loginEmailInput");
        robot.write("Hello");
        assertEquals(controller.loginEmailInput.getText(), "Hello");

        assertEquals(controller.loginPasswordInput.getText(), "");
        robot.clickOn("#loginPasswordInput");
        robot.write("there");
        assertEquals(controller.loginPasswordInput.getText(), "there");
    }

}
