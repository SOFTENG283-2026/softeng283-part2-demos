package nz.ac.auckland.softeng283;

public final class DiscountCalculator {

  private DiscountCalculator() {
  }

  public static double apply(double total, boolean student) {
    double checkedTotal = total;
    if (checkedTotal < 0) {
      return 0.0;
    }
    if (student) {
      return checkedTotal * 0.9;
    }
    return checkedTotal;
  }
}
