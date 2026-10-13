package nz.ac.auckland.softeng283.rescue;

import java.util.HashSet;
import java.util.Set;

public class DonationRepository {

  private final Set<String> reservations;

  public DonationRepository(){
    reservations = new HashSet<>();
  }

  public int getAvailablePortions(String donationId) {
  // TODO 
    return 30;
  }

  public void reserve(String donationId, String organisationId, int portions) {
    reservations.add(donationId);

  }
}
