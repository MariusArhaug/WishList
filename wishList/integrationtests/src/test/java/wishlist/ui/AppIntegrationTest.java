package wishlist.ui;

import org.testfx.framework.junit5.ApplicationTest;

public class AppIntegrationTest extends ApplicationTest {
  // Tar dette når det blir tid
  /*
  @BeforeAll
  public static void setupHeadless() {
      WishListApp.supportHeadless();
  }

  //hvordan velge hvilken controller?
  private LoginViewController controller;

  @Override
  public void start(final Stage stage) throws Exception {
      final FXMLLoader loader = new FXMLLoader(getClass().getResource("TodoApp_it.fxml"));
      final Parent root = loader.load();
      this.controller = loader.getController();
      stage.setScene(new Scene(root));
      stage.show();
  }

  @BeforeEach
  public void setupItems() throws URISyntaxException {
      // same as in test-todolist.json (should perhaps read it instead)
      //Æææææ hva skjer her
      try (Reader reader = new InputStreamReader(getClass().getResourceAsStream("it-todomodel.json"))) {
          String port = System.getProperty("todo.port");
          Assertions.assertNotNull(port, "No todo.port system property set");
          URI baseUri = new URI("http://localhost:" + port + "/wishlist/");
          System.out.println("Base RemoteTodoModelAcces URI: " + baseUri);
          this.controller.setTodoModelAccess(new ControllerAccess(baseUri));
      } catch (IOException ioe) {
          fail(ioe.getMessage());
      }
  }

  @Test
  public void testController_initial() {
      Assertions.assertNotNull(this.controller);
  } */
}
