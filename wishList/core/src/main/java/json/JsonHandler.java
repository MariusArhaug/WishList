package json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public abstract class JsonHandler {

  protected ObjectMapper mapper = new ObjectMapper();

  protected  <T> List<T> loadJsonList(String path) throws IOException {
    return mapper.readValue(new File(path), new TypeReference<List<T>>(){});
  }
}
