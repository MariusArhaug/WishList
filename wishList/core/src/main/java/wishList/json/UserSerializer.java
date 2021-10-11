package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import wishList.core.User;
import wishList.core.WishList;

import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {


  @Override
  public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
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
