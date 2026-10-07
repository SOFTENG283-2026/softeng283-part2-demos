package nz.ac.auckland.se283.cinema;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** The starter tests after Introduce Parameter Object (IntelliJ updates the call sites for you), plus one output check. */
class BookingServiceTest {

  private static final double DELTA = 0.001;
  private static final Screening MONDAY =
      new Screening("Avengers Doomsday", LocalDate.of(2026, 10, 5));
  private static final Screening TUESDAY =
      new Screening("Avengers Doomsday", LocalDate.of(2026, 10, 6));
  private List<String> output;
  private BookingService service;

  @BeforeEach
  void setUp() {
    output = new ArrayList<>();
    service = new BookingService(output::add);
  }

  @Test
  void book_adultTicketsOnWeekday_returnsFullPrice() {
    // Arrange
    Tickets tickets = new Tickets(2, 0, 0);

    // Act
    double price = service.book(MONDAY, tickets, false);

    // Assert
    assertEquals(37.0, price, DELTA);
  }

  @Test
  void book_mixedTicketTypes_returnsCombinedPrice() {
    // Arrange
    Tickets tickets = new Tickets(1, 1, 1);

    // Act
    double price = service.book(MONDAY, tickets, false);

    // Assert
    assertEquals(42.5, price, DELTA);
  }

  @Test
  void book_tuesdayScreening_returnsHalfPrice() {
    // Arrange
    Tickets tickets = new Tickets(2, 0, 0);

    // Act
    double price = service.book(TUESDAY, tickets, false);

    // Assert
    assertEquals(18.5, price, DELTA);
  }

  @Test
  void book_memberBooking_appliesTenPercentDiscount() {
    // Arrange
    Tickets tickets = new Tickets(2, 0, 0);

    // Act
    double price = service.book(MONDAY, tickets, true);

    // Assert
    assertEquals(33.3, price, DELTA);
  }

  @Test
  void book_sixChildTickets_appliesGroupDiscount() {
    // Arrange
    Tickets tickets = new Tickets(0, 0, 6);

    // Act
    double price = service.book(MONDAY, tickets, false);

    // Assert
    assertEquals(55.0, price, DELTA);
  }

  @Test
  void book_tuesdayMemberGroup_appliesAllDiscounts() {
    // Arrange
    Tickets tickets = new Tickets(6, 0, 0);

    // Act
    double price = service.book(TUESDAY, tickets, true);

    // Assert
    assertEquals(44.95, price, DELTA);
  }

  @Test
  void book_successfulBooking_reducesAvailableSeats() {
    // Arrange
    Tickets tickets = new Tickets(2, 1, 0);

    // Act
    service.book(MONDAY, tickets, false);

    // Assert
    assertEquals(117, service.seatsLeft(MONDAY));
  }

  @Test
  void book_moreTicketsThanAvailable_rejectsBooking() {
    // Arrange
    service.book(MONDAY, new Tickets(120, 0, 0), false);

    // Act
    double price = service.book(MONDAY, new Tickets(1, 0, 0), false);

    // Assert
    assertAll(
        () -> assertEquals(0.0, price, DELTA),
        () -> assertEquals(0, service.seatsLeft(MONDAY)),
        () -> assertEquals(1, service.soldOut().size()));
  }

  @Test
  void refund_existingBooking_returnsSeatsAndMoney() {
    // Arrange
    Tickets tickets = new Tickets(2, 1, 0);
    double paid = service.book(MONDAY, tickets, true);

    // Act
    double refunded = service.refund(MONDAY, tickets, true);

    // Assert
    assertAll(
        () -> assertEquals(paid, refunded, DELTA),
        () -> assertEquals(120, service.seatsLeft(MONDAY)));
  }

  @Test
  void book_successfulBooking_reportsReceiptToOutput() {
    // Arrange
    Tickets tickets = new Tickets(2, 0, 0);

    // Act
    service.book(MONDAY, tickets, false);

    // Assert
    assertEquals(List.of("Booked 2 tickets for Avengers Doomsday: $37.0"), output);
  }
}
