package wishList.core;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
  private User john;
  private User jane;

  @BeforeEach
  public void SetUp() {
    john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
    jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
  }

  @AfterEach
  public void TearDown() {
    jane = null;
    john = null;
  }

  @Test
  public void TestUserConstructor() {
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
  public void TestGetFirstName() {
    assertEquals(john.getFirstName(), "John");
  }

  @Test
  public void TestGetLastName() {
    assertEquals(john.getLastName(), "Smith");
  }

  @Test
  public void TestGetEmail() {
    assertEquals(john.getEmail(), "John.Smith@gmail.com");
  }

  @Test
  public void TestGetPassword() {
    assertEquals(john.getPassword(), "!Password123");
  }

  @Test
  public void TestSetFirstName() {
    assertThrows(IllegalArgumentException.class, () -> jane.setFirstName(""));
    john.setFirstName("Jon");
    assertEquals(john.getFirstName(), "Jon");
  }

  @Test
  public void TestSetLastName() {
    assertThrows(IllegalArgumentException.class, () -> jane.setLastName(""));
    john.setLastName("Smitt");
    assertEquals(john.getLastName(), "Smitt");
  }

  @Test
  public void TestSetEmail() {
    john.setEmail("john.sm7@mail123.no");
    assertEquals(john.getEmail(), "john.sm7@mail123.no");
    assertThrows(IllegalArgumentException.class, () -> jane.setLastName(""));
  }

  @Test
  public void TestSetPassword() {
    john.setPassword("asdfgh89210");
    assertEquals(john.getPassword(), "asdfgh89210");
    john.setPassword("gedcmz94");
    assertEquals(john.getPassword(), "gedcmz94");
    assertThrows(IllegalArgumentException.class, () -> jane.setPassword(""));
    assertThrows(IllegalArgumentException.class, () -> john.setPassword("sdfset7"));
  }


  @Test
  public void getContacts() {
    john.addContact(jane);
    assertEquals("Jane.Doe@gmail.com", john.getContacts().get(0));
  }

  @Test
  public void getInvitedWishLists() {
    List<WishList> emptyInvitedList = new ArrayList<>();
    assertEquals(john.getInvitedWishLists(), emptyInvitedList);
    jane.makeWishList("Baby shower");
    jane.addContact(john);
    List<User> group = new ArrayList<>();
    group.add(john);
    jane.shareWishList(jane.getNthOwnedWishList(0), group);
    assertEquals(
        john.getInvitedWishLists().toString(),
        "[Baby shower,Jane,Doe,Jane.Doe@gmail.com,123Password!,[]]");
  }

  @Test
  public void getOwnedWishLists() {
    List<WishList> emptyOwnedList = new ArrayList<>();
    assertEquals(john.getOwnedWishLists(), emptyOwnedList);
    john.makeWishList("Wedding");
    assertEquals(
            john.getOwnedWishLists().toString(),
            "[Wedding,John,Smith,John.Smith@gmail.com,!Password123,[]]");
  }

  @Test
  public void shareWishList() {
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
  public void addContact() {
    jane.addContact(john);
    assertEquals("John.Smith@gmail.com", jane.getContacts().get(0));
  }

  @Test
  public void removeContact() {
    jane.addContact(john);
    jane.removeContact(john);
    assertEquals(jane.getContacts().toString(), "[]");
    assertEquals(john.getContacts().toString(), "[]");
  }

  @Test
  public void TestGetWishListsAndToString() {
    List<WishList> emptyOwnList = new ArrayList<>();
    assertEquals(john.getOwnedWishLists(), emptyOwnList);
    john.makeWishList("Baby shower");
    assertEquals("Baby shower", john.getNthOwnedWishList(0).getName());
  }

  @Test
  public void TestAddWish() {
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
  public void TestRemoveWish() {
    john.makeWishList("Christmas");
    john.addWish(john.getNthOwnedWishList(0), new Wish("Book"));
    assertTrue(john.getWishList("Christmas").isPresent());

    assertEquals(
        john.getWishList("Christmas").get().toString(),
        "Christmas,John,Smith,John.Smith@gmail.com,!Password123,[Book]");
    john.removeWish("Christmas", "Book");
    assertEquals(
        john.getWishList("Christmas").get().toString(),
        "Christmas,John,Smith,John.Smith@gmail.com,!Password123,[]");
  }

  @Test
  public void TestMakeWishList() {
    john.makeWishList("Christmas");
    assertEquals(
        john.getNthOwnedWishList(0).toString(),
        "Christmas,John,Smith,John.Smith@gmail.com,!Password123,[]");
  }

  @Test
  public void TestRemoveWishList() {
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
