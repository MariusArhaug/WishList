package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.User;
import core.WishList;

import java.io.IOException;


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

    jsonGenerator.writeArrayFieldStart("wishLists");
    for (WishList list : user.getWishLists()) {
      jsonGenerator.writeObject(list);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }
}
