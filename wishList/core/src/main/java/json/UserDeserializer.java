package json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import core.User;
import core.WishList;

import java.io.IOException;

/** Deserialize User JSON to User object. */
public class UserDeserializer extends JsonDeserializer<User> {

  private final WishListDeserializer wishListDeserializer = new WishListDeserializer();

  @Override
  public User deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
      throws IOException {
    TreeNode userNode = jsonParser.getCodec().readTree(jsonParser);
    return deserializeUser((JsonNode) userNode, true);
  }

  /**
   * Deserialize user from JSON file.
   *
   * @param node user JSON node
   * @return User object
   */
  User deserializeUser(JsonNode node, boolean loadDetails) {
    String firstName = node.get("firstName").asText();
    String lastName = node.get("lastName").asText();
    String email = node.get("email").asText();
    String password = node.get("password").asText();
    User newUser = new User(firstName, lastName, email, password);
    JsonNode wishListsNode = node.get("wishLists");
    if (wishListsNode instanceof ArrayNode && loadDetails) {
      for (JsonNode wishListNode : wishListsNode) {
        WishList wishList = wishListDeserializer.deserializeWishList(wishListNode);
        if (wishList != null) {
          newUser.addWishList(wishList);
        }
      }
    }
    return newUser;
  }
}
