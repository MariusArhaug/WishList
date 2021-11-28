package wishList.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.matcher.control.LabeledMatchers;
import org.testfx.util.WaitForAsyncUtils;
import wishList.core.User;
import wishList.json.JsonHandler;
import wishList.utils.Utils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class LoginViewControllerTest extends AbstractTestFxui {
  private LoginViewController controller;
  private JsonHandler jsonHandler;
  private User user;

  /**
   * @param stage stage
   * @throws Exception if file is not found
   *     <p>Load fxml file, get controller from file
   */
  @Override
  public void start(final Stage stage) throws Exception {
    final FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginViewTest.fxml"));
    final Parent root = loader.load();
    this.controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  /** Check if user is null */
  @BeforeEach
  public void setup() throws Exception {
    Utils.resetFile(MainViewControllerTest.directory, "jane@doecom.json");
    this.jsonHandler = new JsonHandler(MainViewControllerTest.directory);
    jsonHandler.addUser(new User("Jane", "Doe", "jane@doe.com", "qwerty123"));
  }
  @AfterEach
  public void tearDown() {
    this.user = null;
    jsonHandler = null;
  }

  /** Test that attributes' states are correct */
  @Test
  public void testController() {
    assertNotNull(this.controller);
    assertNull(this.user);
  }

  /**
   * Check that the label that displays error message works as planned.
   *
   */
  @Test
  public void verifyThatLabelChanges() {
    FxAssert.verifyThat("#errorMessage", LabeledMatchers.hasText(""));
    assertEquals(controller.errorMessage.getText(), "");

    clickOn("#logInButton");

    FxAssert.verifyThat(
            "#errorMessage", LabeledMatchers.hasText("E-mail or password is incorrect"));
    FxAssert.verifyThat(
            controller.errorMessage, LabeledMatchers.hasText("E-mail or password is incorrect"));
    assertEquals(controller.errorMessage.getText(), "E-mail or password is incorrect");
  }

  /**
   * Test if existing user can be found in json file
   * The user was added before the tests
   */
  @Test
  public void verifyThatUserExists() {
    clickOn("#loginEmailInput");
    write("jane@doe.com");
    clickOn("#loginPasswordInput");
    write("qwerty123");
    clickOn("#logInButton");
    WaitForAsyncUtils.waitForFxEvents();
    assertNotNull(findSceneRootWithId("addList"));
    assertEquals(controller.errorMessage.getText(), "");

    controller.checkUser();
    this.user = controller.user;
    assertNotNull(this.user);
    assertEquals(controller.loginEmailInput.getText(), this.user.getEmail());
    assertEquals(controller.loginPasswordInput.getText(), this.user.getPassword());
  }

  /**
   * Test if text fields work correctly
   *
   */
  @Test
  public void testTextFields() {
    assertEquals(controller.loginEmailInput.getText(), "");
    clickOn("#loginEmailInput");
    write("Hello");
    assertEquals(controller.loginEmailInput.getText(), "Hello");
    clickOn("#logInButton");
    assertEquals(controller.loginEmailInput.getText(), "Hello");

    assertEquals(controller.loginPasswordInput.getText(), "");
    clickOn("#loginPasswordInput");
    write("there");
    assertEquals(controller.loginPasswordInput.getText(), "there");
    clickOn("#logInButton");
    assertEquals(controller.loginPasswordInput.getText(), "there");
  }

  /**
   * Test that newUserButton changes to correct scene
   */
  @Test
  public void testNewUserButton(){
    clickOn("#newUserButton");
    WaitForAsyncUtils.waitForFxEvents();
    assertNotNull(findSceneRootWithId("registerPane"));
  }
}
