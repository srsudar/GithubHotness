package org.cse390.githubhotness.net;

import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

/**
 * Factory for service creation based on:
 *
 * http://randomdotnext.com/retrofit-rxjava/
 */
public class ServiceFactory {
  public static <T> T getService(final Class<T> clazz, final String
      endpoint) {
    final RestAdapter restAdapter = new RestAdapter.Builder()
        .setEndpoint(endpoint)
        .build();
    T result = restAdapter.create(clazz);
    return result;
  }

  public static GithubService getGithubService() {
    return getService(GithubService.class, GithubService.BASE_URL);
  }
}
