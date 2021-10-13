import core.User;
import core.Wish;
import core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WishTest {
    User john;
    User jane;
    WishList wishList;

    @BeforeEach
    void setUp() {
        john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
        jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
        wishList = new WishList("Birthday", john);
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        jane = null;
        john = null;
    }

    @Test
    void Wish() {
        assertThrows(IllegalArgumentException.class, () ->
            new Wish("")
        );
        assertThrows(IllegalArgumentException.class, () ->
            new Wish("MoreThanTwentyFiveCharacters!")
        );
    }

    @Test
    void getWish() {
        jane.makeWishList("Moving party");
        jane.getWishLists().get(0).addWish(new Wish("Surf board"));
        assertEquals(jane.getWishLists().get(0).getWishes().get(0).getName(), "Surf board");
    }

    @Test
    void getBelongTo() {
        wishList.addWish(new Wish("Surf board"));
        assertEquals(wishList.getWishes().get(0).getBelongTo(), wishList);
    }


}