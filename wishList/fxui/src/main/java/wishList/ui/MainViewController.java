package wishList.ui;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/** controller for main view. */
public class MainViewController extends AbstractController {
  @FXML private Button signOut;
  @FXML private Label mainNameOfUser;

  public void initialize() {
    if (this.user != null) {
      mainNameOfUser.setText(this.user.getFirstName());
    }
  }





}
