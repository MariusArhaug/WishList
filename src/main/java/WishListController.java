
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class WishListController {
    @FXML ListView<String> list;
    @FXML ListView<String> chosenList;
    @FXML TextField addItemField;
    private WishListApp application;
    private Scene scene;
    // Unnecessary when db is implemented?
    public String selected;

    public void configure(final WishListApp application, final Scene scene) {
        this.application = application;
        this.scene = scene;
    }

    public void show() {
        application.show(scene);
    }

    public void addNewList(){
        // Insert code for adding new empty list to db
        // Go to main screen again to update page
        goToMainScreen();
    }

    public void goToMainScreen() {

        // Use to add something to the ListView to show how app works
        List<String> testListe = new ArrayList<>();
        testListe.add("Jul");
        testListe.add("Bursdag");
        testListe.add("Konfirmasjon");
        ObservableList<String> items = FXCollections.observableArrayList();

        for(String i : testListe) {
            items.add(i);
        }
        application.controllerMain.list.setItems(items);

        application.controllerMain.show();
    }

    public void goToDisplayList(){
        application.controllerDisplay.show();

        // Use to add something to the ListView to show how app works
        ObservableList<String> items = FXCollections.observableArrayList();

        // Store which item in ListView is chosen
        this.setSelected(list.getSelectionModel().getSelectedItem());
        List<String> testListe = new ArrayList<>();
        testListe.add("1 + " + this.selected);
        testListe.add("2 + " + this.selected);
        testListe.add("3 + " + this.selected);

        for(String i : testListe) {
            items.add(i);
        }

        application.controllerDisplay.chosenList.setItems(items);
    }

    public void logIn(){
        // Temp variable. Will be replaced with conditions such as "if field is empty"
        boolean temp = false;
        if(temp){
            //Show error
        } else {
            // Needs backend logic to show this user's data
            goToMainScreen();
        }
    }

    public void signUp(){
        //Temp variable. Will be replaced with conditions such as "if field is empty"
        boolean temp = false;
        if(temp){
            //Show error
        } else {
            //Implement logic to add new user to db
            // Needs backend logic to show this user's data
            goToMainScreen();
        }
    }

    public void goToLogIn(){
        application.controllerLogIn.show();
    }

    public void goToNewUser(){
        application.controllerNewUser.show();
    }

    // Unnecessary when backend is implemented?
    public void setSelected(String s){
        this.selected = s;
    }

    public void addNewItem(){
        //Needs backend to add item to chosen list
    }

}

