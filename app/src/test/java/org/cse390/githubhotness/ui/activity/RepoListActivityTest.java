package org.cse390.githubhotness.ui.activity;

import org.cse390.githubhotness.BuildConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sudars on 11/1/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 22)
public class RepoListActivityTest {
  RepoListActivity activity;

  @Before
  public void before() {
//    activity = Robolectric.setupActivity(RepoListActivity.class);
  }

  @Test
  public void notNull() {
    assertThat(activity).isNotNull();
  }
}
