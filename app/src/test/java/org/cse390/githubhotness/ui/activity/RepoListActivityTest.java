package org.cse390.githubhotness.ui.activity;

import org.cse390.githubhotness.BuildConfig;
import org.cse390.githubhotness.TestGithubHotnessApplication;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityComponent;
import org.cse390.githubhotness.ui.widget.RepoListPagerAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by sudars on 11/1/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(
    constants = BuildConfig.class,
    sdk = 22,
    application = TestGithubHotnessApplication.class
)
public class RepoListActivityTest {
  RepoListActivity activity;

  @Mock
  RepoListActivityComponent mockRepoListActivityComponent;
  @Mock
  RepoListPagerAdapter repoListPagerAdapter;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) {
        RepoListActivity activity = (RepoListActivity)
            invocation.getArguments()[0];
        activity.pagerAdapter = repoListPagerAdapter;
        return null;
      }
    })
        .when(mockRepoListActivityComponent)
        .inject(any(RepoListActivity.class));

    TestGithubHotnessApplication app = (TestGithubHotnessApplication)
        RuntimeEnvironment.application;
    app.setRepoListActivityComponent(mockRepoListActivityComponent);

    activity = Robolectric.setupActivity(RepoListActivity.class);
  }

  @Test
  public void notNull() {
    assertThat(activity).isNotNull();
  }
}
