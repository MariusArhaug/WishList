package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import wishList.core.User;
import wishList.core.WishList;

/** Class for serialization of users. */
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
    for (WishList w : user.getOwnedWishLists()) {
      jsonGenerator.writeObject(w);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeArrayFieldStart("invitedWishLists");
    for (WishList w : user.getInvitedWishLists()) {
      jsonGenerator.writeObject(w);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeArrayFieldStart("contacts");
    for (String email : user.getContacts()) {
      jsonGenerator.writeString(email);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }
}
