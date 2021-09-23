
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.io.IOException;


public class LoginViewController extends AbstractController {
    @FXML private TextField loginNameInput;
    @FXML private TextField loginPasswordInput;


    @FXML
    public void changeToRegisterView(ActionEvent event) throws IOException {
       this.changeScene("RegisterView.fxml", event);
    }

    @Override
    public void changeToLoginView(ActionEvent event) throws IOException {

    }


    @FXML
    public void changeToMainView(ActionEvent event) throws IOException {
        this.changeScene("MainView.fxml", event);
    }


    /*
    @FXML private Label outputSignup;

    //private User currentUser = new User("1111", "1111", "", "");
    //private final WishList test = new WishList("test");

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
    //}

}

