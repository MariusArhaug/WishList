package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** WishList object to hold information about wishes. */
public class WishList {

  private final List<Wish> wishes = new ArrayList<>();
  private String name;
  private User owner;
  private boolean hideInfoFromOwner;

  public WishList() {}

  public WishList(String name) {
    this.setName(name);
  }

  public WishList(String name, User user) {
    this(name);
    this.setOwner(user);
  }

  public List<Wish> getWishes() {
    return this.wishes;
  }

  public String getName() {
    return this.name;
  }

  /**
   * Set name of wishList.
   *
   * @param name name for wishList.
   * @return WishList object
   * @throws IllegalArgumentException if name is not sufficient
   */
  public WishList setName(String name) throws IllegalArgumentException {
    if (name.length() == 0 || name.length() > 25) {
      throw new IllegalArgumentException("The name can not be empty or surpass 25 character!");
    }
    this.name = name;
    return this;
  }

  public User getOwner() {
    return this.owner;
  }

  /**
   * Set owner to a wishList.
   *
   * @param owner owner to be set
   * @return wishList
   * @throws IllegalArgumentException input is null or wishList already has owner
   */
  public WishList setOwner(User owner) throws IllegalArgumentException {
    if (this.owner != null) {
      throw new IllegalArgumentException("This wishlist already has a owner!");
    }
    this.owner = owner;
    return this;
  }

  public boolean getHideInfoFromOwner() {
    return this.hideInfoFromOwner;
  }

  /**
   * When a wishList is shared the owner can choose to hide information about who is covering each
   * wish from himself/herself
   *
   * @param hideInfoFromOwner Boolean to hide information from owner or not
   */
  void setHideInfoFromOwner(boolean hideInfoFromOwner) {
    this.hideInfoFromOwner = hideInfoFromOwner;
  }

  /**
   * Add wish to wish list.
   *
   * @param wish wish to add
   */
  public void addWish(Wish wish) {
    if (!hasWish(wish)) {
      this.wishes.add(wish);
      wish.setBelongTo(this);
    }
  }

  boolean hasWish(Wish wish) {
    return this.wishes.contains(wish);
  }

  /**
   * A wish can be removed from a wishList.
   *
   * @param wish core.Wish to remove
   */
  public void removeWish(Wish wish) {
    wishes.remove(wish);
  }

  @Override
  public String toString() {
    return "" + this.name + ", " + this.owner + ", " + this.hideInfoFromOwner + "";
  }

  /**
   * Get wish from wishList.
   *
   * @param name name of wish
   * @return wish
   */
  public Optional<Wish> getWish(String name) {
    List<Wish> wishes = this.getWishes();
    for (Wish w : wishes) {
      if (w.getName().equals(name)) {
        return Optional.of(w);
      }
    }
    return Optional.empty();
  }
}
