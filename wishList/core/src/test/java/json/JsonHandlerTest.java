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


    }

    @AfterEach
    void tearDown() {
        user = null;
        wishList = null;
        wish = null;
    }

    @Test
    void addUserTest() throws Exception {
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getFirstName(), user.getFirstName());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getLastName(), user.getLastName());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getEmail(), user.getEmail());
        assertEquals(jsonHandler.addUser("FirsName", "LastName", "Email@gmail.com", "Password123!").getPassword(), user.getPassword());




    }
    @Test
    void addWishListTest() throws Exception {
        assertEquals(jsonHandler.addWishList("Wedding", user).getOwner(), user);
        List<Wish> wishes = new ArrayList<>();
        wishes.add(wish);
        assertEquals(jsonHandler.addWishList("Wedding", user).getWishes(), wishes);
        assertEquals(jsonHandler.addWishList("Wedding", user).getName(), "Chair");
        assertFalse(jsonHandler.addWishList("Wedding", user).getHideInfoFromOwner());


    }
    @Test
    void addWishTest() throws Exception {
        assertEquals(jsonHandler.addWish("Chair", wishList).getName(), wish.getName());
        assertEquals(jsonHandler.addWish("Chair", wishList).getBelongTo(), wish.getBelongTo());

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