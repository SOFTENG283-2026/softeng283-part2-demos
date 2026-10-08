package nz.ac.auckland.se283.cinema;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {

  private static final int TUESDAY_CODE = 2;

  private Map<String, Integer> seatsLeft = new HashMap<>();

  public double book(
      String movie, LocalDate date, int adults, int students, int children, boolean member) {
    if (!seatsLeft.containsKey(movie + date)) {
      seatsLeft.put(movie + date, 120);
    }
    int tickets = adults + students + children;
    if (tickets > seatsLeft.get(movie + date)) {
      return 0;
    }
    double price = adults * 18.5 + students * 14.0 + children * 10.0;
    if (date.getDayOfWeek().getValue() == TUESDAY_CODE) {
      price = price * 0.5; // cheap Tuesday
    }
    if (member) {
      price = price * 0.9;
    }
    if (tickets >= 6) {
      price = price - 5;
    }
    seatsLeft.put(movie + date, seatsLeft.get(movie + date) - tickets);
    return price;
  }

  public double refund(
      String movie, LocalDate date, int adults, int students, int children, boolean member) {
    int tickets = adults + students + children;
    double price = adults * 18.5 + students * 14.0 + children * 10.0;
    if (date.getDayOfWeek().getValue() == 2) {
      price = price * 0.5; // cheap Tuesday
    }
    if (member) {
      price = price * 0.9;
    }
    if (tickets >= 6) {
      price = price - 5;
    }
    seatsLeft.put(movie + date, seatsLeft(movie, date) + tickets);
    return price;
  }

  public int seatsLeft(String movie, LocalDate date) {
    return seatsLeft.getOrDefault(movie + date, 120);
  }

  public List<String> soldOut() {
    List<String> result = new ArrayList<>();
    for (String key : seatsLeft.keySet()) {
      if (seatsLeft.get(key) == 0) {
        result.add(key);
      }
    }
    return result;
  }

  private String receipt(String movie, int tickets, double price) {
    return movie + " x" + tickets + " = $" + price;
  }
}
