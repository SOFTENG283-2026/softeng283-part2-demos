package nz.ac.auckland.softeng283.refunds;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RefundCalculatorTest {

    private RefundCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new RefundCalculator();
    }

    @Nested
    @DisplayName("Invalid Input Exception Tests")
    class ExceptionHandlingTests {

        @Test
        @DisplayName("Should throw IllegalArgumentException when ticket price is negative")
        void negativeTicketPrice_throwsException() {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculateRefund(-1, 100, 25));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when booking fee is negative")
        void negativeBookingFee_throwsException() {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculateRefund(1000, -1, 25));
        }

        @Test
        @DisplayName("Should throw IllegalArgumentException when notice hours is negative")
        void negativeHours_throwsException() {
            assertThrows(IllegalArgumentException.class, () -> calculator.calculateRefund(1000, 100, -1));
        }
    }

    @Nested
    @DisplayName("Requirement & Mutation Coverage Tests")
    class CalculationTests {

        @ParameterizedTest(name = "ticket={0}c, fee={1}c, hours={2} -> expected refund={3}c")
        @CsvSource({
            // --- 100% Refund Bracket (>= 24 hours) ---
            "2000, 200, 25, 2000",  // > 24 hours
            "2000, 200, 24, 2000",  // Kills mutant: hoursUntilStart >= 24 -> hoursUntilStart > 24

            // --- 50% Refund Bracket (2 <= hours < 24) ---
            "2000, 200, 23, 1000",  // Boundary: 23 hours
            "2000, 200, 12, 1000",  // Mid-range: 12 hours
            "2000, 200,  2, 1000",  // Kills mutant: hoursUntilStart >= 2 -> hoursUntilStart > 2

            // --- 0% Refund Bracket (< 2 hours) ---
            "2000, 200,  1,    0",  // Boundary: 1 hour
            "2000, 200,  0,    0",  // Kills mutant: hoursUntilStart < 0 -> hoursUntilStart <= 0

            // --- Zero-Value Input Boundaries (Kills remaining condition mutants) ---
            "   0, 200, 25,    0",  // Kills mutant: ticketPriceCents < 0 -> ticketPriceCents <= 0
            "2000,   0, 25, 2000",  // Kills mutant: bookingFeeCents < 0 -> bookingFeeCents <= 0
            "2000,   0, 12, 1000",  // 0 booking fee at 50% refund

            // --- Booking Fee Exclusion Verification ---
            "2000, 500, 25, 2000",
            "2000, 500, 12, 1000",

            // --- Integer Division Truncation ---
            "1001, 200, 12,  500"
        })
        void calculateRefund_validInputs_returnsCorrectRefund(
                int ticketPrice, int bookingFee, int hours, int expectedRefund) {

            int actualRefund = calculator.calculateRefund(ticketPrice, bookingFee, hours);
            assertEquals(expectedRefund, actualRefund);
        }
    }
}