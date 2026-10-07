package nz.ac.auckland.softeng283;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    assertEquals(90.0, result, 0.0001);
  }

  @Test
  void negativeTotal_isNonNegative() {
    // Arrange
    double total = -100.0;
    boolean student = true;

    // Act
    double result = DiscountCalculator.apply(total, student);

    // Assert
    assertEquals(0.0, result, 0.00001);
  }

  @Test
  void nostudentDiscount_noDiscount() {
    // Arrange
    double total = 100.0;
    boolean student = false;

    // Act
    double result = DiscountCalculator.apply(total, student);

    // Assert
    assertEquals(100.0, result, 0.00001);
  }
}
