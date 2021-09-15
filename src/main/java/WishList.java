import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishList {

  private final List<Item> items = new ArrayList<>();

  public WishList(Item ... items) {
    Collections.addAll(this.items, items);
  }

}
