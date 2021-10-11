package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;
import core.Wish;
import core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class JsonHandlerTest {
    User user;
    WishList wishList;
    Wish wish;
    JsonHandler jsonHandler;
    Optional<User> optionalUser;
    Optional<WishList> optionalWishList;
    Optional<Wish> optionalWish;
    UserSerializerTest userSerializerTest;
    WishSerializerTest wishSerializerTest;
    WishListSerializerTest wishListSerializerTest;

    @BeforeEach
    void setUp() {
        user = new User("FistName", "LastName", "Email@gmail.com", "Password123!");
        wishList = new WishList(user, "Wedding");
        wish = new Wish("Chair");
        wish.setBelongTo(wishList);
        jsonHandler = new JsonHandler();
        optionalUser = Optional.of(user);
        optionalWishList = Optional.of(wishList);
        optionalWish = Optional.of(wish);
        userSerializerTest = new UserSerializerTest();
        wishSerializerTest = new WishSerializerTest();
        wishListSerializerTest = new WishListSerializerTest();
    }

    @AfterEach
    void tearDown() {
        user = null;
        wishList = null;
        wish = null;
        jsonHandler = null;
        optionalWish = Optional.empty();
        optionalUser = Optional.empty();
        optionalWishList = Optional.empty();
        userSerializerTest = null;
        wishSerializerTest = null;
        wishListSerializerTest = null;
    }

    @Test
    void addUserTest() throws Exception {
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getFirstName(), user.getFirstName());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getLastName(), user.getLastName());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getEmail(), user.getEmail());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getPassword(), user.getPassword());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getWishLists().get(0).getName(), wishList.getName());
        userSerializerTest.userSerializerTest();
    }
    @Test
    void addWishListTest() throws Exception {
        assertEquals(jsonHandler.addWishList("Wedding", user).getOwner(), user);
        assertEquals(jsonHandler.addWishList("Wedding", user).getWishes().get(0).getName(), wish.getName());
        assertEquals(jsonHandler.addWishList("Wedding", user).getName(), "Chair");
        assertFalse(jsonHandler.addWishList("Wedding", user).getHideInfoFromOwner());
        wishListSerializerTest.wishListSerializerTest();
    }
    @Test
    void addWishTest() throws Exception {
        assertEquals(jsonHandler.addWish("Chair", wishList).getName(), wish.getName());
        assertEquals(jsonHandler.addWish("Chair", wishList).getBelongTo(), wish.getBelongTo());
        wishSerializerTest.wishListSerializerTest();
    }
    @Test
    void loadUser() throws Exception {
        assertNull(jsonHandler.loadUser("Email2@gmail.com", "Password123!2"));
        assertEquals(jsonHandler.loadUser("Email@gmail.com", "Password123!"), optionalUser);
    }
    @Test
    void loadWishList() throws Exception {
        assertNull(jsonHandler.loadWishList(user, "Baby shower"));
        assertEquals(jsonHandler.loadWishList(user, "Wedding"), optionalWishList);
    }
    @Test
    void loadWish() throws Exception {
        assertNull(jsonHandler.loadWish(wishList, "Crib"));
        assertEquals(jsonHandler.loadWish(wishList, "Chair"), optionalWish);
    }
}