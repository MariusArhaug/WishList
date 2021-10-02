package json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserDeserializerTest {
    UserDeserializer userDeserializer;
    User user;
    DeserializationContext deserializationContext;
    JsonParser parser;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;

    @BeforeEach
    void setUp() throws IOException {
        user = new User("first", "last", "user@gmail.com", "123Password!");
        json = "{\"firstName\":\"first\",\"lastName\":\"last\",\"email\":\"user@gmail.com\",\"password\":\"123Password!\",\"wishLists\":[]}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        parser = factory.createParser(json);
        userDeserializer = new UserDeserializer();
    }

    @AfterEach
    void tearDown() {
        user = null;
        json = null;
        mapper = null;
        factory = null;
        parser = null;
        userDeserializer = null;
    }

    @Test
    void userDeserializerTest() throws IOException {
        assertEquals((userDeserializer.deserialize(parser, deserializationContext)).toString(), user.toString());
    }
}