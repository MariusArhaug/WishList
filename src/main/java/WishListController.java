
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.application.Application;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

public class WishListController {
    @FXML ListView<String> list;
    @FXML ListView<String> chosenList;
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

    //Skyldes feilen at det er flere controllere? For denne kjøres ikke på samme controller som funksjonen over
    public void goToDisplayList(){
        application.controllerDisplay.show();
        this.setSelected(list.getSelectionModel().getSelectedItem());
        //Okei så den finner ut hva man trykker på, men den klarer ikke å sende det videre
        List<String> testListe = new ArrayList<>();
        testListe.add("1 + " + this.selected);
        testListe.add("2 + " + this.selected);
        testListe.add("3 + " + this.selected);
        ObservableList<String> items = FXCollections.observableArrayList();

        for(String i : testListe) {
            items.add(i);
        }
        application.controllerDisplay.chosenList.setItems(items);
        //setNewList(displayChosenList);
        //displayChosenList.setItems(items);
        //application.controllerDisplay.setNewList(displayChosenList);
    }

    public void setSelected(String s){
        this.selected = s;
    }

    public String getSelected(){
        return this.selected;
    }

}

/*public class WishListController {
    //Endre navn på knappen til addListButton
    @FXML private Button addList;

    /*private void onAddList(){

    }
} */
