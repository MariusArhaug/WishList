package json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.Wish;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WishDeserializerTest {
    WishDeserializer wishDeserializer;
    Wish wish;
    DeserializationContext deserializationContext;
    JsonParser parser;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;

    @BeforeEach
    void setUp() throws IOException {
        wish = new Wish("name");
        json = "{\"name\":\"name\"}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        parser = factory.createParser(json);
        wishDeserializer = new WishDeserializer();
    }

    @AfterEach
    void tearDown() {
        wish = null;
        json = null;
        mapper = null;
        factory = null;
        parser = null;
        wishDeserializer = null;
    }

    @Test
    void wishDeserializerTest() throws IOException {
        assertEquals((wishDeserializer.deserialize(parser, deserializationContext)).toString(), wish.toString());
    }
}