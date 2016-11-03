package org.cse390.githubhotness.net;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by sudars on 10/23/16.
 */
public interface GithubService {
  String SEARCH_OCT1 = "/search/repositories?sort=stars&order=desc&q=created" +
      ":>={date}&per_page={per_page}";

  @GET(SEARCH_OCT1)
  Observable<SearchResponse> searchMostPopularRepos(@Path("date") String date,
      @Path("per_page") int perPage);
}
