package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import wishList.core.User;
import wishList.core.WishList;

/**
 * Class for serialization of users.
 */
public class UserSerializer extends JsonSerializer<User> {

  /**
   * Serialize user.
   *
   * @param user user to be serialized
   * @param jsonGenerator jsonGenerator to be used
   * @param serializerProvider provider
   * @throws IOException if file to serializer user to is not found
   */
  @Override
  public void serialize(
      User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("firstName", user.getFirstName());
    jsonGenerator.writeStringField("lastName", user.getLastName());
    jsonGenerator.writeStringField("email", user.getEmail());
    jsonGenerator.writeStringField("password", user.getPassword());
    jsonGenerator.writeArrayFieldStart("ownedWishLists");
    List<WishList> ownedWishLists = new ArrayList<>();
    Iterator<WishList> iteratorWishLists = user.iterator();
    iteratorWishLists.forEachRemaining(ownedWishLists::add);
    for (WishList w : ownedWishLists) {
      jsonGenerator.writeObject(w);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeArrayFieldStart("invitedWishLists");
    for (WishList w : user.getInvitedWishLists()) {
      jsonGenerator.writeObject(w);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeArrayFieldStart("contacts");
    for (User u : user.getContacts()) {
      jsonGenerator.writeObject(u.getEmail());
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }
}
