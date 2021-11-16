package wishList.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/** WishList object to hold information about wishes. */
public class WishList implements Iterable<Wish> {

  private final List<Wish> wishes = new ArrayList<>();
  private String name;
  private User owner;

  /** Empty constructor for json test purposes. */
  public WishList() {}

  public WishList(String name) {
    this.setName(name);
  }

  public WishList(String name, User owner) {
    this.setName(name).setOwner(owner);
  }

  public List<Wish> getWishes() {
    return new ArrayList<>(this.wishes);
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
    if (name.length() == 0) {
      throw new IllegalArgumentException("The name can not be empty!");
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

  private boolean hasWish(Wish wish) {
    return this.wishes.contains(wish);
  }

  /**
   * A wish can be removed from a wishList.
   *
   * @param wish core.Wish to remove
   */
  void removeWish(Wish wish) {
    wishes.remove(wish);
  }

  /**
   * A wish can be removed from a wishList.
   *
   * @param name remove wish with this name
   */
  void removeWish(String name) {
    if (wishExist(name)) {
      this.wishes.remove(findWish(name));
    }
  }

  /**
   * Find wish from name.
   *
   * @param name name of wish
   * @return first wish with this name
   */
  private Wish findWish(String name) {
    return this.wishes.stream().filter(e -> e.getName().equals(name)).findFirst().orElse(null);
  }

  /**
   * Checks if wish exists.
   *
   * @param name wish to check for
   * @return boolean
   */
  private boolean wishExist(String name) {
    return this.findWish(name) != null;
  }

  @Override
  public String toString() {
    return "" + this.name + ", " + this.owner + "";
  }

  /**
   * Get wish from wishList.
   *
   * @param name name of wish
   * @return wish
   */
  public Optional<Wish> getWish(String name) {
    for (Wish w : this.wishes) {
      if (w.getName().equals(name)) {
        return Optional.of(w);
      }
    }
    return Optional.empty();
  }

  @Override
  public Iterator<Wish> iterator() {
    return this.wishes.iterator();
  }
}
