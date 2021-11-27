package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import wishList.core.Wish;

import java.io.IOException;

/** Class WishSerializer. It extends JsonSerializer. */
public class WishSerializer extends JsonSerializer<Wish> {
  @Override
  public void serialize(
      Wish wish, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("name", wish.getName());
    jsonGenerator.writeEndObject();
  }
}
