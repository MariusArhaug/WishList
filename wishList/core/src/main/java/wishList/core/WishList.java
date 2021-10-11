package wishList.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishList {

  private final String name;
  private final List<Wish> wishes = new ArrayList<>();
  private User owner;
  private boolean hideInfoFromOwner;

  public WishList(String name) {
    this.name = name;
  }

  public WishList(User owner, String name) {
    if (name.length() == 0 || name.length() > 25) {
      throw new IllegalArgumentException("The name can not be empty or surpass 25 character!");
    }
    if (owner == null) {
      throw new IllegalArgumentException("A wish list must be owned by an existing user!");
    }
    this.owner = owner;
    this.name = name;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public List<Wish> getWishes() {
    return this.wishes;
  }

  public String getName() {
    return this.name;
  }

  public User getOwner() {
    return this.owner;
  }

  public boolean getHideInfoFromOwner() {
    return this.hideInfoFromOwner;
  }

  /**
   * When a wishList is shared the owner can choose to hide information about who is covering each wish from himself/herself
   * @param hideInfoFromOwner Boolean to hide information from owner or not
   */
  public void setHideInfoFromOwner(boolean hideInfoFromOwner) {
    this.hideInfoFromOwner = hideInfoFromOwner;
  }

  /**
   * Add wish to wish list
   * @param wish wish to add
   */
  public void addWish(Wish wish) {
    this.wishes.add(wish);
    wish.setBelongTo(this);
  }

  /**
   * A wish can be removed from a wishList
   * @param wish core.Wish to remove
   */
  public void removeWish(Wish wish) {
    wishes.remove(wish);
  }

  public String toString() {
    return "" + this.name + "," + this.owner + "," + this.hideInfoFromOwner + "";
  }
}
