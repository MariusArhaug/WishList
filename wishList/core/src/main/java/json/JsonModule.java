package json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import core.User;
import core.Wish;
import core.WishList;

public class JsonModule extends SimpleModule {

  private static final String NAME = "JSON_MODULE";

  public JsonModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(User.class, new UserSerializer());
    addSerializer(Wish.class, new WishSerializer());
    addSerializer(WishList.class, new WishListSerializer());

    addDeserializer(User.class, new UserDeserializer());
    addDeserializer(Wish.class, new WishDeserializer());
    addDeserializer(WishList.class, new WishListDeserializer());
  }
}
