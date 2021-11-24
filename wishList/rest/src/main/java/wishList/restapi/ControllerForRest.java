package wishList.restapi;

import org.springframework.web.bind.annotation.*;
import wishList.core.User;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/** REST controller. * */
@RestController
@RequestMapping(ControllerForRest.wishListPath)
public class ControllerForRest {

  static final String wishListPath = "wishList";

  @GetMapping("/users/")
  @ResponseBody()
  public List<User> getUsers() throws IOException {
    return WishListService.getUsers();
  }

  @GetMapping("/user/{email}/{password}")
  @ResponseBody()
  public Optional<User> findUser(@PathVariable String email, @PathVariable String password)
      throws Exception {
    return WishListService.findUser(email, password);
  }

  @PostMapping(path = "/create/user")
  @ResponseBody()
  public User createUser(
      @RequestParam("firstName") String firstName,
      @RequestParam("lastName") String lastName,
      @RequestParam("email") String email,
      @RequestParam("password") String password)
      throws Exception {
    return WishListService.createUser(firstName, lastName, email, password);
  }
}
