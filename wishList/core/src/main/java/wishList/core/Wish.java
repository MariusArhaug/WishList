package wishList.core;

public class Wish {

  private final String name;
  private WishList belongTo;

  public Wish(String name) {
    if (name.length() == 0 || name.length() > 25) {

      throw new IllegalArgumentException("The wish can not be empty or surpass 25 character!");
    }
    this.name = name;
  }

  public Wish setBelongTo(WishList list) {
    this.belongTo = list;
    return this;
  }
  public String getName() {
    return this.name;
  }

  public WishList getBelongTo() {
    return this.belongTo;
  }

  public String toString() {
    return "" + this.name + "," + this.belongTo + ",";
  }
}
