package nz.ac.auckland.softeng283;

import static nz.ac.auckland.softeng283.FoodDonation.ProviderType.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/** Deliberately poor tests for a test-refactoring demonstration. All initially pass. */
public class FoodRedistributionTest {

  @Nested
  class BestPractice01 { //  Meaningful test names
    @Test
    void t1() {
      FoodDonation food = new FoodDonation("Neighbourhood Bakery", BAKERY, 100);
      food.addPortions(50);
      assertEquals(150, food.getAvailablePortions());
    }

    @Test
    void t2() {
      FoodDonation food = new FoodDonation("Neighbourhood Bakery", BAKERY, 100);
      food.reserveFor("Community Kitchen", 40);
      assertEquals(60, food.getAvailablePortions());
    }
  }

  @Nested
  class BestPractice02 { // Arrange–Act–Assert structure
    @Test
    void isFullyReserved_whenNoPortionsRemain_returnsTrue() {
      assertTrue(new FoodDonation("Local Farm", FARM, 0).isFullyReserved());
    }
  }

  @Nested
  class BestPractice03 { // Shared setup belongs in a fresh fixture, such as @BeforeEach
    @Test
    void addPortions_increasesAvailableFood() {
      FoodDonation food = new FoodDonation("Neighbourhood Bakery", BAKERY, 100);
      food.addPortions(20);
      assertEquals(120, food.getAvailablePortions());
    }

    @Test
    void reserveFor_decreasesAvailableFood() {
      FoodDonation food = new FoodDonation("Neighbourhood Bakery", BAKERY, 100);
      food.reserveFor("Food Relief Charity", 30);
      assertEquals(70, food.getAvailablePortions());
    }

    @Test
    void reserveFor_entireBatch_leavesNoAvailableFood() {
      FoodDonation food = new FoodDonation("Neighbourhood Bakery", BAKERY, 100);
      food.reserveFor("Community Kitchen", 100);
      assertTrue(food.isFullyReserved());
    }
  }

  @Nested
  class BestPractice04 { // Parameterised tests for repeated cases
    @Test
    void addPortions_10_makes10Available() {
      FoodDonation food = new FoodDonation("Local Restaurant", RESTAURANT, 0);
      food.addPortions(10);
      assertEquals(10, food.getAvailablePortions());
    }

    @Test
    void addPortions_20_makes20Available() {
      FoodDonation food = new FoodDonation("Local Restaurant", RESTAURANT, 0);
      food.addPortions(20);
      assertEquals(20, food.getAvailablePortions());
    }

    @Test
    void addPortions_50_makes50Available() {
      FoodDonation food = new FoodDonation("Local Restaurant", RESTAURANT, 0);
      food.addPortions(50);
      assertEquals(50, food.getAvailablePortions());
    }
  }

  @Nested
  class BestPractice05 { // assertThrows for expected exceptions
    @Test
    void reserveFor_tooManyPortions_throwsInsufficientFoodException() {
      FoodDonation food = new FoodDonation("Local Farm", FARM, 10);
      try {
        food.reserveFor("Food Relief Charity", 20);
        fail("Expected InsufficientFoodException");
      } catch (InsufficientFoodException exception) {
        // Expected, but assertThrows would express this more clearly.
      }
    }

    @Test
    void collect_overVehicleCapacity_throwsCapacityExceededException() {
      FoodDonation food = new FoodDonation("Local Supermarket", SUPERMARKET, 100);
      VolunteerDriver driver = new VolunteerDriver("Aroha", 30);
      try {
        driver.collect(food, "Community Kitchen", 40);
        fail("Expected CapacityExceededException");
      } catch (CapacityExceededException exception) {
        // Expected, but assertThrows would express this more clearly.
      }
    }
  }

  @Nested
  class BestPractice06 { // One logical behaviour per test
    @Test
    void everythingWorks() {
      FoodDonation food = new FoodDonation("Local Restaurant", RESTAURANT, 100);
      VolunteerDriver driver = new VolunteerDriver("Aroha", 50);
      food.addPortions(20);
      assertEquals(120, food.getAvailablePortions());
      driver.collect(food, "Community Kitchen", 30);
      assertEquals(30, driver.getCarriedPortions());
      assertEquals(30, driver.deliver());
      assertEquals(0, driver.getCarriedPortions());
    }
  }

  @Nested
  class BestPractice07 { // assertAll for related assertions
    @Test
    void collect_updatesDonationAndDriver() {
      FoodDonation food = new FoodDonation("Local Supermarket", SUPERMARKET, 100);
      VolunteerDriver driver = new VolunteerDriver("Aroha", 50);

      driver.collect(food, "Food Relief Charity", 30);

      assertEquals(70, food.getAvailablePortions());
      assertEquals(30, driver.getCarriedPortions());
      assertEquals(20, driver.getRemainingCapacity());
    }
  }

  @Nested
  class BestPractice08 { // Avoid loops and calculated expectations in simple tests
    @Test
    void addPortions_multipleAdditions_accumulatesFood() {
      FoodDonation food = new FoodDonation("Local Farm", FARM, 0);
      int[] portions = {10, 20, 30};
      int expected = 0;
      for (int amount : portions) {
        food.addPortions(amount);
        expected += amount;
      }
      assertEquals(expected, food.getAvailablePortions());
    }
  }

  @Nested
  class BestPractice09 { // Test the public API rather than private methods
    @Test
    void validatePortions_negativeInput_throwsException_usingReflection() throws Exception {
      FoodDonation food = new FoodDonation("Neighbourhood Bakery", BAKERY, 100);
      Method method = FoodDonation.class.getDeclaredMethod("validatePortions", int.class);
      method.setAccessible(true);

      InvocationTargetException exception =
          assertThrows(InvocationTargetException.class, () -> method.invoke(food, -5));
      assertInstanceOf(IllegalArgumentException.class, exception.getCause());
      assertEquals("Portions must be positive", exception.getCause().getMessage());
    }
  }

  @Nested
  class BestPractice10 { // Named constants for scenario values
    @Test
    void collect_30Portions_leaves70Available() {
      FoodDonation food = new FoodDonation("Local Supermarket", SUPERMARKET, 100);
      VolunteerDriver driver = new VolunteerDriver("Aroha", 50);
      driver.collect(food, "Community Kitchen", 30);
      assertEquals(70, food.getAvailablePortions());
    }
  }
}
