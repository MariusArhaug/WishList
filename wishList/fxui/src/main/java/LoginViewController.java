import core.User;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import json.JsonHandler;


import java.io.IOException;
import java.util.Optional;


public class LoginViewController extends AbstractController {
    @FXML private TextField loginEmailInput;
    @FXML private TextField loginPasswordInput;
    @FXML private Label errorMessage;

    private JsonHandler jsonHandler;

    public void initialize() {
        this.jsonHandler = new JsonHandler();
    }


    @FXML
    public void changeToRegisterView(ActionEvent event) throws IOException {
       this.changeScene("RegisterView.fxml", event);
    }

    @Override
    public void changeToLoginView(ActionEvent event) throws IOException {
        this.changeScene("LogInView.fxml", event);
    }


    @FXML
    public void changeToMainView(ActionEvent event) throws IOException {
        String email = loginEmailInput.getText();
        String password = loginPasswordInput.getText();

        try {
            Optional<User> user = jsonHandler.loadUser(email, password);
            if (user.isPresent()) {
                this.changeScene("MainView.fxml", event);
                this.updateUser(user.get());
            }
            else {
                errorMessage.setText("E-mail or password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void changeToCreateListView(ActionEvent event) throws IOException {
        this.changeScene("CreateListView.fxml", event);

    }

    @Override
    public void changeToShowListView(ActionEvent event) throws IOException {
        this.changeScene("ShowListView.fxml", event);
    }

}

