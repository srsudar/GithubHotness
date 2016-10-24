package org.cse390.githubhotness.net;

import org.cse390.githubhotness.models.Repo;

import java.util.List;

import retrofit.http.GET;
import rx.Observable;

import static org.cse390.githubhotness.net.GithubService.SEARCH_OCT1;

/**
 * Created by sudars on 10/23/16.
 */

public interface GithubService {
  String BASE_URL = "https://api.github.com";
  String SEARCH_OCT1 = "/search/repositories?sort=stars&order=desc&q=created" +
      ":>2016-10-01&per_page=25";

  @GET(SEARCH_OCT1)
  Observable<SearchResponse> getRepos();
}
