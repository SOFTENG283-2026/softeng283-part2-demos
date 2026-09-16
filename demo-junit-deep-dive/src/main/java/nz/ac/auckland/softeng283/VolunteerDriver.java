package nz.ac.auckland.softeng283;

/** A volunteer's vehicle, with capacity measured in food portions. */
public class VolunteerDriver {

  private final String name;
  private final int capacity;
  private int carriedPortions;

  public VolunteerDriver(String name, int capacity) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("Driver name must not be blank");
    }
    if (capacity <= 0) {
      throw new IllegalArgumentException("Capacity must be positive");
    }
    this.name = name;
    this.capacity = capacity;
  }

  public String getName() {
    return name;
  }

  public int getCarriedPortions() {
    return carriedPortions;
  }

  public int getRemainingCapacity() {
    return capacity - carriedPortions;
  }

  /**
   * Allocates food to a recipient and loads it into this driver's vehicle.
   * Failed collections leave both the donation and vehicle unchanged.
   *
   * @throws IllegalArgumentException if the donation is null, recipient is blank,
   *     or portions are not positive
   * @throws CapacityExceededException if the vehicle has insufficient space
   * @throws InsufficientFoodException if the donation has insufficient food
   */
  public void collect(FoodDonation donation, String recipient, int portions) {
    if (donation == null) {
      throw new IllegalArgumentException("Donation must not be null");
    }
    if (recipient == null || recipient.isBlank()) {
      throw new IllegalArgumentException("Recipient name must not be blank");
    }
    if (portions <= 0) {
      throw new IllegalArgumentException("Portions must be positive");
    }
    if (portions > getRemainingCapacity()) {
      throw new CapacityExceededException("Vehicle capacity exceeded");
    }
    donation.reserveFor(recipient, portions);
    carriedPortions += portions;
  }

  /** Delivers the complete load, returning its size and freeing the vehicle's capacity. */
  public int deliver() {
    int deliveredPortions = carriedPortions;
    carriedPortions = 0;
    return deliveredPortions;
  }
}
