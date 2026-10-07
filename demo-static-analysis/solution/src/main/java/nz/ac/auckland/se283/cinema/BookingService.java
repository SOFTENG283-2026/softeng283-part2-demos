package nz.ac.auckland.se283.cinema;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BookingService {

  private static final int CAPACITY = 120;

  private final Map<Screening, Integer> seatsLeft = new HashMap<>();
  private final PriceCalculator pricing = new PriceCalculator();
  private final Consumer<String> output;

  public BookingService(Consumer<String> output) {
    this.output = output;
  }

  public double book(Screening screening, Tickets tickets, boolean member) {
    int available = seatsLeft(screening);
    if (tickets.count() > available) {
      output.accept("Sold out: " + screening.movie() + " on " + screening.date());
      return 0;
    }
    double price = pricing.price(screening, tickets, member);
    seatsLeft.put(screening, available - tickets.count());
    output.accept("Booked " + tickets.count() + " tickets for " + screening.movie() + ": $" + price);
    return price;
  }

  public double refund(Screening screening, Tickets tickets, boolean member) {
    double price = pricing.price(screening, tickets, member);
    seatsLeft.put(screening, seatsLeft(screening) + tickets.count());
    output.accept("Refunded " + tickets.count() + " tickets for " + screening.movie() + ": $" + price);
    return price;
  }

  public int seatsLeft(Screening screening) {
    return seatsLeft.getOrDefault(screening, CAPACITY);
  }

  public List<Screening> soldOut() {
    return seatsLeft.entrySet().stream()
        .filter(entry -> entry.getValue() == 0)
        .map(Map.Entry::getKey)
        .toList();
  }
}
