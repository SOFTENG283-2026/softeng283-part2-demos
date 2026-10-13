package nz.ac.auckland.softeng283.rescue;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ReservationIntegrationTest {

  private static final String DONATION_ID = "pohutukawa-bakery-donation";
  private static final String ORGANISATION_ID = "tui-community-pantry";

  @Test
  public void scenario_state_expectedResult() {
    DonationRepository donationRepository = new DonationRepository();
    FoodRescueService service = new FoodRescueService(donationRepository);

    boolean reserved = service.reserve(DONATION_ID, ORGANISATION_ID, 20);

    assertTrue(reserved);
  }
}
