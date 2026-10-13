package nz.ac.auckland.softeng283.rescue;

public interface DonationRepository {

  int getAvailablePortions(String donationId);

  void reserve(String donationId, String organisationId, int portions);
}
