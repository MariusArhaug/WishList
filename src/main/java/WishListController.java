
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class WishListController {
    @FXML ListView<String> list;
    @FXML ListView<String> chosenList;
    @FXML TextField addItemField;
    private WishListApp application;
    private Scene scene;
    public String selected;

    public void configure(final WishListApp application, final Scene scene) {
        this.application = application;
        this.scene = scene;
    }

    public void show() {
        application.show(scene);
    }

    public void goToAddList() {
        application.controller2.show();
    }

    public void goToMainScreen() {
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
        ObservableList<String> items = FXCollections.observableArrayList();
        this.setSelected(list.getSelectionModel().getSelectedItem());
        List<String> testListe = new ArrayList<>();
        testListe.add("1 + " + this.selected);
        testListe.add("2 + " + this.selected);
        testListe.add("3 + " + this.selected);

        for(String i : testListe) {
            items.add(i);
        }
        System.out.println("Items før man legger til: " + items);
        System.out.println(this);
        application.controllerDisplay.chosenList.setItems(items);
    }

    public void setSelected(String s){
        this.selected = s;
    }

    public String getSelected(){
        return this.selected;
    }

    //Får det ikke til å fungere, men logikken blir uansett annerledes med databaser
    public void addNewItem(){
        /*
        System.out.println(this);
        application.controllerDisplay.items.add(addItemField.getText());
        System.out.println("Items etter man legger til: " + this.items);
        chosenList.setItems(application.controllerDisplay.items); */
    }

}

