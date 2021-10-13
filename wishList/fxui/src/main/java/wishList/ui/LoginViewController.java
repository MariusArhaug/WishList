package wishList.ui;

import java.io.IOException;
import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import wishList.core.User;
import wishList.json.JsonHandler;



public class LoginViewController extends AbstractController {
    @FXML
    private TextField loginEmailInput;
    @FXML
    private TextField loginPasswordInput;
    @FXML
    private Label errorMessage;

    private JsonHandler jsonHandler;

    public void initialize() {
        this.jsonHandler = new JsonHandler();
    }

    /**
     * Change scene to RegisterView.fxml
     *
     * @param event gets state
     * @throws IOException if file is not found
     */
    @FXML
    public void changeToRegisterView(ActionEvent event) throws IOException {
       this.changeScene("/wishList/ui/RegisterView.fxml", event, this.user);
    }

    /**
     * Change scene to LoginView.fxml
     *
     * @param event gets state
     * @throws IOException if file is not found
     */
    @Override
    public void changeToLoginView(ActionEvent event) throws IOException {
        this.changeScene("/wishList/ui/LoginView.fxml", event, this.user);
    }

    /**
     * Change scene to MainView.fxml
     * Will only change scene if e-mail and password match
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void changeToMainView(ActionEvent event) throws IOException {
        String email = loginEmailInput.getText();
        String password = loginPasswordInput.getText();

        try {
            Optional<User> tryUser = jsonHandler.loadUser(email, password);
            if (tryUser.isPresent()) {
                this.user = tryUser.get();
                System.out.println(this.user.getFirstName());
                this.changeScene("/wishList/ui/MainView.fxml", event, this.user);
            } else {
                errorMessage.setText("E-mail or password is incorrect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * Change scene to ShowListView.fxml
     *
     * @param event gets state
     * @throws IOException if file is not found
     */
    @Override
    public void changeToShowListView(ActionEvent event) throws IOException {
        this.changeScene("/wishList/ui/ShowListView.fxml", event, this.user);
    }

}

