package json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.Wish;
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

class WishSerializerTest {
    WishSerializer wishSerializer;
    Wish wish;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;
    JsonGenerator jsonGenerator;
    SerializerProvider serializerProvider;
    String path;
    File file;

    @BeforeEach
    void setUp() throws IOException {
        wish = new Wish("Toy");
        json = "{\"name\":\"Toy\"}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        path = Paths.get("").toAbsolutePath().getParent() + "\\fxui\\src\\main\\resources\\wishTest.json";
        file = new File(path);
        jsonGenerator = factory.createGenerator(file, JsonEncoding.UTF8);
        wishSerializer = new WishSerializer();
        serializerProvider = mapper.getSerializerProvider();
    }

    @AfterEach
    void tearDown() {
        wish = null;
        json = null;
        mapper = null;
        factory = null;
        path = null;
        jsonGenerator = null;
        wishSerializer = null;
        serializerProvider = null;
    }

    @Test
    void wishListSerializerTest() throws Exception {

        List<Wish> wishes = new ArrayList<>();
        wishes.add(wish);
        mapper.writeValue(file, wishes);

        Wish[] wishesFromFile = mapper.readValue(file, new TypeReference<Wish[]>(){});

        assertEquals(wishesFromFile[0].getName(), "Toy");
    }
}