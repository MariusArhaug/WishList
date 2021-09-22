import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WishListApp extends Application {

    public WishListController controllerMain;
    public WishListController controller2;
    public WishListController controllerDisplay;
    public WishListController controllerLogIn;
    public WishListController controllerNewUser;
    private Stage primaryStage;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        /** Define starting stage **/
        this.primaryStage = primaryStage;

        // Load fxml files
        controllerLogIn = load("LogIn.fxml");
        controllerNewUser = load("NewUser.fxml");
        controllerMain = load("WishList.fxml");
        // Will be removed
        controller2 = load("NewList.fxml");
        controllerDisplay = load("DisplayList.fxml");

        // Show chosen fxml file when app starts
        primaryStage.setTitle("WishList");
        controllerMain.goToLogIn();
        primaryStage.show();
    }

    //Define function that opens app
    public WishListController load(final String name) throws IOException {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource(name));
        loader.load();
        final WishListController controller = loader.getController();
        controller.configure(this, new Scene(loader.getRoot()));
        return controller;
    }

    public void show(final Scene scene) {
        primaryStage.setScene(scene);
    }
}

