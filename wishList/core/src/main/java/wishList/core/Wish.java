package wishList.core;

/** Wish object class. */
public class Wish {

  private String name;
  private WishList belongTo;

  public Wish() {}

  public Wish(String name) {
    this.setName(name);
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
    if (name.length() == 0 || name.length() > 25) {

      throw new IllegalArgumentException("The wish can not be empty or surpass 25 character!");
    }
    this.name = name;
    return this;
  }

  public WishList getBelongTo() {
    return this.belongTo;
  }

  /**
   * Set wishList as parent to wish.
   *
   * @param list wishList parent
   * @return this object
   */
  public Wish setBelongTo(WishList list) {
    this.belongTo = list;
    return this;
  }

  @Override
  public String toString() {
    return "" + this.name + "," + this.belongTo + ",";
  }
}
