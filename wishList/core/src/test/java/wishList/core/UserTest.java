package wishList.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private User john;
  private User jane;

  @BeforeEach
  void SetUp() {
    john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
    jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
  }

  @AfterEach
  void TearDown() {
    jane = null;
    john = null;
  }

  @Test
  void TestUserConstructor() {
    String[][] illegalNames = {
      {"", ""},
      {"moreThanTwentyFiveCharacters", ""},
      {"", "moreThanTwentyFiveCharacters"},
      {"moreThanTwentyFiveCharacters", "moreThanTwentyFiveCharacters"}
    };

    for (int i = 0; i < illegalNames.length; i++) {
      final int j = i;
      assertThrows(
          IllegalArgumentException.class,
          () -> new User(illegalNames[j][0], illegalNames[j][1], "", ""));
    }
  }

  @Test
  void TestGetFirstName() {
    assertEquals(john.getFirstName(), "John");
  }

  @Test
  void TestGetLastName() {
    assertEquals(john.getLastName(), "Smith");
  }

  @Test
  void TestGetEmail() {
    assertEquals(john.getEmail(), "John.Smith@gmail.com");
  }

  @Test
  void TestGetPassword() {
    assertEquals(john.getPassword(), "!Password123");
  }

  @Test
  void TestGetWishListsAndToString() {
    List<WishList> emptyOwnList = new ArrayList<>();
    assertEquals(john.getWishLists(), emptyOwnList);
    john.makeWishList("Baby shower");
    assertEquals("Baby shower", john.getWishLists().get(0).getName());
  }

  @Test
  void TestAddWish() {
    WishList wishList = new WishList("Christmas");
    john.addWishList(wishList);

    assertEquals(
        wishList.getWishes().toString(), john.getWishLists().get(0).getWishes().toString());

    Wish wish = new Wish("Book");
    wishList.addWish(wish);
    john.addWish(john.getWishLists().get(0), wish);

    assertEquals(
        wishList.getWishes().toString(), john.getWishLists().get(0).getWishes().toString());

    john.removeWishList("Christmas");
    assertTrue(john.getWishLists().isEmpty());

    WishList wishList2 = new WishList("Second wishes");
    john.addWishList(wishList2);
    assertTrue(john.getWishList("Second wishes").isPresent());
    assertEquals(wishList2, john.getWishList("Second wishes").get());

    assertEquals(john, wishList2.getOwner());
  }

  @Test
  void TestRemoveWish() {
    Wish wish = new Wish("book");
    WishList wishList = new WishList("Christmas", john);
    wishList.addWish(wish);
    john.makeWishList("Christmas");
    john.addWish(wishList, wish);
    assertEquals(wishList.toString(), john.getWishLists().get(0).toString());
    john.removeWish("Christmas", "Book");
    wishList.removeWish(wish);
    assertEquals(wishList.toString(), john.getWishLists().get(0).toString());
  }

  @Test
  void TestMakeWishList() {
    john.makeWishList("Christmas");
    assertEquals(
        john.getWishLists().toString(),
        "[Christmas, John,Smith,John.Smith@gmail.com,!Password123, false]");
  }

  @Test
  void TestRemoveWishList() {
    System.out.println(john.getWishLists());
    john.makeWishList("Christmas");
    assertTrue(john.getWishList("Christmas").isPresent());
    assertThrows(
        IllegalCallerException.class, () -> jane.removeWishList(john.getWishLists().get(0)));
    System.out.println(john.getWishLists());
    john.removeWishList(john.getWishList("Christmas").get());
    System.out.println(john.getWishLists());
    assertTrue(john.getWishLists().isEmpty());

    john.makeWishList("Dinner");
    assertEquals("Dinner", john.getWishLists().get(0).getName());
    john.removeWishList("Dinner");
    assertTrue(john.getWishLists().isEmpty());
  }

  @Test
  void testIterable() {
    WishList wishList1 = new WishList("One");
    WishList wishList2 = new WishList("Two");
    WishList wishList3 = new WishList("Three");


    john.addWishList(wishList1);
    john.addWishList(wishList2);
    john.addWishList(wishList3);

    Iterator<WishList> iterator = john.iterator();
    assertEquals(wishList1, iterator.next());
    assertEquals(wishList2, iterator.next());
    assertEquals(wishList3, iterator.next());

  }
}
