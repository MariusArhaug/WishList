package wishList.restapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import wishList.json.JsonModule;

/** REST app. * */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class RESTApplication {

  public static void main(String[] args) {
    SpringApplication.run(RESTApplication.class, args);
  }

  @Bean
  public JsonModule jsonModule() {
    return new JsonModule();
  }
}
