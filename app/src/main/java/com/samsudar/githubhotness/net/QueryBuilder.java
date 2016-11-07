package com.samsudar.githubhotness.net;

/**
 * Builds query arguments for GithubSearches.
 */
public class QueryBuilder {
  private static final String BASE_QUERY_DATE = "created:>=";
  private String creationDate;

  public QueryBuilder dateCreated(String creationDate) {
    this.creationDate = creationDate;
    return this;
  }

  public String build() {
    return BASE_QUERY_DATE + creationDate;
  }
}
