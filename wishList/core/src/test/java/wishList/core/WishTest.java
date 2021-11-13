package wishList.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishTest {
  private User john;
  private User jane;
  private WishList wishList;
  private WishList element;

  @BeforeEach
  void setUp() {
    john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
    jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
    wishList = new WishList("Birthday", john);
  }

  @AfterEach
  void tearDown() {
    wishList = null;
    jane = null;
    john = null;
    element = null;
  }

  @Test
  void Wish() {
    assertThrows(IllegalArgumentException.class, () -> new Wish(""));
  }

  @Test
  void getWish() {
    jane.makeWishList("Moving party");
    WishList element = jane.iterator().next();
    element.addWish(new Wish("Surf board"));
    assertEquals(element.getWishes().get(0).getName(), "Surf board");
  }

  @Test
  void getBelongTo() {
    wishList.addWish(new Wish("Surf board"));
    assertEquals(wishList.getWishes().get(0).getBelongTo(), wishList);
  }
}
