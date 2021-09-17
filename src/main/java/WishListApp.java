import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WishListApp extends Application {

    //Hvorfor trenger man 1 controller per side?
    public WishListController controllerMain;
    public WishListController controller2;
    public WishListController controllerDisplay;
    private Stage primaryStage;

    public static void main(final String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        controllerMain = load("WishList.fxml");
        controller2 = load("NewList.fxml");
        controllerDisplay = load("DisplayList.fxml");
        //controllerDisplay.setNewList(controllerDisplay.displayChosenList);

        primaryStage.setTitle("WishList");
        controllerMain.goToMainScreen();
        primaryStage.show();
    }

    //Usikker på hva "name" betyr
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

