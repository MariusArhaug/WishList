package wishList.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import wishList.core.User;
import wishList.core.WishList;

/**
Deserialize user JSON object into Java User object.
 */
public class UserDeserializer extends JsonDeserializer<User> {

  private final WishListDeserializer wishListDeserializer = new WishListDeserializer();

  /**
   * Deserialize user object from wishList.json.
   *
   * @param jsonParser what parser we use
   * @param deserializationContext context
   * @return user object
   * @throws IOException file not found
   */
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
  private User deserializeUser(JsonNode node, boolean loadDetails) {
    String firstName = node.get("firstName").asText();
    String lastName = node.get("lastName").asText();
    String email = node.get("email").asText();
    String password = node.get("password").asText();
    User newUser = new User(firstName, lastName, email, password);
    JsonNode ownedWishListsNode = node.get("ownedWishLists");
    if (ownedWishListsNode instanceof ArrayNode && loadDetails) {
      for (JsonNode ownedWishListNode : ownedWishListsNode) {
        WishList wishList = wishListDeserializer.deserializeWishList(ownedWishListNode);
        if (wishList != null) {
          newUser.addWishList(wishList);
        }
      }
    }
    JsonNode invitedWishListsNode = node.get("invitedWishLists");
    if (invitedWishListsNode instanceof ArrayNode && loadDetails) {
      for (JsonNode initedWishListNode : invitedWishListsNode) {
        WishList wishList = wishListDeserializer.deserializeWishList(initedWishListNode);
        if (wishList != null) {
          newUser.getInvitedWishLists().add(wishList);
        }
      }
    }
    JsonNode groupsNode = node.get("wishListGroups");
    if (groupsNode instanceof ArrayNode && loadDetails) {
      for (JsonNode groupNode : groupsNode) {
        List<User> group = new ArrayList<>();
        for (JsonNode groupMember : groupNode) {
          String firstNameMember = groupMember.get("firstName").asText();
          String lastNameMember = groupMember.get("lastName").asText();
          String emailMember = groupMember.get("email").asText();
          String passwordMember = groupMember.get("password").asText();
          User member = new User(firstNameMember, lastNameMember, emailMember, passwordMember);
          group.add(member);
        }
        newUser.getWishListGroups().add(group);
      }
    }
    JsonNode contactsNode = node.get("contacts");
    if (contactsNode instanceof ArrayNode && loadDetails) {
      for (JsonNode contactNode : contactsNode) {
        String firstNameContact = contactNode.get("firstName").asText();
        String lastNameContact = contactNode.get("lastName").asText();
        String emailContact = contactNode.get("email").asText();
        String passwordContact = contactNode.get("password").asText();
        User contact = new User(firstNameContact, lastNameContact, emailContact, passwordContact);
        newUser.addContact(contact);
      }
    }
    return newUser;
  }
}
