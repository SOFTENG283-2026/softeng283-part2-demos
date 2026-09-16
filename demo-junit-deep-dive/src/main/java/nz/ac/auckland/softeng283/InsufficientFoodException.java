package nz.ac.auckland.softeng283;

/** Raised when a recipient requests more portions than a donation contains. */
public class InsufficientFoodException extends RuntimeException {
  public InsufficientFoodException(String message) {
    super(message);
  }
}
