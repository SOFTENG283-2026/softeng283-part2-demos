package nz.ac.auckland.softeng283.refunds;

/** Calculates the refund for a cancelled cinema booking. */
public class RefundCalculator {

  public int calculateRefund(int ticketPriceCents, int bookingFeeCents, int hoursUntilStart) {
    if (ticketPriceCents < 0 || bookingFeeCents < 0 || hoursUntilStart < 0) {
      throw new IllegalArgumentException("Prices and notice must not be negative");
    }

    int amountPaid = ticketPriceCents + bookingFeeCents;
    if (hoursUntilStart > 24) {
      return amountPaid;
    }
    if (hoursUntilStart >= 2) {
      return amountPaid / 2;
    }
    return 0;
  }
}

