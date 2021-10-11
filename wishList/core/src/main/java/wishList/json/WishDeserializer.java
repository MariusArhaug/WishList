package wishList.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import wishList.core.Wish;

import java.io.IOException;

public class WishDeserializer extends JsonDeserializer<Wish> {
  @Override
  public Wish deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    return deserializeWish(node);
  }

  public Wish deserializeWish(JsonNode node) {
    return new Wish(node.get("name").asText());
  }
}
