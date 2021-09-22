package core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WishList {

  private final String name;
  private List<Wish> wishes = new ArrayList<>();
  private final User owner;
  private boolean hideInfoFromOwner;

  public WishList(User owner, String name, Wish... wishes) {
    if (name.length() != 0 && name.length()< 25) {
      this.name = name;
    } else {
      throw new IllegalArgumentException("The name can not be empty or surpass 25 character!");
    }
    if (owner != null) {
      this.owner = owner;
    } else {
      throw new IllegalArgumentException("A wish list must be owned by an existing user!");
    }
    Collections.addAll(this.wishes, wishes);
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
   * A new wish can be added to a wishList
   * @param wishContent core.Wish content to add
   */
  public void addWish(String wishContent) {
    Wish wish = new Wish(wishContent, this);
    wishes.add(wish);
  }

  /**
   * A wish can be removed from a wishList
   * @param wish core.Wish to remove
   */
  public void removeWish(Wish wish) {
    wishes.remove(wish);
    wish = null;
  }

  public String toString() {
    return "" + this.name + "," + this.owner + "," + this.hideInfoFromOwner + "";
  }
}
