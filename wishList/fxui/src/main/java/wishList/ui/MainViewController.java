package wishList.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController extends AbstractController {
    @FXML private Button signOut;
    @FXML private Label mainNameOfUser;


    @Override
    public void initialize() {
        if(this.user != null) {
            mainNameOfUser.setText(this.user.getFirstName());
        }
    }

    /**
     * Change scene to MainView.fxml
     * @param event gets state
     * @throws IOException if the file is not found
     */
    @Override
    public void changeToMainView(ActionEvent event) throws IOException {
        this.changeScene("/wishList/ui/MainView.fxml", event, this.user);
    }


    /**
     * Change scene to RegisterView.fxml
     * @param event gets state
     * @throws IOException if the file is not found
     */
    @Override
    public void changeToRegisterView(ActionEvent event) throws IOException {
        this.changeScene("/wishList/ui/RegisterView.fxml", event, this.user);
    }

    /**
     * Change scene to LoginView.fxml
     * @param event gets state
     * @throws IOException if file is not found
     */
    @Override
    public void changeToLoginView(ActionEvent event) throws IOException {
        this.changeScene("/wishList/ui/LoginView.fxml", event, this.user);
    }

    //Change scene to ShowListView.fxml

    /**
     * Change scene to ShowListView.fxml
     * @param event gets state
     * @throws IOException if file is not found
     */
    @Override
    public void changeToShowListView(ActionEvent event) throws IOException {
        this.changeScene("/wishList/ui/ShowListView.fxml", event, this.user);
    }

    /**
     * Close app
     */
    public void closeApp(){
        Stage stage = (Stage) signOut.getScene().getWindow();
        stage.close();
    }
}
