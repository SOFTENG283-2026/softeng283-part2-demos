package nz.ac.auckland.se283.cinema;

/** How many tickets of each type. Replaces the adults/students/children data clump. */
public record Tickets(int adults, int students, int children) {

  private static final double ADULT_PRICE = 18.5;
  private static final double STUDENT_PRICE = 14.0;
  private static final double CHILD_PRICE = 10.0;
  private static final int GROUP_SIZE = 6;

  public int count() {
    return adults + students + children;
  }

  public double basePrice() {
    return adults * ADULT_PRICE + students * STUDENT_PRICE + children * CHILD_PRICE;
  }

  public boolean isGroup() {
    return count() >= GROUP_SIZE;
  }
}
