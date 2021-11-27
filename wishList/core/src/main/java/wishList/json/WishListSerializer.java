package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import wishList.core.Wish;
import wishList.core.WishList;

import java.io.IOException;

/** Serializer for wishList. */
public class WishListSerializer extends JsonSerializer<WishList> {
  @Override
  public void serialize(
      WishList wishList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("name", wishList.getName());
    jsonGenerator.writeArrayFieldStart("wishes");
    for (Wish wish : wishList.getWishes()) {
      jsonGenerator.writeObject(wish);
    }
    jsonGenerator.writeEndArray();
    jsonGenerator.writeEndObject();
  }
}
