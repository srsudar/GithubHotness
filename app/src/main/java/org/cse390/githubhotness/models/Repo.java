package org.cse390.githubhotness.models;

import com.squareup.moshi.Json;

/**
 * Simple class representing a repository.
 */
public class Repo {
  @Json(name = "full_name")
  private String fullName;

  @Json(name = "description")
  private String description;

  @Json(name = "stargazers_count")
  private int numStars;

  @Json(name = "html_url")
  private String htmlUrl;

  public Repo() {
    this.fullName = null;
    this.description = null;
    this.numStars = -1;
    this.htmlUrl = null;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getNumStars() {
    return numStars;
  }

  public void setNumStars(int numStars) {
    this.numStars = numStars;
  }

  public String getHtmlUrl() {
    return htmlUrl;
  }

  public void setHtmlUrl(String htmlUrl) {
    this.htmlUrl = htmlUrl;
  }

  @Override
  public String toString() {
    return "Repo{" +
        "fullName='" + fullName + '\'' +
        ", description='" + description + '\'' +
        ", numStars=" + numStars +
        ", htmlUrl='" + htmlUrl + '\'' +
        '}';
  }
}
