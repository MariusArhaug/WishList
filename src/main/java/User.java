import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;

  private final List<User> contacts = new ArrayList<>();

  private final List<List<User>> wishListGroups = new ArrayList<>();

  private final List<WishList> ownWishLists = new ArrayList<>();
  private final List<WishList> invitedWishLists = new ArrayList<>();


  public User(String firstName, String lastName, String email, String password) {
    if (firstName.length() != 0 && firstName.length() < 20) {
      this.firstName = firstName;
    } else {
      throw new IllegalArgumentException("A user must have a first name and it can not surpass 20 characters!");
    }
    if (lastName.length() != 0 && lastName.length() < 20) {
      this.lastName = lastName;
    } else {
      throw new IllegalArgumentException("A user must have a last name and it can not surpass 20 characters!");
    }
    if (email.length() != 0) {
      Pattern emailPattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
      Matcher emailMatcher   = emailPattern.matcher(email);
      boolean validEmail = emailMatcher.find();
      if (validEmail) {
        this.email = email;
      } else {
        throw new IllegalArgumentException("This is not a valid email!");
      }
    } else {
      throw new IllegalArgumentException("A user must have an email!");
    }

    if (password != null && password.length() >= 8) {
      Pattern specialCharacter = Pattern.compile("[$&+,:;=?@#|'<>.^*()%!-]");
      Pattern number = Pattern.compile("[0-9]");
      Pattern upperCase = Pattern.compile("[A-Z]");
      Matcher scMatcher = specialCharacter.matcher(password);
      Matcher numberMatcher = number.matcher(password);
      Matcher upperMatcher = upperCase.matcher(password);
      boolean containsSC = scMatcher.find();
      boolean containsNumber = numberMatcher.find();
      boolean containsUpper = upperMatcher.find();
      if (!containsSC) {
        throw new IllegalArgumentException("Password must contain at least one special character!");
      } else if (!containsNumber) {
        throw new IllegalArgumentException("Password must contain at least one number!");
      } else if (!containsUpper) {
        throw new IllegalArgumentException("Password must contain at least one uppercase letter!");
      } else {
        this.password = password;
      }
    } else {
      throw new IllegalArgumentException("A user must have a password and it must be at least 8 characters long!");
    }
  }

  public String getFirstName() {
    return this.firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public String getPassword() {
    return this.password;
  }

  public List<WishList> getOwnWishLists() {
    return this.ownWishLists;
  }

  public List<WishList> getInvitedWishLists() {
    return this.invitedWishLists;
  }

  public List<List<User>> getWishListGroups() {
    return this.wishListGroups;
  }

  public List<User> getContacts() {
    return this.contacts;
  }

  /** Add new group of people into this user's list of previously used groups, so it can be reused **/
  private void addGroup(List<User> newGroup) {
    this.wishListGroups.add(newGroup);
  }
  /** Choose specific users to add to a group **/
  public void makeGroup(User... chosenContactsArray) {
    List<User> chosenContactsList = new ArrayList<>();
    Collections.addAll(chosenContactsList, chosenContactsArray);
    addGroup(chosenContactsList);
  }

  /** Add a wishList a user has been invited to into the list of invited wishlists **/
  private void addInvitedToWishList(WishList wishList) {
    this.invitedWishLists.add(wishList);
  }

  /** share a wishlist with a group **/
  public void shareWishList(WishList wishList, List<User> group, boolean hideInfoFromOwner) {
    if (this.ownWishLists.contains(wishList)) {
      wishList.setHideInfoFromOwner(hideInfoFromOwner);
      for (User u: group) {
        u.addInvitedToWishList(wishList);
      }
    } else{
      throw new IllegalCallerException("You do not own this wishlist so you can not share it!");
    }
  }

  /** Changes if the user that owns the wishlist can se hidden information or not **/
  public void changeVisibility(WishList wishList, boolean hideInfoFromOwner) {
    if (wishList.getHideInfoFromOwner() != hideInfoFromOwner) {
      wishList.setHideInfoFromOwner(hideInfoFromOwner);
    }
  }

  /** Adds a wish to an owned wishlist **/
  public void addWish(WishList wishList, String wish) {
    if (this.ownWishLists.contains(wishList)) {
      wishList.addWish(wish);
    } else {
      throw new IllegalCallerException("You do not own this wishlist so you can not add this wish!");
    }
  }

  /** Removes a wish from an owned wishlist **/
  public void removeWish(Wish wish) {
    if (this.ownWishLists.contains(wish.getBelongTo())) {
      wish.getBelongTo().removeWish(wish);
    } else {
      throw new IllegalCallerException("You do not own this wishlist so you can not remove this wish!");
    }
  }

  /** Add a contact to a users contact list **/
  public void addContact(User newContact) {
    this.contacts.add(newContact);
    newContact.getContacts().add(this);
  }

  /** Remove a contact from a users contact list **/
  public void removeContact(User notContact) {
    this.contacts.remove(notContact);
    notContact.getContacts().remove(this);

  }

  /** If the list is not your own the user can cover a wish on that list **/
  public void coverAWish(Wish wish) {
    if (this.invitedWishLists.contains(wish.getBelongTo())) {
      wish.coverAWish(this);
    } else {
      throw new IllegalCallerException("This wishlist has not been shared with you so you can not cover a wish on this list!");
    }
  }

  /** A user can make a new wishList **/
  public void makeWishList(String name, Wish... wishes) {
    WishList wishList = new WishList(this, name, wishes);
    ownWishLists.add(wishList);
  }

  /** A user can delete a wishList that they own **/
  public void removeWishList(WishList wishList) {
    if (this.ownWishLists.contains(wishList)) {
      ownWishLists.remove(wishList);
    } else {
      throw new IllegalCallerException("You do not own this wishlist so you can not delete it!");
    }
  }

  public String toString() {
    return "" + this.firstName + "," + this.lastName + "," + this.email + "," + this.password + "";
  }
}
