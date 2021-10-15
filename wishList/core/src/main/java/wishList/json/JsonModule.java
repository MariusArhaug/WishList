package wishList.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;

/** Json Module for adding serializers/deserializers. */
public class JsonModule extends SimpleModule {

  private static final String NAME = "JSON_MODULE";

  /** constructor. */
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
