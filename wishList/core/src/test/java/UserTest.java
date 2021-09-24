import core.User;
import core.WishList;
import core.Wish;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {
    User john;
    User jane;

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

        for (int i = 0; i< illegalNames.length; i++) {
            final int j = i;
            assertThrows(IllegalArgumentException.class, () ->
                new User(illegalNames[j][0], illegalNames[j][1], "", "")
            );
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
        assertEquals(john.getWishLists().toString(), "[Baby shower,John,Smith,John.Smith@gmail.com,!Password123,false]");
    }


    @Test
    void TestAddWish() {
        WishList wishList = new WishList(john, "Christmas");
        john.makeWishList("Christmas");

        assertEquals(
            wishList.getWishes().toString(),
            john.getWishLists().get(0).getWishes().toString());

        Wish wish = new Wish("Book");
        wishList.addWish(wish);
        john.addWish(john.getWishLists().get(0), wish);

        assertEquals(
            wishList.getWishes().toString(),
            john.getWishLists().get(0).getWishes().toString());
    }

    @Test
    void TestRemoveWish() {
        Wish wish = new Wish("book");
        WishList wishList = new WishList(john, "Christmas");
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
        assertEquals(john.getWishLists().toString(), "[Christmas,John,Smith,John.Smith@gmail.com,!Password123,false]");
    }

    @Test
    void TestRemoveWishList() {
        john.makeWishList("Christmas");
        assertThrows(IllegalCallerException.class, () -> jane.removeWishList(john.getWishLists().get(0)));
        john.removeWishList(john.getWishLists().get(0));
        assertEquals(john.getWishLists().toString(), "[]");
    }
}