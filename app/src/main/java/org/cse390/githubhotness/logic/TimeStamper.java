package org.cse390.githubhotness.logic;

import android.support.annotation.NonNull;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

/**
 * Creates time stamps for use in querying the Github API.
 */
public class TimeStamper {
  DateTimeFormatter dateOnlyFormatter;
  DateTimeFormatter fullIsoFormatter;

  public TimeStamper() {
    dateOnlyFormatter = ISODateTimeFormat.date();
    fullIsoFormatter = ISODateTimeFormat.dateTimeNoMillis();
  }

  /**
   * Get the ISO8601 representation of a date, without a time zone, that is
   * one month ago from the given dateTime.
   * @param dateTime the time for now
   * @return
   */
  public String getOneMonthAgo(@NonNull DateTime dateTime) {
    LocalDate currentLocalDate = new LocalDate(dateTime);
    LocalDate oneMonthAgo = currentLocalDate.minusMonths(1);
    String result = dateOnlyFormatter.print(oneMonthAgo);
    return result;
  }

  public String getOneWeekAgo(@NonNull DateTime dateTime) {
    LocalDate currentLocalDate = new LocalDate(dateTime);
    LocalDate oneWeekAgo = currentLocalDate.minusWeeks(1);
    String result = dateOnlyFormatter.print(oneWeekAgo);
    return result;
  }

  /**
   * Returns a String representation of a datetime 24 hours ago, in UTC.
   * @param dateTime
   * @return
   */
  public String get24HoursAgo(@NonNull DateTime dateTime) {
    DateTime twentyFourHoursAgo = dateTime.minusHours(24)
        .toDateTime(DateTimeZone.UTC);
    String result = fullIsoFormatter.print(twentyFourHoursAgo);
    return result;
  }
}
