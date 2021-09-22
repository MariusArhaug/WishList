import core.User;
import core.Wish;
import core.WishList;
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
        wishList = new WishList(john, "Birthday", new Wish[] {});
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        john = null;
    }

    @Test
    void WishList() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WishList(john, "", new Wish[] {});
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new WishList(john, "MoreThanTwenthyFiveCharacters", new Wish[] {});
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new WishList(null, "Wedding", new Wish[] {});
        });
    }

    @Test
    void getWishes() {
        wishList.addWish("Car");
        assertEquals(wishList.getWishes().get(0).getWish(), "Car");
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
    void getHideInfoFromOwner() {
        User jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
        john.addContact(jane);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.makeWishList("Moving party", new Wish[] {});

        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        assertEquals(john.getOwnWishLists().get(0).getHideInfoFromOwner(), false);
        john.changeVisibility(john.getOwnWishLists().get(0), true);
        assertEquals(john.getOwnWishLists().get(0).getHideInfoFromOwner(), true);
    }

    @Test
    void setHideInfoFromOwner() {
        User jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
        john.addContact(jane);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.makeWishList("Moving party", new Wish[] {});
        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        john.getOwnWishLists().get(0).setHideInfoFromOwner(true);
        assertEquals(john.getOwnWishLists().get(0).getHideInfoFromOwner(), true);
    }

    @Test
    void addWish() {
        wishList.addWish("Car");
        wishList.addWish("Bicycle");
        assertEquals(wishList.getWishes().get(1).getWish(), "Bicycle");
    }

    @Test
    void removeWish() {
        wishList.addWish("Car");
        wishList.removeWish(wishList.getWishes().get(0));
        assertEquals(wishList.getWishes().toString(), "[]");
    }
}