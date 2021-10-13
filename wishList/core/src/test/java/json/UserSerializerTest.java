package json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.User;
import core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;

class UserSerializerTest {
    private User user;
    private ObjectMapper mapper;
    private final File usersTestFile = new File(JsonHandlerTest.testFolder + "users.json");


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

        User[] usersFromFile = mapper.readValue(usersTestFile, new TypeReference<User[]>(){});

        assertEquals(usersFromFile[0].getFirstName(), "first");
        assertEquals(usersFromFile[0].getLastName(), "last");
        assertEquals(usersFromFile[0].getEmail(), "user@gmail.com");
        assertEquals(usersFromFile[0].getPassword(), "123Password!");
        List<WishList> wishLists = new ArrayList<>();
        assertEquals(usersFromFile[0].getWishLists(), wishLists);
    }
}