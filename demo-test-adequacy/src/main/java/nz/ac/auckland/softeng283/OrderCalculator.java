package nz.ac.auckland.softeng283;

public final class OrderCalculator {

  private OrderCalculator() {
  }

  public static double calculateTotal(
      double subtotal, double taxRate, double discountRate, double shipping) {
    double discountedSubtotal = subtotal * (1 - discountRate);
    double taxAmount = discountedSubtotal * taxRate;
    double total = discountedSubtotal + taxAmount + shipping;
    return total;
  }
}
