import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import json.JsonHandler;

import java.io.IOException;

public class WishListApp extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("WishListApp");
        Parent parent = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
        primaryStage.setScene(new Scene(parent));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    /*public WishListController controllerMain;
    public WishListController controller2;
    public WishListController controllerDisplay;
    public WishListController controllerLogIn;
    public WishListController controllerNewUser;
    private Stage primaryStage;


    @Override
    public void start(final Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        // Load fxml files
        controllerLogIn = load("LoginView.fxml");
        controllerNewUser = load("RegisterView.fxml");
        controllerMain = load("MainView.fxml");
        // Will be removed
        controller2 = load("CreateListView.fxml");
        controllerDisplay = load("ShowListView.fxml");

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

    public static void main(final String[] args) {
        launch(args);
    }*/
    /*@Override
    public void start(Stage stage) throws Exception {
        System.out.println(getClass().getResource("MainView.fxml"));
        Parent parent = FXMLLoader.load(getClass().getResource("MainView.fxml"));
        stage.setScene(new Scene(parent));
        stage.show();
    }

    public static void main(String[] args) {
        launch(WishListApp.class, args);
    }*/

}

