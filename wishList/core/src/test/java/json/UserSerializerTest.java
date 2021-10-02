package json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserSerializerTest {
    UserSerializer userSerializer;
    User user;
    DeserializationContext deserializationContext;
    JsonParser parser;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;
    JsonGenerator jsonGenerator;

    @BeforeEach
    void setUp() throws IOException {
        user = new User("first", "last", "user@gmail.com", "123Password!");
        json = "{\"firstName\":\"first\",\"lastName\":\"last\",\"email\":\"user@gmail.com\",\"password\":\"123Password!\",\"wishLists\":[]}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        parser = factory.createParser(json);
        jsonGenerator = new JsonGenerator();
        userSerializer = new UserSerializer();
    }

    @AfterEach
    void tearDown() {
        user = null;
        json = null;
    }

    @Test
    void userDeserializerTest() throws IOException {
        assertEquals((userSerializer.deserialize(user, deserializationContext)).toString(), user.toString());
    }
}