
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import json.JsonLoader;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class WishListController {
    public AnchorPane addList;
    public TextField addNewList;
    @FXML private TextField firstNameSignUp;
    @FXML private TextField lastNameSignUp;
    @FXML private TextField emailSignUp;
    @FXML private TextField passwordSignUp;
    

    @FXML private Label outputSignup;

    private User currentUser;
    //private JsonLoader jsonLoader = new JsonLoader();
    private ObjectMapper mapper = new ObjectMapper();

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
    public WishListController() {
        //this.jsonLoader = new JsonLoader();
    }
    public void show() {
        application.show(scene);
    }

    public void goToAddList() {
        application.controller2.show();
    }

    @FXML
    public void signUpUser() {
        String firstname = firstNameSignUp.getText();
        String lastname = lastNameSignUp.getText();
        String emailSignup = emailSignUp.getText();
        String password = passwordSignUp.getText();
        try {
            //currentUser = jsonLoader.addUser(firstname, lastname, emailSignup, password);
            currentUser = addUser(firstname, lastname, emailSignup, password);

            goToMainScreen();
        } catch (Exception e) {
            //outputSignup.setText(e.toString());;
            e.printStackTrace();
        }
    }

    private List<User> loadJsonList(File file) throws IOException {
        return mapper.readValue(file, new TypeReference<List<User>>(){});
    }

    public User addUser(String firstname, String lastname, String email, String password) throws IllegalArgumentException, Exception {
        try {
            List<User> users = loadJsonList(new File(Paths.get("").toAbsolutePath() + "/src/main/resources/users.json"));
            System.out.println(users);



            for (User user : users) {
                if (user.getEmail().equals(email)) {
                    throw new IllegalArgumentException(
                        "An user with this email already exists, please try another one"
                    );
                }
            }
            User newUser = new User(firstname, lastname, email, password);

            users.add(newUser);
            //System.out.println(Paths.get("").toAbsolutePath() + "/src/main/resources/users.json");
            mapper.writeValue(new File(Paths.get("").toAbsolutePath() + "/src/main/resources/users.json"), users);
            return newUser;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public void goToMainScreen() {
        List<String> testListe = new ArrayList<>();
        testListe.add("Jul");
        testListe.add("Bursdag");
        testListe.add("Konfirmasjon");
        ObservableList<String> items = FXCollections.observableArrayList();

        items.addAll(testListe);
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

        application.controllerDisplay.chosenList.setItems(items);
    }

    public void logIn() {
        
        boolean temp = false;
        if(temp){
            //Show error
        } else {
            // Needs backend logic to show this user's data
            application.controllerMain.show();
        }
    }

    public void goToLogIn(){
        application.controllerLogIn.show();
    }

    public void newUser(){
        application.controllerNewUser.show();
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

