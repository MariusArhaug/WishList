package wishList.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User class saves various user data such as: contacts list of {@link User} wish Lists of {@link
 * WishList}.
 */
// @Entity
public class User {
  private final List<WishList> ownedWishLists = new ArrayList<>();
  private final List<WishList> invitedWishLists = new ArrayList<>();
  private final List<String> contacts = new ArrayList<>();
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
    this.setFirstName(firstName).setLastName(lastName).setEmail(email).setPassword(password);
  }

  /** Empty constructor for json test purposes. */
  public User() {}

  /**
   * Get first name of user.
   *
   * @return User's first name
   */
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
    if (firstName.length() == 0) {
      throw new IllegalArgumentException("A user must have a first name!");
    }
    this.firstName = firstName;
    return this;
  }

  /**
   * Get last name of user.
   *
   * @return User's last name
   */
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
    if (lastName.length() == 0) {
      throw new IllegalArgumentException("A user must have a last name!");
    }
    this.lastName = lastName;
    return this;
  }

  /**
   * Get email of user.
   *
   * @return User's email
   */
  public String getEmail() {
    return this.email;
  }

  /**
   * Set email of user.
   *
   * @param email name
   */
  public User setEmail(String email) {
    if (email.length() == 0) {
      throw new IllegalArgumentException("A user must have a email address!");
    }
    Pattern emailPattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    Matcher emailMatcher = emailPattern.matcher(email);
    boolean validEmail = emailMatcher.find();
    if (!validEmail) {
      throw new IllegalArgumentException("This is not a valid email address!");
    }
    this.email = email;
    return this;
  }

  /**
   * Get password of user.
   *
   * @return User's password
   */
  public String getPassword() {
    return this.password;
  }

  /**
   * Set password of user.
   *
   * @param password name
   */
  public User setPassword(String password) throws IllegalArgumentException {
    if (password.length() == 0) {
      throw new IllegalArgumentException("A user must have a password!");
    }
    if (password.length() < 8) {
      throw new IllegalArgumentException("The password must be at least eight characters!");
    }
    this.password = password;
    return this;
  }

  public List<WishList> getInvitedWishLists() {
    return new ArrayList<>(this.invitedWishLists);
  }

  public List<WishList> getOwnedWishLists() {
    return new ArrayList<>(this.ownedWishLists);
  }

  /**
   * Get contacts.
   *
   * @return contacts
   */
  public List<String> getContacts() {
    return new ArrayList<>(contacts);
  }

  /**
   * Get nth wishList from ownedWishList.
   *
   * @param n number
   * @return wishlist or null
   */
  public WishList getNthOwnedWishList(int n) {
    try {
      return this.getOwnedWishLists().get(n);
    } catch (NullPointerException e) {
      return null;
    }
  }

  /**
   * Get nth wishlist from invited wishList.
   *
   * @param n number
   * @return wishList or null.
   */
  public WishList getNthInvitedWishList(int n) {
    try {
      return this.getInvitedWishLists().get(n);
    } catch (NullPointerException e) {
      return null;
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
  public String makeWishList(String name) {
    if (!wishListsExist(name)) {
      this.ownedWishLists.add(new WishList(name, this));
      return "New wish list added!";
    }
    return "You already have a wishlist with this name!";
  }

  /**
   * Removes existing wish list.
   *
   * @param name name string
   */
  public String removeWishList(String name) throws IllegalArgumentException {
    if (!wishListsExist(name)) {
      throw new IllegalArgumentException("You do not own a wishList with this name!");
    }
    WishList wishList = this.findWishList(name);
    this.ownedWishLists.remove(wishList);
    return "Wish list has been removed!";
  }

  /**
   * Remove wish from wish list.
   *
   * @param wishListName wish list to remove wish from
   * @param wishName wish to remove
   */
  public void removeWish(String wishListName, String wishName) {
    if (wishListsExist(wishListName)) {
      this.findWishList(wishListName).removeWish(wishName);
    }
  }

  /**
   * Find wish list from name.
   *
   * @param name name of wish list
   * @return wish list with this name
   */
  private WishList findWishList(String name) {
    return this.ownedWishLists.stream()
        .filter(e -> e.getName().equals(name))
        .findFirst()
        .orElse(null);
  }

  /**
   * Checks if wish list exists.
   *
   * @param name wish list to check for
   * @return boolean
   */
  private boolean wishListsExist(String name) {
    return this.findWishList(name) != null;
  }

  /**
   * Add new wish list.
   *
   * @param list wish list to add
   */
  public void addWishList(WishList list) {
    ownedWishLists.add(list);
    list.setOwner(this);
  }

  /**
   * Add new invitied wishList.
   *
   * @param list invited wishList.
   */
  public void addInvitedWishList(WishList list) {
    this.invitedWishLists.add(list);
  }

  /**
   * Add wish to wish list.
   *
   * @param wishList wish list to add wish to
   * @param wish wish to add
   */
  public void addWish(WishList wishList, Wish wish) {
    for (WishList wl : this.ownedWishLists) {
      if (wl.getName().equals(wishList.getName())) {
        wl.addWish(wish);
      }
    }
  }

  /**
   * Remove contact.
   *
   * @param user contact to remove
   */
  public void removeContact(User user) {
    this.removeContact(user.getEmail());
  }

  /**
   * Remove contact.
   *
   * @param email email of contact
   */
  public void removeContact(String email) {
    this.contacts.remove(email);
  }

  /**
   * Add contact.
   *
   * @param newContact contact to add
   */
  public void addContact(User newContact) {
    this.contacts.add(newContact.getEmail());
  }

  public void addContact(String email) {
    this.contacts.add(email);
  }

  /**
   * Share wishlist with group.
   *
   * @param wishList wish list to share
   * @param group group to share with
   */
  public void shareWishList(WishList wishList, List<User> group) {
    if (wishListsExist(wishList.getName())) {
      for (User u : group) {
        u.addInvitedToWishList(wishList);
      }
    } else {
      throw new IllegalCallerException("You do not own this wishlist so you can not share it!");
    }
  }

  /**
   * Add wishList to users invited wishlists.
   *
   * @param wishList wish list to add
   */
  private void addInvitedToWishList(WishList wishList) {
    this.invitedWishLists.add(wishList);
  }

  /**
   * Get wishList matching the name.
   *
   * @param name name of wishList
   * @return wishList
   */
  public Optional<WishList> getWishList(String name) {
    return this.ownedWishLists.stream()
        .filter(wishList -> name.equals(wishList.getName()))
        .findAny();
  }

  @Override
  public String toString() {
    return "" + this.firstName + "," + this.lastName + "," + this.email + "," + this.password + "";
  }
}
