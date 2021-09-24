import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

public class MainViewController extends AbstractController{


    @Override
    public void changeToMainView(ActionEvent event) throws IOException {
        this.changeScene("MainView.fxml", event);
    }

    @Override
    public void changeToRegisterView(ActionEvent event) throws IOException {
        this.changeScene("RegisterView.fxml", event);
    }

    @Override
    public void changeToLoginView(ActionEvent event) throws IOException {
        this.changeScene("LogInView.fxml", event);
    }

    @Override
    public void changeToShowListView(ActionEvent event) throws IOException {
        this.changeScene("ShowListView.fxml", event);
    }
}
