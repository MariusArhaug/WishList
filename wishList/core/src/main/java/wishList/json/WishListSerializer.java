package wishList.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import wishList.core.Wish;
import wishList.core.WishList;


public class WishListSerializer extends JsonSerializer<WishList> {
  @Override
  public void serialize(
      WishList wishList, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
      throws IOException {
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("name", wishList.getName());
    jsonGenerator.writeBooleanField("hideInfoFromOwner", wishList.getHideInfoFromOwner());
    jsonGenerator.writeObjectField("owner", wishList.getOwner());
    jsonGenerator.writeArrayFieldStart("wishes");
    for (Wish wish : wishList.getWishes()) {
      jsonGenerator.writeObject(wish);
    }
    jsonGenerator.writeEndArray();
  }
}
