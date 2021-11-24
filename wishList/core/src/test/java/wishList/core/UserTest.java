package wishList.core;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
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
      {"firstName", ""},
      {"", "lastName"},
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
  void getContacts() {
    List<User> emptyContactList = new ArrayList<>();
    assertEquals(john.getContacts(), emptyContactList);
    john.addContact(jane);
    emptyContactList.add(jane);
    assertEquals(john.getContacts().toString(), emptyContactList.toString());
  }

  @Test
  void getInvitedWishLists() {
    List<WishList> emptyInvitedList = new ArrayList<>();
    assertEquals(john.getInvitedWishLists(), emptyInvitedList);
    jane.makeWishList("Baby shower");
    jane.addContact(john);
    List<User> group = new ArrayList<>();
    group.add(john);
    jane.shareWishList(jane.getNthOwnedWishList(0), group);
    assertEquals(
        john.getInvitedWishLists().toString(),
        "[Baby shower,Jane,Doe,Jane.Doe@gmail.com,123Password!]");
  }

  @Test
  void shareWishList() {
    john.makeWishList("Christmas");
    jane.addContact(john);
    List<User> group = new ArrayList<>();
    group.add(jane);
    john.shareWishList(john.getNthOwnedWishList(0), group);
    assertThrows(
        IllegalCallerException.class, () -> jane.shareWishList(john.getNthOwnedWishList(0), group));
    assertEquals(jane.getInvitedWishLists().get(0), john.getNthOwnedWishList(0));
  }

  @Test
  void addContact() {
    jane.addContact(john);
    assertEquals(jane.getContacts().toString(), "[John,Smith,John.Smith@gmail.com,!Password123]");
  }

  @Test
  void removeContact() {
    jane.addContact(john);
    jane.removeContact(john);
    assertEquals(jane.getContacts().toString(), "[]");
    assertEquals(john.getContacts().toString(), "[]");
  }

  @Test
  void TestGetWishListsAndToString() {
    List<WishList> emptyOwnList = new ArrayList<>();
    assertEquals(john.getOwnedWishLists(), emptyOwnList);
    john.makeWishList("Baby shower");
    assertEquals("Baby shower", john.getNthOwnedWishList(0).getName());
  }

  @Test
  void TestAddWish() {
    WishList wishList = new WishList("Christmas");
    john.addWishList(wishList);
    assertTrue(john.getWishList("Christmas").isPresent());
    assertEquals(
        wishList.getWishes().toString(),
        john.getWishList("Christmas").get().getWishes().toString());

    Wish wish = new Wish("Book");
    wishList.addWish(wish);
    john.addWish(wishList, wish);
    assertEquals(
        wishList.getWishes().toString(), john.getNthOwnedWishList(0).getWishes().toString());

    john.removeWishList("Christmas");

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

    assertTrue(john.getWishList("Christmas").isPresent());
    assertEquals(wishList.toString(), john.getWishList("Christmas").get().toString());
    john.removeWish("Christmas", "Book");
    wishList.removeWish(wish);
    assertEquals(wishList.toString(), john.getWishList("Christmas").get().toString());
  }

  @Test
  void TestMakeWishList() {
    john.makeWishList("Christmas");
    assertEquals(
        john.getNthOwnedWishList(0).toString(),
        "Christmas,John,Smith,John.Smith@gmail.com,!Password123");
  }

  @Test
  void TestRemoveWishList() {
    john.makeWishList("Christmas");
    assertTrue(john.getWishList("Christmas").isPresent());

    assertThrows(IllegalArgumentException.class, () -> jane.removeWishList("Christmas"));
    john.removeWishList(john.getWishList("Christmas").get().getName());
    assertEquals(0, john.getOwnedWishLists().size());

    john.makeWishList("Dinner");
    assertTrue(john.getWishList("Dinner").isPresent());
    assertEquals("Dinner", john.getWishList("Dinner").get().getName());
  }
}
