package wishList.core;

import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WishListTest {
  private User john;
  private WishList wishList;

  @BeforeEach
  public void setUp() {
    john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
    wishList = new WishList("Birthday", john);
  }

  @AfterEach
  public void tearDown() {
    wishList = null;
    john = null;
  }

  @Test
  public void WishList() {
    assertThrows(IllegalArgumentException.class, () -> new WishList("", john));

    assertThrows(
        IllegalArgumentException.class,
        () -> {
          WishList wishList = new WishList("OneOwner", john);
          wishList.setOwner(new User("dave", "smith", "Dave.Smith@gmail.com", "!Password123"));
        });
  }

  @Test
  public void getWishes() {
    Wish wish = new Wish("Car");
    wishList.addWish(wish);
    assertEquals(wishList.getWishes().get(0).getName(), "Car");
  }

  @Test
  public void getName() {
    assertEquals(wishList.getName(), "Birthday");
  }

  @Test
  public void getOwner() {
    assertEquals(wishList.getOwner(), john);
  }

  @Test
  public void addWish() {
    Wish wishOne = new Wish("Car");
    Wish wishTwo = new Wish("Bicycle");
    wishList.addWish(wishOne);
    wishList.addWish(wishTwo);
    assertEquals(wishList.getWishes().get(1).getName(), "Bicycle");
  }

  @Test
  public void removeWish() {
    Wish wish = new Wish("Car");
    wishList.addWish(wish);
    wishList.removeWish(wishList.getWishes().get(0));
    assertEquals(wishList.getWishes().toString(), "[]");
  }

  @Test
  public void getWishTest() {
    Wish wishOne = new Wish("Car");
    Wish wishTwo = new Wish("Door");
    Wish wishThree = new Wish("Ice");
    wishList.addWish(wishOne);
    wishList.addWish(wishTwo);
    wishList.addWish(wishThree);

    Optional<Wish> optionalWishOne = wishList.getWish("Car");
    assertTrue(optionalWishOne.isPresent());

    Optional<Wish> optionalWishTwo = wishList.getWish("Not here");
    assertFalse(optionalWishTwo.isPresent());
  }
}
