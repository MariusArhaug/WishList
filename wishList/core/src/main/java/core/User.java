package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class User {

  private final String firstName;
  private final String lastName;
  private final String email;
  private final String password;

  private final List<User> contacts = new ArrayList<>();

  private final List<List<User>> wishListGroups = new ArrayList<>();

  private final List<WishList> ownWishLists = new ArrayList<>();
  private final List<WishList> invitedWishLists = new ArrayList<>();


  public User(String firstName, String lastName, String email, String password) throws IllegalArgumentException {
    if (firstName.length() == 0 || firstName.length() > 20) {
      throw new IllegalArgumentException("A user must have a first name and it can not surpass 20 characters!");
    }
    if (lastName.length() == 0 || lastName.length() > 20) {
      throw new IllegalArgumentException("A user must have a last name and it can not surpass 20 characters!");
    }
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
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

  /**
   * Add new group of people into this user's list of previously used groups, so it can be reused
   * @param newGroup Group to add
   */
  private void addGroup(List<User> newGroup) {
    this.wishListGroups.add(newGroup);
  }
  /**
   * Choose specific users to add to a group
   * @param chosenContactsArray Contacts to add
   */
  public void makeGroup(User... chosenContactsArray) {
    List<User> chosenContactsList = new ArrayList<>();
    Collections.addAll(chosenContactsList, chosenContactsArray);
    addGroup(chosenContactsList);
  }

  /**
   * Add a wishList a user has been invited to into the list of invited wishlists
   * @param wishList Wishlist to add
   */
  private void addInvitedToWishList(WishList wishList) {
    this.invitedWishLists.add(wishList);
  }

  /**
   * Share a wishlist with a group
   * @param wishList Wishlist to share
   * @param group Group to share with
   * @param hideInfoFromOwner Boolean to hide information from owner or not
   */
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

  /**
   * Changes if the user that owns the wishlist can se hidden information or not
   * @param wishList Wishlist to edit
   * @param hideInfoFromOwner Boolean to hide information from owner or not
   */
  public void changeVisibility(WishList wishList, boolean hideInfoFromOwner) {
    if (wishList.getHideInfoFromOwner() != hideInfoFromOwner) {
      wishList.setHideInfoFromOwner(hideInfoFromOwner);
    }
  }

  /**
   * Checks to see if user's email and passwords match with inputs
   * @param email email string
   * @param password password string
   * @return boolean
   */
  public boolean checkCredentials(String email, String password) {
    return this.email.equals(email) && this.password.equals(password);
  }

  /**
   * Adds a wish to an owned wishlist
   * @param wishList Wishlist to add a wish to
   * @param wish core.Wish to add
   */
  public void addWish(WishList wishList, String wish) {
    if (this.ownWishLists.contains(wishList)) {
      wishList.addWish(wish);
    } else {
      throw new IllegalCallerException("You do not own this wishlist so you can not add this wish!");
    }
  }

  /**
   * Removes a wish from an owned wishlist
   * @param wish core.Wish to remove
   */
  public void removeWish(Wish wish) {
    if (this.ownWishLists.contains(wish.getBelongTo())) {
      wish.getBelongTo().removeWish(wish);
    } else {
      throw new IllegalCallerException("You do not own this wishlist so you can not remove this wish!");
    }
  }

  /**
   * Add a contact to a users contact list
   * @param newContact Contact to add
   */
  public void addContact(User newContact) {
    this.contacts.add(newContact);
    newContact.getContacts().add(this);
  }

  /**
   * Remove a contact from a users contact list
   * @param notContact Contact to remove
   */
  public void removeContact(User notContact) {
    this.contacts.remove(notContact);
    notContact.getContacts().remove(this);

  }

  /**
   * If the list is not your own the user can cover a wish on that list
   * @param wish core.Wish to cover
   */
  public void coverAWish(Wish wish) {
    if (this.invitedWishLists.contains(wish.getBelongTo())) {
      wish.coverAWish(this);
    } else {
      throw new IllegalCallerException("This wishlist has not been shared with you so you can not cover a wish on this list!");
    }
  }

  /**
   * A user can make a new wishList
   * @param name Name of wishlist
   * @param wishes Wishes to add
   */
  public void makeWishList(String name, Wish... wishes) {
    WishList wishList = new WishList(this, name, wishes);
    ownWishLists.add(wishList);
  }

  /**
   * A user can delete a wishList that they own
   * @param wishList Wishlist to delete
   */
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
