package wishList.core;

/** Wish object class. */
public class Wish {

  private String name;
  private WishList belongTo;

  /** Empty constructor for json test purposes. */
  public Wish() {}

  /**
   * Create a wish with only name. For test purposes.
   *
   * @param name content of wish
   */
  public Wish(String name) {
    this.setName(name);
  }

  /**
   * Create a wish with name and its belonging wish list.
   *
   * @param name content of wish
   * @param belongTo wish belongs to this wish list
   */
  public Wish(String name, WishList belongTo) {
    this.setName(name).setBelongTo(belongTo);
  }

  public String getName() {
    return this.name;
  }

  /**
   * Set name of wish.
   *
   * @param name name of wish
   * @return wish object
   * @throws IllegalArgumentException when name is not matching constraint
   */
  public Wish setName(String name) throws IllegalArgumentException {
    if (name.length() == 0) {
      throw new IllegalArgumentException("The wish can not be empty!");
    }
    this.name = name;
    return this;
  }

  WishList getBelongTo() {
    return this.belongTo;
  }

  /**
   * Set wishList as parent to wish.wq
   *
   * @param belongTo wishList parent
   */
  Wish setBelongTo(WishList belongTo) {
    this.belongTo = belongTo;
    return this;
  }

  @Override
  public String toString() {
    return "" + this.name + "";
  }
}
