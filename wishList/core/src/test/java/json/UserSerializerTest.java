package json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.core.JsonParser;
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
    UserSerializer userSerializer;
    User user;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;
    JsonGenerator jsonGenerator;
    SerializerProvider serializerProvider;
    String path;

    @BeforeEach
    void setUp() throws IOException {
        user = new User("first", "last", "user@gmail.com", "123Password!");
        json = "{\"firstName\":\"first\",\"lastName\":\"last\",\"email\":\"user@gmail.com\",\"password\":\"123Password!\",\"wishLists\":[]}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        path = Paths.get("").toAbsolutePath().getParent() + "/fxui/src/main/resources/usersTest.json";
        jsonGenerator = factory.createGenerator(new File(path), JsonEncoding.UTF8);
        userSerializer = new UserSerializer();
        serializerProvider = mapper.getSerializerProvider();
    }

    @AfterEach
    void tearDown() {
        user = null;
        json = null;
        mapper = null;
        factory = null;
        path = null;
        jsonGenerator = null;
        userSerializer = null;
        serializerProvider = null;
    }

    @Test
    void userSerializerTest() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(user);
        mapper.writeValue(new File(path), users);
        //mapper.writeValue(new File(path), users);

        //userSerializer.serialize(user, jsonGenerator, serializerProvider);

        List<User> usersFromFile = mapper.readValue(new File(path), new TypeReference<List<User>>(){});
        System.out.println(usersFromFile.get(0).getFirstName());
        assertEquals(usersFromFile.get(0).getFirstName(), "first");
        assertEquals(usersFromFile.get(0).getLastName(), "last");
        assertEquals(usersFromFile.get(0).getEmail(), "user@gmail.com");
        assertEquals(usersFromFile.get(0).getPassword(), "123Password!");
        List<WishList> wishLists = new ArrayList<>();
        assertEquals(usersFromFile.get(0).getWishLists(), wishLists);
    }
}