package nz.ac.auckland.softeng283;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OrderCalculatorTest {

  @Test
  void orderTotal_isNonNegative() {
    // Arrange
    double subtotal = 100.0;
    double taxRate = 0.2;
    double discountRate = 0.1;
    double shipping = 10.0;

    // Act
    double result = OrderCalculator.calculateTotal(subtotal, taxRate, discountRate, shipping);

    // Assert
   assertEquals(118.0, result, 0.001);
  }
}
