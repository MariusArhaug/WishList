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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
import wishList.json.JsonHandler;

@ExtendWith(ApplicationExtension.class)
public class LoginViewControllerTest extends ApplicationTest{
    private LoginViewController controller;
    private JsonHandler jsonHandler= new JsonHandler();

    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    //TextField inputEmail = (TextField) root2.lookup("#loginEmailInput");
    @BeforeEach
    public void setup(FxRobot robot){

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
    }

    /*
    @Test
    public void verifyThatUserExists(FxRobot robot){
        robot.clickOn("#login");
    } */

    @Test
    public void verifyTextFields(FxRobot robot){
        FxAssert.verifyThat("#loginEmailInput", NodeMatchers.isNotNull());
    }

}
