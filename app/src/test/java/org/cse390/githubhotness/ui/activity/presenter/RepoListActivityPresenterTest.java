package org.cse390.githubhotness.ui.activity.presenter;

import org.cse390.githubhotness.BuildConfig;
import org.cse390.githubhotness.TestGithubHotnessApplication;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.net.SearchManager;
import org.cse390.githubhotness.net.SearchResponse;
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
public class RepoListActivityPresenterTest {
  RepoListActivityPresenter presenter;
  @Mock
  RepoListActivityPresenter.PresenterCallbacks mockCallbacks;
  @Mock
  SearchManager mockSearchManager;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
    presenter = new RepoListActivityPresenter(mockCallbacks,
        mockSearchManager);
  }

  private TestSubscriber<SearchResponse> setupError(Throwable throwable) {
    Observable<SearchResponse> observable = Observable.error(throwable);

    when(mockSearchManager.getSearchResponse()).thenReturn(observable);

    TestSubscriber<SearchResponse> responseTestSubscriber = new
        TestSubscriber<>();
    observable.subscribe(responseTestSubscriber);
    return responseTestSubscriber;
  }

  private TestSubscriber<SearchResponse> setupRepoResponse(
      SearchResponse response) {
    Observable<SearchResponse> observable = Observable.just(response);

    when(mockSearchManager.getSearchResponse()).thenReturn(observable);

    TestSubscriber<SearchResponse> responseTestSubscriber = new
        TestSubscriber<>();
    observable.subscribe(responseTestSubscriber);
    return responseTestSubscriber;
  }

  @Test
  public void loadSearchResults_updatesViewToLoading() {
    loadSearchResultsHelper();
    verify(mockCallbacks, times(1))
        .updateViewState(RepoListActivityPresenter.ViewState.LOADING);
  }

  @Test
  public void loadSearchResults_updatesToEmpty() {
    ArrayList<Repo> repos = new ArrayList<>();
    SearchResponse response = new SearchResponse();
    response.setRepos(repos);
    TestSubscriber<SearchResponse> subscriber = setupRepoResponse(response);

    presenter.loadSearchResults();

    subscriber.assertNoErrors();
    subscriber.assertValue(response);
    subscriber.assertCompleted();

    verify(mockCallbacks, times(1)).setRepos(repos);
    verify(mockCallbacks, times(1))
        .updateViewState(RepoListActivityPresenter.ViewState.EMPTY);
  }

  @Test
  public void loadSearchResults_updatesToError() {
    Throwable throwable = new IOException();
    TestSubscriber<SearchResponse> subscriber = setupError(throwable);

    presenter.loadSearchResults();

    subscriber.assertError(throwable);

    verify(mockCallbacks, times(1))
        .updateViewState(RepoListActivityPresenter.ViewState.ERROR);
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

    presenter.loadSearchResults();

    subscriber.assertNoErrors();
    subscriber.assertValue(response);
    subscriber.assertCompleted();

    verify(mockCallbacks, times(1)).setRepos(repos);
    verify(mockCallbacks, times(1))
        .updateViewState(RepoListActivityPresenter.ViewState.LOADED);
  }
}
