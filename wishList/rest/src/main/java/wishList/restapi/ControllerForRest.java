package wishList.restapi;

import org.springframework.web.bind.annotation.*;
import wishList.core.User;

/** REST controller. * */
@RestController
@RequestMapping(ControllerForRest.wishListPath)
public class ControllerForRest {

  static final String wishListPath = "wishlist";
  private final WishListService wishListService;

  public ControllerForRest() {
    this.wishListService = new WishListService();
  }

  @GetMapping("/{email}/{password}")
  public User findUser(@PathVariable String email, @PathVariable String password) throws Exception {
    return this.wishListService.findUser(email, password);
  }

  @PostMapping(path = "/createUser")
  public User createUser(
      @RequestParam("firstName") String firstName,
      @RequestParam("lastName") String lastName,
      @RequestParam("email") String email,
      @RequestParam("password") String password)
      throws Exception {

    return this.wishListService.createUser(firstName, lastName, email, password);
  }

  @PostMapping(path = "")
  public void createWishList() {
    this.wishListService.createWishList("hallo");
  }
}
