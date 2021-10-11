package wishList.core;

import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishListTest {
    User john;
    WishList wishList;

    @BeforeEach
    void setUp() {
        john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
        wishList = new WishList(john, "Birthday");
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        john = null;
    }

    @Test
    void WishList() {
        assertThrows(IllegalArgumentException.class, () ->
            new WishList(john, "")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new WishList(john, "MoreThanTwentyFiveCharacters!")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new WishList(null, "Wedding")
        );
    }

    @Test
    void getWishes() {
        Wish wish = new Wish("Car");
        wishList.addWish(wish);
        assertEquals(wishList.getWishes().get(0).getName(), "Car");
    }

    @Test
    void getName() {
        assertEquals(wishList.getName(), "Birthday");
    }

    @Test
    void getOwner() {
        assertEquals(wishList.getOwner(), john);
    }

    @Test
    void addWish() {
        Wish wishOne = new Wish("Car");
        Wish wishTwo = new Wish("Bicycle");
        wishList.addWish(wishOne);
        wishList.addWish(wishTwo);
        assertEquals(wishList.getWishes().get(1).getName(), "Bicycle");
    }

    @Test
    void removeWish() {
        Wish wish = new Wish("Car");
        wishList.addWish(wish);
        wishList.removeWish(wishList.getWishes().get(0));
        assertEquals(wishList.getWishes().toString(), "[]");
    }
}