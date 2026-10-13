package nz.ac.auckland.softeng283.rescue;

public class FoodRescueService {

  private final DonationRepository donationRepository;

  public FoodRescueService(DonationRepository donationRepository) {
    this.donationRepository = donationRepository;
  }

  public boolean reserve(String donationId, String organisationId, int requestedPortions) {
    if (requestedPortions < 5) {
      return false;
    }

    int availablePortions = donationRepository.getAvailablePortions(donationId);
    if (availablePortions < requestedPortions) {
      return false;
    }

    donationRepository.reserve(donationId, organisationId, requestedPortions);
    return true;
  }
}
