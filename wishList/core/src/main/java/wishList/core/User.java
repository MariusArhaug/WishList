package wishList.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * User class saves various user data such as: contacts list of {@link User} wish Lists of {@link
 * WishList}.
 */
public class User {

  private final List<WishList> wishLists = new ArrayList<>();
  private String email;
  private String password;
  private String firstName;
  private String lastName;

  /**
   * Create user with firstname, lastname, email and password.
   *
   * @param firstName firstname
   * @param lastName lastname
   * @param email email
   * @param password password
   * @throws IllegalArgumentException if first or lastname don't match with constraints
   */
  public User(String firstName, String lastName, String email, String password)
      throws IllegalArgumentException {
    this.setFirstName(firstName);
    this.setLastName(lastName);
    this.email = email;
    this.password = password;
  }

  public User() {}

  public String getFirstName() {
    return this.firstName;
  }

  /**
   * Set firstname of user.
   *
   * @param firstName name
   * @return user object
   * @throws IllegalArgumentException if name is not sufficient.
   */
  public User setFirstName(String firstName) throws IllegalArgumentException {
    if (firstName.length() == 0 || firstName.length() > 20) {
      throw new IllegalArgumentException(
          "A user must have a first name and it can not surpass 20 characters!");
    }
    this.firstName = firstName;
    return this;
  }

  public String getLastName() {
    return this.lastName;
  }

  /**
   * Set lastname of user.
   *
   * @param lastName name
   * @return User object
   */
  public User setLastName(String lastName) throws IllegalArgumentException {
    if (lastName.length() == 0 || lastName.length() > 20) {
      throw new IllegalArgumentException(
          "A user must have a last name and it can not surpass 20 characters!");
    }
    this.lastName = lastName;
    return this;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public List<WishList> getWishLists() {
    List<WishList> listToReturn = new ArrayList<>(this.wishLists);
    return listToReturn;
  }

  /**
   * Changes if the user that owns the wishlist can se hidden information or not.
   *
   * @param wishList Wishlist to edit
   * @param hideInfoFromOwner Boolean to hide information from owner or not
   */
  public void changeVisibility(WishList wishList, boolean hideInfoFromOwner) {
    if (wishList.getHideInfoFromOwner() != hideInfoFromOwner) {
      wishList.setHideInfoFromOwner(hideInfoFromOwner);
    }
  }

  /**
   * Checks to see if user's email and passwords match with inputs.
   *
   * @param email email string
   * @param password password string
   * @return boolean
   */
  public boolean checkCredentials(String email, String password) {
    return this.email.equals(email) && this.password.equals(password);
  }

  /**
   * Make a new wish list.
   *
   * @param name name string
   */
  public void makeWishList(String name) {
    if (!wishListsExist(name)) {
      this.wishLists.add(new WishList(name, this));
    }
  }

  /**
   * Removes existing wish list.
   *
   * @param name name string
   */
  public void removeWishList(String name) {
    if (wishListsExist(name)) {
      this.wishLists.remove(
          this.wishLists.stream()
              .filter(e -> e.getName().equals(name))
              .collect(Collectors.toList())
              .get(0));
    }
  }

  /**
   * Owner of wish list can remove it.

   * @param list Wish list to remove
   * @throws IllegalCallerException
   */
  public void removeWishList(WishList list) throws IllegalCallerException {
    if (list.getOwner() != this) {
      throw new IllegalCallerException("You can only remove your own list!");
    }

    if (wishListsExist(list.getName())) {
      this.wishLists.remove(list);
    }
  }

  /**
   * Remove wish from wish list.
   *
   * @param wishListName wish list to remove wish from
   * @param wishName wish to remove
   */
  public void removeWish(String wishListName, String wishName) {
    if (wishListsExist(wishListName)) {
      this.wishLists.stream()
          .filter(w -> w.getName().equals(wishListName))
          .forEach(e -> e.removeWish(new Wish(wishName)));
    }
  }

  /**
   * Checks if wish list exists.
   *
   * @param name wish list to check for
   * @return boolean
   */
  private boolean wishListsExist(String name) {
    return this.wishLists.stream().anyMatch(e -> e.getName().equals(name));
  }

  /**
   * Add new wish list.
   *
   * @param list wish list to add
   */
  public void addWishList(WishList list) {
    wishLists.add(list);
    list.setOwner(this);
  }

  /**
   * Add wish to wish list.
   *
   * @param wishList wish list to add wish to
   * @param wish wish to add
   */
  public void addWish(WishList wishList, Wish wish) {
    wishLists.stream().filter(wl -> wl.equals(wishList)).forEach(wl -> wl.addWish(wish));
  }

  @Override
  public String toString() {
    return "" + this.firstName + "," + this.lastName + "," + this.email + "," + this.password + "";
  }

  /**
   * Get wishList matching the name.
   *
   * @param name name of wishList
   * @return wishList
   */
  public Optional<WishList> getWishList(String name) {
    return this.getWishLists().stream()
        .filter(wishList -> name.equals(wishList.getName()))
        .findAny();
  }
}
