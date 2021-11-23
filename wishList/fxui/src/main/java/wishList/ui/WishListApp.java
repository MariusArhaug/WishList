package wishList.ui;

import java.util.Objects;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/** App launcher. */
public class WishListApp extends Application {
  public static void main(String[] args) {
    launch(args);
  }

  /**
   * Helper method used by tests needing to run headless.
   */
  public static void supportHeadless() {
    if (Boolean.getBoolean("headless")) {
      System.setProperty("testfx.robot", "glass");
      System.setProperty("testfx.headless", "true");
      System.setProperty("prism.order", "sw");
      System.setProperty("prism.text", "t2k");
      System.setProperty("java.awt.headless", "true");
    }
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    primaryStage.setTitle("WishListApp");
    Parent parent =
        FXMLLoader.load(Objects.requireNonNull(getClass().getResource("LoginView.fxml")));
    primaryStage.setScene(new Scene(parent));
    primaryStage.show();
  }
}
