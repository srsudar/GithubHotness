package com.samsudar.githubhotness.ui.view.presenter;

import com.samsudar.githubhotness.TestGithubHotnessApplication;
import com.samsudar.githubhotness.logic.TimeStamper;
import com.samsudar.githubhotness.ui.view.RepoListView;
import com.samsudar.githubhotness.BuildConfig;
import com.samsudar.githubhotness.models.Repo;
import com.samsudar.githubhotness.net.SearchManager;
import com.samsudar.githubhotness.net.SearchResponse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import rx.Observable;
import rx.observers.TestSubscriber;

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
public class RepoListViewPresenterTest {
  private static final int PER_PAGE = 25;

  RepoListViewPresenter presenter;
  @Mock
  RepoListViewPresenter.PresenterCallbacks mockCallbacks;
  @Mock
  SearchManager mockSearchManager;
  @Mock
  TimeStamper mockTimeStamper;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    presenter = new RepoListViewPresenter(mockCallbacks,
        mockSearchManager, mockTimeStamper);
  }

  private TestSubscriber<SearchResponse> setupError(Throwable throwable) {
    Observable<SearchResponse> observable = Observable.error(throwable);

    when(mockSearchManager.getSearchResponse(any(String.class), any(Integer
        .class))).thenReturn(observable);

    TestSubscriber<SearchResponse> responseTestSubscriber = new
        TestSubscriber<>();
    observable.subscribe(responseTestSubscriber);
    return responseTestSubscriber;
  }

  private TestSubscriber<SearchResponse> setupRepoResponse(
      SearchResponse response) {
    Observable<SearchResponse> observable = Observable.just(response);

    when(mockSearchManager.getSearchResponse(any(String.class), any(Integer
        .class))).thenReturn(observable);

    TestSubscriber<SearchResponse> responseTestSubscriber = new
        TestSubscriber<>();
    observable.subscribe(responseTestSubscriber);
    return responseTestSubscriber;
  }

  @Test
  public void loadSearchResults_updatesViewToLoading() {
    loadSearchResultsHelper();
    verify(mockCallbacks, times(1))
        .updateViewState(RepoListView.ViewState.LOADING);
  }

  @Test
  public void loadSearchResults_updatesToEmpty() {
    ArrayList<Repo> repos = new ArrayList<>();
    SearchResponse response = new SearchResponse();
    response.setRepos(repos);
    TestSubscriber<SearchResponse> subscriber = setupRepoResponse(response);

    presenter.loadSearchResults(RepoListViewPresenter.SearchPeriod.MONTH,
        PER_PAGE);

    subscriber.assertNoErrors();
    subscriber.assertValue(response);
    subscriber.assertCompleted();

    verify(mockCallbacks, times(1)).setRepos(repos);
    verify(mockCallbacks, times(1))
        .updateViewState(RepoListView.ViewState.EMPTY);
  }

  @Test
  public void loadSearchResults_updatesToError() {
    Throwable throwable = new IOException();
    TestSubscriber<SearchResponse> subscriber = setupError(throwable);

    presenter.loadSearchResults(RepoListViewPresenter.SearchPeriod.MONTH,
        PER_PAGE);

    subscriber.assertError(throwable);

    verify(mockCallbacks, times(1))
        .updateViewState(RepoListView.ViewState.ERROR);
  }

  @Test
  public void loadSearchResults_updatesToLoaded() {
    loadSearchResultsHelper();
  }

  /**
   * Helper to assert that a single repo is loaded.
   */
  private void loadSearchResultsHelper() {
    ArrayList<Repo> repos = new ArrayList<>(Arrays.asList(new Repo()));
    SearchResponse response = new SearchResponse();
    response.setRepos(repos);
    TestSubscriber<SearchResponse> subscriber = setupRepoResponse(response);

    presenter.loadSearchResults(RepoListViewPresenter.SearchPeriod.MONTH,
        PER_PAGE);

    subscriber.assertNoErrors();
    subscriber.assertValue(response);
    subscriber.assertCompleted();

    verify(mockCallbacks, times(1)).setRepos(repos);
    verify(mockCallbacks, times(1))
        .updateViewState(RepoListView.ViewState.LOADED);
  }
}
