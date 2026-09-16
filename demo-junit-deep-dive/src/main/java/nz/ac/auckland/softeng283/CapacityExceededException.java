package nz.ac.auckland.softeng283;

/** Raised when a collection would exceed a volunteer's vehicle capacity. */
public class CapacityExceededException extends RuntimeException {
  public CapacityExceededException(String message) {
    super(message);
  }
}
