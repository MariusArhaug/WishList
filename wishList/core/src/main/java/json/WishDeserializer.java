package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import core.Wish;


import java.io.IOException;

public class WishDeserializer extends JsonDeserializer<Wish> {
  /**
   * Deserialize wish from JSON
   * @param jsonParser parser
   * @param deserializationContext context
   * @return Wish from JSON file
   * @throws IOException file not found
   */
  @Override
  public Wish deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    return deserializeWish(node);
  }


  public Wish deserializeWish(JsonNode node) {
    return new Wish(node.get("name").asText());
  }
}
