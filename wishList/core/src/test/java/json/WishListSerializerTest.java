package json;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import core.User;
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

import static org.junit.jupiter.api.Assertions.*;

class WishListSerializerTest {
    WishListSerializer wishListSerializer;
    WishList wishList;
    String json;
    ObjectMapper mapper;
    JsonFactory factory;
    JsonGenerator jsonGenerator;
    SerializerProvider serializerProvider;
    File wishListsFile = new File(JsonHandlerTest.testFolder + "wishLists.json");

    @BeforeEach
    void setUp() throws IOException {
        wishList = new WishList("Birthday");
        json = "{\"name\":\"Birthday\"}";
        mapper = new ObjectMapper();
        factory = mapper.getFactory();
        jsonGenerator = factory.createGenerator(wishListsFile, JsonEncoding.UTF8);
        wishListSerializer = new WishListSerializer();
        serializerProvider = mapper.getSerializerProvider();
    }

    @AfterEach
    void tearDown() {
        wishList = null;
        json = null;
        mapper = null;
        factory = null;
        jsonGenerator = null;
        wishListSerializer = null;
        serializerProvider = null;
    }

    @Test
    void wishListSerializerTest() throws Exception {

        List<WishList> wishLists = new ArrayList<>();
        wishLists.add(wishList);
        mapper.writeValue(wishListsFile, wishLists);

        WishList[] wishListsFromFile = mapper.readValue(wishListsFile, new TypeReference<WishList[]>(){});

        assertEquals(wishListsFromFile[0].getName(), "Birthday");
        List<Wish> wishes = new ArrayList<>();
        assertEquals(wishListsFromFile[0].getWishes(), wishes);
        assertNull(wishListsFromFile[0].getOwner());
        assertFalse(wishListsFromFile[0].getHideInfoFromOwner());
    }
}