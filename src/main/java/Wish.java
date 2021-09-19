public class Wish {

  private final String wish;
  private boolean covered = false;
  private User coveredBy = null;
  private WishList belongTo;

  public Wish(String wish, WishList belongTo) {
    if (wish.length() != 0 && wish.length() < 25) {
      this.wish = wish;
    } else {
      throw new IllegalArgumentException("The wish can not be empty or surpass 25 character!");
    }
    if (belongTo != null) {
      this.belongTo = belongTo;
    } else {
      throw new IllegalArgumentException("A wish must belong to an existing wish list!");
    }
  }

  public String getWish() {
    return this.wish;
  }
  public boolean getCovered() {
    return this.covered;
  }

  public User getCoveredBy() {
    return this.coveredBy;
  }

  public WishList getBelongTo() {
    return this.belongTo;
  }

  /**
   * A user covers a wish on a wishlist they have been invited to
   * @param coveredBy User that covers a wish
   */
  public void coverAWish(User coveredBy) {
    this.covered = true;
    this.coveredBy = coveredBy;
  }

  public String toString() {
    return "" + this.wish + "," + this.belongTo + "," + this.covered + "," + this.coveredBy + "";
  }
}
