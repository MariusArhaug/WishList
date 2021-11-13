package wishList.json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wishList.core.User;
import wishList.core.WishList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserSerializerTest {
  private final File usersTestFile = new File(JsonHandlerTest.testFolder + "users.json");
  private User user;
  private ObjectMapper mapper;

  @BeforeEach
  void setUp() throws IOException {
    user = new User("first", "last", "user@gmail.com", "123Password!");
    mapper = new ObjectMapper();
    mapper.getFactory().createGenerator(usersTestFile, JsonEncoding.UTF8);
  }

  @AfterEach
  void tearDown() {
    user = null;
    mapper = null;
  }

  @Test
  void userSerializerTest() throws Exception {
    List<User> users = new ArrayList<>();
    users.add(user);
    mapper.writeValue(usersTestFile, users);

    User[] usersFromFile = mapper.readValue(usersTestFile, new TypeReference<User[]>() {});

    assertEquals(usersFromFile[0].getFirstName(), "first");
    assertEquals(usersFromFile[0].getLastName(), "last");
    assertEquals(usersFromFile[0].getEmail(), "user@gmail.com");
    assertEquals(usersFromFile[0].getPassword(), "123Password!");
    List<WishList> emptyOwnList = new ArrayList<>();
    List<WishList> iteratorList = new ArrayList<>();
    usersFromFile[0].iterator().forEachRemaining(iteratorList::add);
    assertEquals(iteratorList, emptyOwnList);
    List<WishList> invitedWishLists = new ArrayList<>();
    assertEquals(usersFromFile[0].getInvitedWishLists(), invitedWishLists);
    List<List<User>> groups = new ArrayList<>();
    assertEquals(usersFromFile[0].getWishListGroups(), groups);
    List<User> contacts = new ArrayList<>();
    assertEquals(usersFromFile[0].getContacts(), contacts);
  }
}