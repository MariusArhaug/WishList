package wishList.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.IOException;

/** Wish deserializer from JSON. */
public class WishDeserializer extends JsonDeserializer<Wish> {
  /**
   * Deserialize wish from JSON.
   *
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

  Wish deserializeWish(JsonNode node) {
    Wish wish = new Wish(node.get("name").asText());
    JsonNode belongToNode = node.get("belongTo");
    if (belongToNode instanceof ObjectNode) {
      WishList wishList;
      if (!belongToNode.get("name").isNull()) {
        wishList = new WishList(belongToNode.get("name").asText());
        wish.setBelongTo(wishList);
      }
    }
    return wish;
  }
}
