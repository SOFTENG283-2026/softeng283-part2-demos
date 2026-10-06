package nz.ac.auckland.softeng283;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DiscountCalculatorTest {

  @Test
  void studentDiscount_isNonNegative() {
    // Arrange
    double total = 100.0;
    boolean student = true;

    // Act
    double result = DiscountCalculator.apply(total, student);

    // Assert
    assertTrue(result >= 0.0);
  }

  @Test
  void negativeTotal_isNonNegative() {
    // Arrange
    double total = -100.0;
    boolean student = true;

    // Act
    double result = DiscountCalculator.apply(total, student);

    // Assert
    assertTrue(result >= 0.0);
  }
}
