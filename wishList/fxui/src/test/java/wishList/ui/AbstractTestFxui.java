package wishList.ui;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.testfx.framework.junit5.ApplicationTest;

/**
 * Abstract class with findSceneRootWithId that the test classes inherit
 */
public abstract class AbstractTestFxui extends ApplicationTest {
    /**
     * Method for checking if a fxml file with fx:id="id" exists
     *
     * @param id fx:id of the fxml file the method is trying to find
     * @return if the method returns null, the tests will fail
     */
    public Parent findSceneRootWithId(String id) {
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage && window.isShowing()) {
                var root = window.getScene().getRoot();
                if (id.equals(root.getId())) {
                    return root;
                }
            }
        }
        return null;
    }

}
