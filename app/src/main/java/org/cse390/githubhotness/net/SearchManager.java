package org.cse390.githubhotness.net;

import org.cse390.githubhotness.persistence.PreferenceAccessor;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sudars on 10/31/16.
 */

public class SearchManager {
  private GithubService githubService;

  public SearchManager(GithubService githubService) {
    this.githubService = githubService;
  }

  public Observable<SearchResponse> getSearchResponse(String date,
      int perPage) {
    String queryString = new QueryBuilder().dateCreated(date).build();
    return githubService
        .searchMostPopularRepos(GithubService.SortArgs.STARS, GithubService
            .OrderArgs.DESCENDING, queryString, perPage)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
