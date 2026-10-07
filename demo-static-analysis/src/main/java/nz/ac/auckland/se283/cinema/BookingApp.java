package nz.ac.auckland.se283.cinema;

import java.time.LocalDate;

public class BookingApp {

  public static void main(String[] args) {
    BookingService service = new BookingService();
    LocalDate date = LocalDate.parse(args[0]);
    double total = service.book("Avengers Doomsday", date, 2, 1, 0, true);
    System.out.println("Total to pay: $" + total);
  }
}
