package nz.ac.auckland.se283.cinema;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BookingServiceTest {

  private static final double DELTA = 0.001;
  private static final String MOVIE = "Avengers Doomsday";
  private static final LocalDate MONDAY = LocalDate.of(2026, 10, 5);
  private static final LocalDate TUESDAY = LocalDate.of(2026, 10, 6);
  private BookingService service;

  @BeforeEach
  void setUp() {
    service = new BookingService();
  }

  @Test
  void book_adultTicketsOnWeekday_returnsFullPrice() {
    // Arrange
    int adults = 2;

    // Act
    double price = service.book(MOVIE, MONDAY, adults, 0, 0, false);

    // Assert
    assertEquals(37.0, price, DELTA);
  }

  @Test
  void book_mixedTicketTypes_returnsCombinedPrice() {
    // Arrange
    int adults = 1;
    int students = 1;
    int children = 1;

    // Act
    double price = service.book(MOVIE, MONDAY, adults, students, children, false);

    // Assert
    assertEquals(42.5, price, DELTA);
  }

  @Test
  void book_tuesdayScreening_returnsHalfPrice() {
    // Arrange
    int adults = 2;

    // Act
    double price = service.book(MOVIE, TUESDAY, adults, 0, 0, false);

    // Assert
    assertEquals(18.5, price, DELTA);
  }

  @Test
  void book_memberBooking_appliesTenPercentDiscount() {
    // Arrange
    int adults = 2;

    // Act
    double price = service.book(MOVIE, MONDAY, adults, 0, 0, true);

    // Assert
    assertEquals(33.3, price, DELTA);
  }

  @Test
  void book_sixChildTickets_appliesGroupDiscount() {
    // Arrange
    int children = 6;

    // Act
    double price = service.book(MOVIE, MONDAY, 0, 0, children, false);

    // Assert
    assertEquals(55.0, price, DELTA);
  }

  @Test
  void book_tuesdayMemberGroup_appliesAllDiscounts() {
    // Arrange
    int adults = 6;

    // Act
    double price = service.book(MOVIE, TUESDAY, adults, 0, 0, true);

    // Assert
    assertEquals(44.95, price, DELTA);
  }

  @Test
  void book_successfulBooking_reducesAvailableSeats() {
    // Arrange
    int adults = 2;
    int students = 1;

    // Act
    service.book(MOVIE, MONDAY, adults, students, 0, false);

    // Assert
    assertEquals(117, service.seatsLeft(MOVIE, MONDAY));
  }

  @Test
  void book_moreTicketsThanAvailable_rejectsBooking() {
    // Arrange
    service.book(MOVIE, MONDAY, 120, 0, 0, false);

    // Act
    double price = service.book(MOVIE, MONDAY, 1, 0, 0, false);

    // Assert
    assertAll(
        () -> assertEquals(0.0, price, DELTA),
        () -> assertEquals(0, service.seatsLeft(MOVIE, MONDAY)),
        () -> assertEquals(1, service.soldOut().size()));
  }

  @Test
  void refund_existingBooking_returnsSeatsAndMoney() {
    // Arrange
    double paid = service.book(MOVIE, MONDAY, 2, 1, 0, true);

    // Act
    double refunded = service.refund(MOVIE, MONDAY, 2, 1, 0, true);

    // Assert
    assertAll(
        () -> assertEquals(paid, refunded, DELTA),
        () -> assertEquals(120, service.seatsLeft(MOVIE, MONDAY)));
  }
}
