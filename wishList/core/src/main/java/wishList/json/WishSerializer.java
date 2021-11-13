package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import wishList.core.Wish;


/**
 * Class WishSerializer. It extends JsonSerializer.
 */
public class WishSerializer extends JsonSerializer<Wish> {
  @Override
  public void serialize(
      Wish wish, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("name", wish.getName());
    System.out.println("1");
    jsonGenerator.writeObjectField("belongTo", wish.getBelongTo());
    System.out.println("2");
    jsonGenerator.writeEndObject();
  }
}
