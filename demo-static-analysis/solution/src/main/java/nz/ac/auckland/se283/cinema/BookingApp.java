package nz.ac.auckland.se283.cinema;

import java.time.LocalDate;

/** Command-line entry point: books tickets and prints what the customer has to pay. */
public class BookingApp {

  // FALSE POSITIVE: this class IS the console boundary, so printing is its job.
  // All other output is injected into BookingService, which no longer touches System.out.
  @SuppressWarnings("PMD.SystemPrintln")
  public static void main(String[] args) {
    BookingService service = new BookingService(System.out::println);
    LocalDate date = LocalDate.parse(args[0]);
    double total =
        service.book(new Screening("Avengers Doomsday", date), new Tickets(2, 1, 0), true);
    System.out.println("Total to pay: $" + total);
  }
}
