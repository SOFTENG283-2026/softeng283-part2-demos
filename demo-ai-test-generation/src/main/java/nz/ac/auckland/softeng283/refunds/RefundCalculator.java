package nz.ac.auckland.softeng283.refunds;

/** Calculates the refund for a cancelled cinema booking. */
public class RefundCalculator {

  public int calculateRefund(int ticketPriceCents, int bookingFeeCents, int hoursUntilStart) {
    if (ticketPriceCents < 0 || bookingFeeCents < 0 || hoursUntilStart < 0) {
      throw new IllegalArgumentException("Prices and notice must not be negative");
    }

    if (hoursUntilStart >= 24) {
      return ticketPriceCents;
    }
    if (hoursUntilStart >= 2) {
      return ticketPriceCents / 2;
    }
    return 0;
  }
}
