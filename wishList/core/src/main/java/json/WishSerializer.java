package json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Wish;

import java.io.IOException;

public class WishSerializer extends JsonSerializer<Wish> {
  @Override
  public void serialize(Wish wish, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("name", wish.getName());
    jsonGenerator.writeObjectField("belongTo", wish.getBelongTo());

  }
}
