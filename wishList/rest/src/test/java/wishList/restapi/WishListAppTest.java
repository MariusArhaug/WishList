package wishList.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import wishList.core.User;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { RESTApplication.class, RESTController.class, WishListService.class })
@WebMvcTest
public class WishListAppTest {

    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Test that GET method for getiing user from file is working.
     *
     * @throws Exception if URI not found
     */
    @Test
    public void testGetUser() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/wishList/api/v1" + "/user/{email}/", "email")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test that GET method for all wishlists is working.
     *
     * @throws Exception if URI not found
     */
    @Test
    public void testGetAllWishLists() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/wishList/api/v1/wishLists/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test that GET method for getting all users is working.
     *
     * @throws Exception if URI not found
     */
    @Test
    public void testGetAllUsers() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/wishList/api/v1/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test that GET method for getting user with email and password as parameters is working.
     *
     * @throws Exception if URI not found
     */
    @Test
    public void testGetUserWithPassword() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders
                .get("/wishList/api/v1/user/{email}/{password}/", "email", "password")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

    /**
     * Test that POST method for new user is working.
     *
     * @throws Exception if URI not found
     */
    @Test
    public void testPostUser() throws Exception {
        User user = new User("John", "Smith", "john@smith.no", "12345678");
        String json = objectMapper.writeValueAsString(user);
        MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
                .post("/wishList/api/v1/user/add/")
                .content(json)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<Character> first = new ArrayList<>();
        for(char c : json.toCharArray()){
            first.add(c);
        }

        List<Character> second = new ArrayList<>();
        for(char c : result.getResponse().getContentAsString().toCharArray()){
            second.add(c);
        }

        Collections.sort(first);
        Collections.sort(second);

        Assertions.assertEquals(first, second);

        try{
            Path path = FileSystems.getDefault().getPath("./src/main/resources/wishList/users/john@smithno.json");
            Files.delete(path);
        } catch (NoSuchFileException ignored){

        }
    }
}
