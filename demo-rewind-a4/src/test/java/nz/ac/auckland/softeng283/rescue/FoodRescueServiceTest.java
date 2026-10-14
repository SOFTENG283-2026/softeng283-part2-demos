package nz.ac.auckland.softeng283.rescue;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FoodRescueServiceTest {

  private static final String DONATION_ID = "pohutukawa-bakery-donation";
  private static final String ORGANISATION_ID = "tui-community-pantry";

  @Test
  void reserve_availableDonation_recordsReservation() {
    DonationRepository donationRepository = Mockito.mock(DonationRepository.class);
    FoodRescueService service = new FoodRescueService(donationRepository);
    Mockito.when(donationRepository.getAvailablePortions(DONATION_ID)).thenReturn(30);

    boolean reserved = service.reserve(DONATION_ID, ORGANISATION_ID, 20);

    assertTrue(reserved);
    Mockito.verify(donationRepository).reserve(DONATION_ID, ORGANISATION_ID, 20);
  }

  @Test
  void reserve_notEnoughRequest_reservationAborted() {
    DonationRepository donationRepository = Mockito.mock(DonationRepository.class);
    FoodRescueService service = new FoodRescueService(donationRepository);

    boolean reserved = service.reserve(DONATION_ID, ORGANISATION_ID, 2);

    assertFalse(reserved);
  }

  @Test
  void reserve_notEnoughRequestBoundary_reservationAborted() {
    DonationRepository donationRepository = Mockito.mock(DonationRepository.class);
    FoodRescueService service = new FoodRescueService(donationRepository);

    boolean reserved = service.reserve(DONATION_ID, ORGANISATION_ID, 4);

    assertFalse(reserved);
  }


    @Test
  void reserve_availableDonationexactly5_recordsReservation() {
    DonationRepository donationRepository = Mockito.mock(DonationRepository.class);
    FoodRescueService service = new FoodRescueService(donationRepository);
    Mockito.when(donationRepository.getAvailablePortions(DONATION_ID)).thenReturn(30);

    boolean reserved = service.reserve(DONATION_ID, ORGANISATION_ID, 5);

    assertTrue(reserved);
    Mockito.verify(donationRepository).reserve(DONATION_ID, ORGANISATION_ID, 5);
  }

}
