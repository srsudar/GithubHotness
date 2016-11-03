package org.cse390.githubhotness.net;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sudars on 10/31/16.
 */

public class SearchManager {
  public static final int DEFAULT_PER_PAGE = 25;

  private GithubService githubService;

  public SearchManager(GithubService githubService) {
    this.githubService = githubService;
  }

  public Observable<SearchResponse> getSearchResponse(String date,
      int perPage) {
    return githubService
        .searchMostPopularRepos(date, perPage)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
