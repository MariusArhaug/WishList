package wishList.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class WishTest {
  private User john;
  private User jane;
  private WishList wishList;

  @BeforeEach
  public void setUp() {
    john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
    jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
    wishList = new WishList("Birthday", john.getEmail());
  }

  @AfterEach
  public void tearDown() {
    wishList = null;
    jane = null;
    john = null;
  }

  @Test
  public void Wish() {
    assertThrows(IllegalArgumentException.class, () -> new Wish(""));
  }

  @Test
  public void getWish() {
    jane.makeWishList("Moving party");
    WishList element = jane.getNthOwnedWishList(0);
    element.addWish(new Wish("Surf board"));
    assertEquals(element.getWishes().get(0).getName(), "Surf board");
  }

  @Test
  public void getBelongTo() {
    wishList.addWish(new Wish("Surf board"));
    assertEquals(wishList.getWishes().get(0).getBelongTo(), wishList);
  }
}
