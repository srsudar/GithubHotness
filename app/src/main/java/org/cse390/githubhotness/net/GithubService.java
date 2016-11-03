package org.cse390.githubhotness.net;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sudars on 10/23/16.
 */
public interface GithubService {
//  String SEARCH_OCT1 = "/search/repositories?sort=stars&order=desc&q=created" +
//      ":>={date}&per_page={per_page}";

  String SEARCH = "/search/repositories";

  @GET(SEARCH)
  Observable<SearchResponse> searchMostPopularRepos(
      @Query("sort") String sort,
      @Query("order") String order,
      @Query("q") String query,
      @Query("per_page") int perPage);

  class SortArgs {
    public static final String STARS = "stars";
  }

  class OrderArgs {
    public static final String DESCENDING = "desc";
  }
}
