package wishList.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/** App launcher. */
public class WishListApp extends Application {
  public static void main(String[] args) {
    launch(args);
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
