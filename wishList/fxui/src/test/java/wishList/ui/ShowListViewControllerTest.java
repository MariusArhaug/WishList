package wishList.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class ShowListViewControllerTest extends ApplicationTest {
    private ShowListViewController controller;

    /**
     * Load test fxml file. Get controller from file
     * @param stage
     * @throws Exception if file is not found
     */
    @Override
    public void start(final Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowListViewTest.fxml"));
        final Parent root = loader.load();
        this.controller = loader.getController();
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     * Check that controller from fxml file is not null
     */
    @Test
    public void testController(){
        assertNotNull(this.controller);
    }

}