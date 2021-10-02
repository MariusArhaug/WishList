package json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.WishList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WishListDeserializerTest {
    WishListDeserializer wishListDeserializer;
    WishList wishList;
    DeserializationContext deserializationContext;
    JsonParser parser;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;

    @BeforeEach
    void setUp() throws IOException {
        wishList = new WishList("name");
        json = "{\"name\":\"name\"}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        parser = factory.createParser(json);
        wishListDeserializer = new WishListDeserializer();
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        json = null;
        mapper = null;
        factory = null;
        parser = null;
        wishListDeserializer = null;
    }

    @Test
    void wishListDeserializerTest() throws IOException {
        assertEquals((wishListDeserializer.deserialize(parser, deserializationContext)).toString(), wishList.toString());
    }
}