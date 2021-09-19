import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User john;
    User jane;

    @BeforeEach
    void setUp() {
        john = new User("John", "Smith", "John.Smith@gmail.com", "!Password123");
        jane = new User("Jane", "Doe", "Jane.Doe@gmail.com", "123Password!");
    }

    @AfterEach
    void tearDown() {
        jane = null;
        john = null;
    }

    @Test
    void User() {
        String[] testFirstNames = {"", "MoreThanTwentyLetters", "Tom", "Tom", "Tom", "Tom", "Tom", "Tom", "Tom", "Tom", "Tom"};
        String[] testLastNames = {"Parker", "Parker", "", "MoreThanTwentyLetters", "Parker", "Parker", "Parker", "Parker", "Parker", "Parker", "Parker"};
        String[] testEmails = {"Tom.Parker@gmail.com", "Tom.Parker@gmail.com", "Tom.Parker@gmail.com", "Tom.Parker@gmail.com", "", "123456789", "Tom.Parker@gmail.com", "Tom.Parker@gmail.com", "Tom.Parker@gmail.com", "Tom.Parker@gmail.com", "Tom.Parker@gmail.com"};
        String[] testPasswords = {"123!Password", "123!Password", "123!Password", "123!Password", "123!Password", "123!Password", "passwor", "null", "!Password", "123Password", "123!password"};

        for (int i = 0; i<testFirstNames.length; i++) {
            int finalI = i;
            assertThrows(IllegalArgumentException.class, () -> {
                new User(testFirstNames[finalI], testLastNames[finalI], testEmails[finalI], testPasswords[finalI]);
            });
        }
    }

    @Test
    void getFirstName() {
        assertEquals(john.getFirstName(), "John");
    }

    @Test
    void getLastName() {
        assertEquals(john.getLastName(), "Smith");
    }

    @Test
    void getEmail() {
        assertEquals(john.getEmail(), "John.Smith@gmail.com");
    }

    @Test
    void getPassword() {
        assertEquals(john.getPassword(), "!Password123");
    }

    @Test
    void getContacts() {
        List<User> emptyContactList = new ArrayList<>();
        assertEquals(john.getContacts(), emptyContactList);
        john.addContact(jane);
        emptyContactList.add(jane);
        assertEquals(john.getContacts(), emptyContactList);
    }

    @Test
    void getOwnWishLists() {
        List<WishList> emptyOwnList = new ArrayList<>();
        assertEquals(john.getOwnWishLists(), emptyOwnList);
        john.makeWishList("Baby shower", new Wish[] {});
        assertEquals(john.getOwnWishLists().toString(), "[Baby shower,John,Smith,John.Smith@gmail.com,!Password123,false]");
    }

    @Test
    void getInvitedWishLists() {
        List<WishList> emptyInvitedList = new ArrayList<>();
        assertEquals(john.getInvitedWishLists(), emptyInvitedList);
        jane.makeWishList("Baby shower", new Wish[] {});
        jane.addContact(john);
        User[] group = new User[]{john};
        jane.makeGroup(group);
        jane.shareWishList(jane.getOwnWishLists().get(0), jane.getWishListGroups().get(0), false);
        assertEquals(john.getInvitedWishLists().toString(), "[Baby shower,Jane,Doe,Jane.Doe@gmail.com,123Password!,false]");
    }

    @Test
    void getWishListGroups() {
        List<List<User>> emptyGroupList = new ArrayList<>();
        assertEquals(john.getInvitedWishLists(), emptyGroupList);
        jane.addContact(john);
        User[] group = new User[]{john};
        jane.makeGroup(group);
        assertEquals(jane.getWishListGroups().toString(), "[[John,Smith,John.Smith@gmail.com,!Password123]]");
    }

    @Test
    void makeGroup() {
        jane.addContact(john);
        User[] group = new User[]{john};
        jane.makeGroup(group);
        assertEquals(jane.getWishListGroups().toString(), "[[John,Smith,John.Smith@gmail.com,!Password123]]");
    }

    @Test
    void shareWishList() {
        john.makeWishList("Christmas", new Wish[] {});
        jane.addContact(john);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        assertThrows(IllegalCallerException.class, () -> {
            jane.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        });
        assertEquals(jane.getInvitedWishLists().get(0), john.getOwnWishLists().get(0));
    }

    @Test
    void changeVisibility() {
        john.makeWishList("Christmas", new Wish[] {});
        jane.addContact(john);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        assertEquals(john.getOwnWishLists().get(0).getHideInfoFromOwner(), false);
        john.changeVisibility(john.getOwnWishLists().get(0), true);
        assertEquals(john.getOwnWishLists().get(0).getHideInfoFromOwner(), true);
    }

    @Test
    void addWish() {
        john.makeWishList("Christmas", new Wish[] {});
        assertEquals(john.getOwnWishLists().get(0).getWishes().toString(), "[]");
        john.addWish(john.getOwnWishLists().get(0), "Book");
        jane.addContact(john);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        assertThrows(IllegalCallerException.class, () -> {
            jane.addWish(jane.getInvitedWishLists().get(0), "Sunglasses");
        });
        assertEquals(john.getOwnWishLists().get(0).getWishes().toString(), "[Book,Christmas,John,Smith,John.Smith@gmail.com,!Password123,false,false,null]");
    }

    @Test
    void removeWish() {
        john.makeWishList("Christmas", new Wish[] {});
        john.addWish(john.getOwnWishLists().get(0), "Book");
        jane.addContact(john);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        assertThrows(IllegalCallerException.class, () -> {
            jane.removeWish(jane.getInvitedWishLists().get(0).getWishes().get(0));
        });
        john.removeWish(john.getOwnWishLists().get(0).getWishes().get(0));
        assertEquals(john.getOwnWishLists().get(0).getWishes().toString(), "[]");
    }

    @Test
    void coverAWish() {
        john.makeWishList("Christmas", new Wish[] {});
        john.getOwnWishLists().get(0).addWish("Book");
        jane.addContact(john);
        User[] group = new User[]{jane};
        john.makeGroup(group);
        john.shareWishList(john.getOwnWishLists().get(0), john.getWishListGroups().get(0), false);
        jane.coverAWish(jane.getInvitedWishLists().get(0).getWishes().get(0));
        assertThrows(IllegalCallerException.class, () -> {
            john.coverAWish(jane.getInvitedWishLists().get(0).getWishes().get(0));
        });
        assertEquals(jane.getInvitedWishLists().get(0).getWishes().get(0).getCovered(), true);
        assertEquals(jane.getInvitedWishLists().get(0).getWishes().get(0).getCoveredBy(), jane);
    }

    @Test
    void addContact() {
        jane.addContact(john);
        assertEquals(jane.getContacts().toString(), "[John,Smith,John.Smith@gmail.com,!Password123]");
        assertEquals(john.getContacts().toString(), "[Jane,Doe,Jane.Doe@gmail.com,123Password!]");
    }

    @Test
    void removeContact() {
        jane.addContact(john);
        jane.removeContact(john);
        assertEquals(jane.getContacts().toString(), "[]");
        assertEquals(john.getContacts().toString(), "[]");
    }

    @Test
    void makeWishList() {
        john.makeWishList("Christmas", new Wish[] {});
        assertEquals(john.getOwnWishLists().toString(), "[Christmas,John,Smith,John.Smith@gmail.com,!Password123,false]");
    }

    @Test
    void removeWishList() {
        john.makeWishList("Christmas", new Wish[] {});
        assertThrows(IllegalCallerException.class, () -> {
            jane.removeWishList(john.getOwnWishLists().get(0));
        });
        john.removeWishList(john.getOwnWishLists().get(0));
        assertEquals(john.getOwnWishLists().toString(), "[]");
    }
}