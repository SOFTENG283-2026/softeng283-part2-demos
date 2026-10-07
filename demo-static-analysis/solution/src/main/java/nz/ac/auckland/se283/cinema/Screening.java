package nz.ac.auckland.se283.cinema;

import java.time.DayOfWeek;
import java.time.LocalDate;

/** A movie shown on a given day. Replaces the movie + date data clump (and the "movie + date" string key). */
public record Screening(String movie, LocalDate date) {

  public boolean isCheapTuesday() {
    return date.getDayOfWeek() == DayOfWeek.TUESDAY;
  }
}
