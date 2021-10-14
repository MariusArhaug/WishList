package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import wishList.core.Wish;



public class WishSerializer extends JsonSerializer<Wish> {
  @Override
  public void serialize(
      Wish wish, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("name", wish.getName());
    jsonGenerator.writeObjectField("belongTo", wish.getBelongTo());
    jsonGenerator.writeEndObject();
  }
}
