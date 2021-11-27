package wishList.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.IOException;

/** Deserializer for WishList. */
public class WishListDeserializer extends JsonDeserializer<WishList> {

  private final WishDeserializer wishDeserializer = new WishDeserializer();

  /**
   * Deserialize wishList.
   *
   * @param jsonParser parser
   * @param deserializationContext context
   * @return wishList from JSON
   * @throws IOException if file not found
   */
  @Override
  public WishList deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    JsonNode node = jsonParser.getCodec().readTree(jsonParser);
    return deserializeWishList(node);
  }

  /**
   * Deserialize WishList from JSON to object.
   *
   * @param node wishList JSON object
   * @return wishList object
   */
  WishList deserializeWishList(JsonNode node) {
    if (node instanceof ObjectNode) {
      WishList wishList = new WishList(node.get("name").asText());
      JsonNode wishes = node.get("wishes");
      if (wishes instanceof ArrayNode) {
        for (JsonNode wishNode : wishes) {
          Wish wish = wishDeserializer.deserializeWish(wishNode);
          if (wish != null) {
            wishList.addWish(wish);
          }
        }
      }
      return wishList;
    }
    return null;
  }
}
