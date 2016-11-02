package org.cse390.githubhotness.ui.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.cse390.githubhotness.BuildConfig;
import org.cse390.githubhotness.TestGithubHotnessApplication;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.ui.activity.component.RepoListActivityComponent;
import org.cse390.githubhotness.ui.activity.module.RepoListActivityModule;
import org.cse390.githubhotness.ui.activity.presenter.RepoListActivityPresenter;
import org.cse390.githubhotness.widgets.RepoRecyclerViewAdapter;
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
import org.robolectric.util.ActivityController;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
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
  RepoListActivityPresenter mockPresenter;
  @Mock
  RepoRecyclerViewAdapter mockAdapter;
  @Mock
  RecyclerView mockRecyclerView;
  @Mock
  SwipeRefreshLayout mockRefreshLayout;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);

    doAnswer(new Answer() {
      @Override
      public Object answer(InvocationOnMock invocation) {
        RepoListActivity activity = (RepoListActivity)
            invocation.getArguments()[0];
        activity.presenter = mockPresenter;
        activity.adapter = mockAdapter;
        activity.layoutManager = new LinearLayoutManager(
            RuntimeEnvironment.application
        );
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

  @Test
  public void setReposUpdatesAdapter() {
    List<Repo> repos = new ArrayList<>();
    activity.setRepos(repos);
    verify(mockAdapter, times(1)).replaceDataset(repos);
  }

  @Test
  public void searchResultsAutoLoad() {
    verify(mockPresenter, times(1)).loadSearchResults();
  }

  @Test
  public void clickingFabLoadsRepos() {
    // Replace the presenter to avoid the autoclick.
    mockPresenter = mock(RepoListActivityPresenter.class);
    activity.presenter = mockPresenter;
    activity.fab.performClick();
    verify(mockPresenter, times(1)).loadSearchResults();
  }

  @Test
  public void updateViewStateCorrect_error() {
    injectMockSwipeToRefresh();
    activity.updateViewState(RepoListActivity.RecyclerViewState.ERROR);
    assertThat(activity.tvError).isVisible();
    assertThat(activity.tvEmpty).isGone();
    assertThat(activity.rvRepos).isGone();
    verify(activity.refreshLayout, times(1)).setRefreshing(false);
  }

  @Test
  public void updateViewStateCorrect_loaded() {
    injectMockSwipeToRefresh();
    activity.updateViewState(RepoListActivity.RecyclerViewState.LOADED);
    assertThat(activity.tvError).isGone();
    assertThat(activity.tvEmpty).isGone();
    assertThat(activity.rvRepos).isVisible();
    verify(activity.refreshLayout, times(1)).setRefreshing(false);
  }

  @Test
  public void updateViewStateCorrect_loading() {
    injectMockSwipeToRefresh();
    activity.updateViewState(RepoListActivity.RecyclerViewState.LOADING);
    assertThat(activity.tvError).isGone();
    assertThat(activity.tvEmpty).isGone();
    assertThat(activity.rvRepos).isVisible();
    verify(activity.refreshLayout, times(1)).setRefreshing(true);
  }

  @Test
  public void updateViewStateCorrect_empty() {
    injectMockSwipeToRefresh();
    activity.updateViewState(RepoListActivity.RecyclerViewState.EMPTY);
    assertThat(activity.tvError).isGone();
    assertThat(activity.rvRepos).isGone();
    assertThat(activity.tvEmpty).isVisible();
    verify(activity.refreshLayout, times(1)).setRefreshing(false);
  }

  private void injectMockSwipeToRefresh() {
    activity.refreshLayout = mockRefreshLayout;
  }
}
