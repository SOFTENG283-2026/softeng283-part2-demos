package nz.ac.auckland.softeng283;

/** A batch of surplus food offered to charities and community kitchens. */
public class FoodDonation {

  public enum ProviderType {
    RESTAURANT, SUPERMARKET, BAKERY, FARM
  }

  private final String providerName;
  private final ProviderType providerType;
  private int availablePortions;

  /** Creates a donation with a non-negative number of available portions. */
  public FoodDonation(String providerName, ProviderType providerType, int availablePortions) {
    if (providerName == null || providerName.isBlank()) {
      throw new IllegalArgumentException("Provider name must not be blank");
    }
    if (providerType == null) {
      throw new IllegalArgumentException("Provider type must not be null");
    }
    if (availablePortions < 0) {
      throw new IllegalArgumentException("Available portions cannot be negative");
    }
    this.providerName = providerName;
    this.providerType = providerType;
    this.availablePortions = availablePortions;
  }

  public String getProviderName() {
    return providerName;
  }

  public ProviderType getProviderType() {
    return providerType;
  }

  public int getAvailablePortions() {
    return availablePortions;
  }

  /** Adds a positive number of newly offered portions to this batch. */
  public void addPortions(int portions) {
    validatePortions(portions);
    availablePortions = Math.addExact(availablePortions, portions);
  }

  /**
   * Allocates portions to a named charity or community kitchen.
   *
   * @throws IllegalArgumentException if the recipient is blank or portions are not positive
   * @throws InsufficientFoodException if the batch cannot fulfil the request
   */
  public void reserveFor(String recipient, int portions) {
    if (recipient == null || recipient.isBlank()) {
      throw new IllegalArgumentException("Recipient name must not be blank");
    }
    validatePortions(portions);
    if (portions > availablePortions) {
      throw new InsufficientFoodException("Not enough food portions available");
    }
    availablePortions -= portions;
  }

  public boolean isFullyReserved() {
    return availablePortions == 0;
  }

  private void validatePortions(int portions) {
    if (portions <= 0) {
      throw new IllegalArgumentException("Portions must be positive");
    }
  }
}
