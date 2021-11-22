package wishList.core;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
  private User john;
  private User jane;
  private Iterator<WishList> iterator;
  private WishList element;
  
  @BeforeEach
  void SetUp() {
        john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
        jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
  }

  @AfterEach
  void TearDown() {
    jane = null;
    john = null;
    iterator = null;
    element = null;
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
    System.out.println(john.getContacts());
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
    jane.shareWishList(jane.iterator().next(), group);
    assertEquals(john.getInvitedWishLists().toString(), "[Baby shower,Jane,Doe,Jane.Doe@gmail.com,123Password!]");
  }

  @Test
  void shareWishList() {
    john.makeWishList("Christmas");
    jane.addContact(john);
    List<User> group = new ArrayList<>();
    group.add(jane);
    element = john.iterator().next();
    john.shareWishList(element, group);
    assertThrows(IllegalCallerException.class, () -> {
      jane.shareWishList(element, group);
    });
    assertEquals(jane.getInvitedWishLists().get(0), element);
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
    List<WishList> iteratorList = new ArrayList<>();
    john.iterator().forEachRemaining(iteratorList::add);
    assertEquals(iteratorList, emptyOwnList);
    john.makeWishList("Baby shower");
    assertEquals("Baby shower", john.iterator().next().getName());
  }

  @Test
  void TestAddWish() {
    WishList wishList = new WishList("Christmas");
    john.addWishList(wishList);
    iterator = john.iterator();
    element = iterator.next();
    assertEquals(
        wishList.getWishes().toString(), element.getWishes().toString());

    Wish wish = new Wish("Book");
    wishList.addWish(wish);
    john.addWish(element, wish);
    assertEquals(
        wishList.getWishes().toString(), element.getWishes().toString());

    john.removeWishList("Christmas");
    assertFalse(john.iterator().hasNext());

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
    iterator = john.iterator();
    element = iterator.next();
    assertEquals(wishList.toString(), element.toString());
    john.removeWish("Christmas", "Book");
    wishList.removeWish(wish);
    iterator = john.iterator();
    element = iterator.next();
    assertEquals(wishList.toString(), element.toString());
  }

  @Test
  void TestMakeWishList() {
    john.makeWishList("Christmas");
    iterator = john.iterator();
    assertEquals(
            iterator.next().toString(),
        "Christmas,John,Smith,John.Smith@gmail.com,!Password123");
  }

  @Test
  void TestRemoveWishList() {
    john.makeWishList("Christmas");
    assertTrue(john.getWishList("Christmas").isPresent());
    iterator = john.iterator();
    element = iterator.next();
    assertThrows(
        IllegalArgumentException.class, () -> jane.removeWishList(element.getName()));
    john.removeWishList(john.getWishList("Christmas").get().getName());
    assertFalse(john.iterator().hasNext());

    john.makeWishList("Dinner");
    iterator = john.iterator();
    element = iterator.next();
    assertEquals("Dinner", element.getName());
    john.removeWishList("Dinner");
    assertFalse(john.iterator().hasNext());
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
