import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishTest {
    User john;
    User jane;
    WishList wishList;
    Wish wish;
    Wish wish2;

    @BeforeEach
    void setUp() {
        john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
        jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
        wishList = new WishList(john, "Birthday");
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        jane = null;
        john = null;
    }

    @Test
    void Wish() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Wish("", wishList);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Wish("MoreThanTwenthyFiveCharacters", wishList);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Wish("Wedding", null);
        });
    }

    @Test
    void getWish() {
        jane.makeWishList("Moving party", new Wish[] {});
        jane.getOwnWishLists().get(0).addWish("Surf board");
        assertEquals(jane.getOwnWishLists().get(0).getWishes().get(0).getWish(), "Surf board");
    }

    @Test
    void getCovered() {
        jane.makeWishList("Moving party", new Wish[] {});
        jane.getOwnWishLists().get(0).addWish("Surf board");
        assertEquals(jane.getOwnWishLists().get(0).getWishes().get(0).getCovered(), false);
        jane.addContact(john);
        User[] group = new User[]{john};
        jane.makeGroup(group);
        jane.shareWishList(jane.getOwnWishLists().get(0), jane.getWishListGroups().get(0), false);
        john.coverAWish(john.getInvitedWishLists().get(0).getWishes().get(0));
        assertEquals(john.getInvitedWishLists().get(0).getWishes().get(0).getCovered(), true);
    }

    @Test
    void getCoveredBy() {
        jane.makeWishList("Moving party", new Wish[] {});
        jane.getOwnWishLists().get(0).addWish("Surf board");
        jane.addContact(john);
        User[] group = new User[]{john};
        jane.makeGroup(group);
        jane.shareWishList(jane.getOwnWishLists().get(0), jane.getWishListGroups().get(0), false);
        john.coverAWish(john.getInvitedWishLists().get(0).getWishes().get(0));
        assertEquals(jane.getOwnWishLists().get(0).getWishes().get(0).getCoveredBy(), john);
    }

    @Test
    void getBelongTo() {
        wishList.addWish("Surf board");
        assertEquals(wishList.getWishes().get(0).getBelongTo(), wishList);
    }

    @Test
    void coverAWish() {
        jane.makeWishList("Moving party", new Wish[] {});
        jane.getOwnWishLists().get(0).addWish("Surf board");
        jane.addContact(john);
        User[] group = new User[]{john};
        jane.makeGroup(group);
        jane.shareWishList(jane.getOwnWishLists().get(0), jane.getWishListGroups().get(0), false);
        john.coverAWish(john.getInvitedWishLists().get(0).getWishes().get(0));
        assertEquals(jane.getOwnWishLists().get(0).getWishes().get(0).getCovered(), true);
        assertEquals(jane.getOwnWishLists().get(0).getWishes().get(0).getCoveredBy(), john);
    }
}