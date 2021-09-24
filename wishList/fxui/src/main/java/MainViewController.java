import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainViewController extends AbstractController{
    @FXML private Button signOut;


    @Override
    public void initialize() {

    }

    /**
     * Change scene to MainView.fxml
     * @param event gets state
     * @throws IOException if the file is not found
     */
    @Override
    public void changeToMainView(ActionEvent event) throws IOException {
        this.changeScene("MainView.fxml", event);
    }


    /**
     * Change scene to RegisterView.fxml
     * @param event gets state
     * @throws IOException if the file is not found
     */
    @Override
    public void changeToRegisterView(ActionEvent event) throws IOException {
        this.changeScene("RegisterView.fxml", event);
    }

    /**
     * Change scene to LoginView.fxml
     * @param event gets state
     * @throws IOException if file is not found
     */
    @Override
    public void changeToLoginView(ActionEvent event) throws IOException {
        this.changeScene("LogInView.fxml", event);
    }

    //Change scene to ShowListView.fxml

    /**
     * Change scene to ShowListView.fxml
     * @param event gets state
     * @throws IOException if file is not found
     */
    @Override
    public void changeToShowListView(ActionEvent event) throws IOException {
        this.changeScene("ShowListView.fxml", event);
    }

    /**
     * Close app
     */
    public void closeApp(){
        Stage stage = (Stage) signOut.getScene().getWindow();
        stage.close();
    }
}
