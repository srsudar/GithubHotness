package org.cse390.githubhotness.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import org.cse390.githubhotness.BuildConfig;
import org.cse390.githubhotness.R;
import org.cse390.githubhotness.TestGithubHotnessApplication;
import org.cse390.githubhotness.injection.ui.view.RepoListViewComponent;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.persistence.PreferenceAccessor;
import org.cse390.githubhotness.ui.view.presenter.RepoListViewPresenter;
import org.cse390.githubhotness.widgets.RepoRecyclerViewAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;

import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by sudars on 11/3/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(
    constants = BuildConfig.class,
    sdk = 22,
    application = TestGithubHotnessApplication.class
)
public class RepoListViewTest {
  private static final int DEFAULT_PER_PAGE = 7;

  RepoListView repoListView;

  @Mock
  RepoListViewPresenter mockPresenter;
  @Mock
  RepoRecyclerViewAdapter mockAdapter;
  @Mock
  RepoListViewComponent mockComponent;
  @Mock
  SwipeRefreshLayout mockRefreshLayout;
  @Mock
  PreferenceAccessor mockPreferenceAccessor;

  RecyclerView.LayoutManager layoutManager;
  RepoListViewPresenter.SearchPeriod searchPeriod;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    when(mockPreferenceAccessor.getPerPagePreference(any(Context.class)))
        .thenReturn(DEFAULT_PER_PAGE);
    layoutManager = new LinearLayoutManager(RuntimeEnvironment.application);
    searchPeriod = RepoListViewPresenter.SearchPeriod.MONTH;

    when(mockComponent.inject(any(RepoListView.class)))
        .thenAnswer(new Answer() {
          @Override
          public Object answer(InvocationOnMock invocation) {
            RepoListView view = (RepoListView) invocation.getArguments()[0];
            view.presenter = mockPresenter;
            view.adapter = mockAdapter;
            view.layoutManager = layoutManager;
            view.searchPeriod = searchPeriod;
            view.refreshLayout = mockRefreshLayout;
            view.preferenceAccessor = mockPreferenceAccessor;
            return null;
          }
    });

    TestGithubHotnessApplication app = (TestGithubHotnessApplication)
        RuntimeEnvironment.application;
    app.setSetRepoListViewComponent(mockComponent);

    LayoutInflater inflater = LayoutInflater.from(RuntimeEnvironment
        .application);
    repoListView = (RepoListView) inflater.inflate(R.layout
        .inflatable_repo_list_view,
        null);
  }

  @Test
  public void loadSearchResults_callsPresenter() {
    RepoListViewPresenter.SearchPeriod expected = RepoListViewPresenter
        .SearchPeriod.DAY;
    repoListView.setSearchPeriod(expected);
    repoListView.loadSearchResults();
    verify(mockPresenter, times(1)).loadSearchResults(RepoListViewPresenter
        .SearchPeriod.DAY, DEFAULT_PER_PAGE);
  }

  @Test
  public void setReposUpdatesAdapter() {
    List<Repo> repos = new ArrayList<>();
    repoListView.setRepos(repos);
    verify(mockAdapter, times(1)).replaceDataset(repos);
  }

  @Test
  public void updateViewStateCorrect_error() {
    injectMockSwipeToRefresh();
    repoListView.updateViewState(RepoListView.ViewState.ERROR);
    assertThat(repoListView.tvError).isVisible();
    assertThat(repoListView.tvEmpty).isGone();
    assertThat(repoListView.rvRepos).isGone();
    verify(repoListView.refreshLayout, times(1)).setRefreshing(false);
  }

  @Test
  public void updateViewStateCorrect_loaded() {
    injectMockSwipeToRefresh();
    repoListView.updateViewState(RepoListView.ViewState.LOADED);
    assertThat(repoListView.tvError).isGone();
    assertThat(repoListView.tvEmpty).isGone();
    assertThat(repoListView.rvRepos).isVisible();
    verify(repoListView.refreshLayout, times(1)).setRefreshing(false);
  }

  @Test
  public void updateViewStateCorrect_loading() {
    injectMockSwipeToRefresh();
    repoListView.updateViewState(RepoListView.ViewState.LOADING);
    assertThat(repoListView.tvError).isGone();
    assertThat(repoListView.tvEmpty).isGone();
    assertThat(repoListView.rvRepos).isVisible();
    verify(repoListView.refreshLayout, times(1)).setRefreshing(true);
  }

  @Test
  public void updateViewStateCorrect_empty() {
    injectMockSwipeToRefresh();
    repoListView.updateViewState(RepoListView.ViewState.EMPTY);
    assertThat(repoListView.tvError).isGone();
    assertThat(repoListView.rvRepos).isGone();
    assertThat(repoListView.tvEmpty).isVisible();
    verify(repoListView.refreshLayout, times(1)).setRefreshing(false);
  }


  private void injectMockSwipeToRefresh() {
    repoListView.refreshLayout = mockRefreshLayout;
  }

}
