package nz.ac.auckland.se283.cinema;

/** The pricing rules, extracted so book() and refund() no longer duplicate them. */
public class PriceCalculator {

  private static final double TUESDAY_FACTOR = 0.5;
  private static final double MEMBER_FACTOR = 0.9;
  private static final double GROUP_DISCOUNT = 5;

  public double price(Screening screening, Tickets tickets, boolean member) {
    double price = tickets.basePrice();
    if (screening.isCheapTuesday()) {
      price = price * TUESDAY_FACTOR;
    }
    if (member) {
      price = price * MEMBER_FACTOR;
    }
    if (tickets.isGroup()) {
      price = price - GROUP_DISCOUNT;
    }
    return price;
  }
}
