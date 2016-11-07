package com.samsudar.githubhotness.logic;

import com.samsudar.githubhotness.BuildConfig;
import com.samsudar.githubhotness.TestGithubHotnessApplication;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sudars on 11/3/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(
    constants = BuildConfig.class,
    sdk = 22,
    application = TestGithubHotnessApplication.class
)
public class TimeStamperTest {
  TimeStamper timeStamper;
  DateTime nov_03_2016;

  @Before
  public void before() {
    timeStamper = new TimeStamper();
    nov_03_2016 = new DateTime("2016-11-03T11:05:00.000-07:00");
  }

  @Test
  public void getOneMonthAgo() {
    // We are inputting 2016-11-03, and expecting to get in return 2016-10-03.
    String expected = "2016-10-03";
    String actual = timeStamper.getOneMonthAgo(nov_03_2016);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void getOneWeekAgo() {
    String expected = "2016-10-27";
    String actual = timeStamper.getOneWeekAgo(nov_03_2016);
    assertThat(actual).isEqualTo(expected);
  }

  @Test
  public void get24HoursAgo() {
    String expected = "2016-11-02T11:05:00-07:00";
    String actual = timeStamper.get24HoursAgo(nov_03_2016);
    assertThat(actual).isEqualTo(expected);
  }
}
