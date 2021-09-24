import javafx.event.ActionEvent;

import java.io.IOException;

public class MainViewController extends AbstractController{


    @Override
    public void initialize() {

    }

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
