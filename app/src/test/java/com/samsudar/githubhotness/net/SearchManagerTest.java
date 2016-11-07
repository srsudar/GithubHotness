package com.samsudar.githubhotness.net;

import com.samsudar.githubhotness.BuildConfig;
import com.samsudar.githubhotness.TestGithubHotnessApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import rx.Observable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
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
public class SearchManagerTest {
  SearchManager searchManager;

  @Mock
  GithubService mockGithubService;
  @Mock
  SearchResponse mockReponse;

  @Before
  public void before() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void getSearchResponse_returnsRepos() {
    // This is kind of ugly. subscribeOn and observeOn are final and I don't
    // believe they can be mocked using Mockito. Thus this is kind of hacky
    // for now.
    MockitoAnnotations.initMocks(this);
    Observable<SearchResponse> response = Observable.just(mockReponse);
    when(mockGithubService.searchMostPopularRepos(
        any(String.class),
        any(String.class),
        any(String.class),
        any(Integer.class)
    )).thenReturn(response);

    searchManager = new SearchManager(mockGithubService);

    Observable<SearchResponse> actual = searchManager.getSearchResponse("sort",
        25);
    // What I'd actually like to do here is assert that it is the result of
    // observeOn, but I'm not going to get that without a deeper
    // understanding of RxJava and its testing mechanisms, which is for now
    // premature given the inchoate version of this service.
    assertThat(actual).isNotNull();
  }
}
