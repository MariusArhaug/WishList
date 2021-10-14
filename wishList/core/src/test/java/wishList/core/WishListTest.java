package wishList.core;

import wishList.core.User;
import wishList.core.Wish;
import wishList.core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class WishListTest {
    User john;
    WishList wishList;

    @BeforeEach
    void setUp() {
        john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
        wishList = new WishList("Birthday", john);
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        john = null;
    }

    @Test
    void WishList() {
        assertThrows(IllegalArgumentException.class, () ->
            new WishList("", john)
        );
        assertThrows(IllegalArgumentException.class, () ->
            new WishList("MoreThanTwentyFiveCharacters!", john)
        );

        assertThrows(IllegalArgumentException.class, () -> {
            WishList wishList = new WishList("OneOwner", john);
            wishList.setOwner(new User(
                    "dave",
                    "smith",
                    "Dave.Smith@gmail.com",
                    "!Password123"));
            }
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

    @Test
    void getWishTest() {
        Wish wishOne = new Wish("Car");
        Wish wishTwo = new Wish("Door");
        Wish wishThree = new Wish("Ice");
        wishList.addWish(wishOne);
        wishList.addWish(wishTwo);
        wishList.addWish(wishThree);


        Optional<Wish> optionalWishOne= wishList.getWish("Car");
        assertTrue(optionalWishOne.isPresent());

        Optional<Wish> optionalWishTwo = wishList.getWish("Not here");
        assertFalse(optionalWishTwo.isPresent());


    }
}