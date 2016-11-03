package org.cse390.githubhotness.net;

import com.google.gson.annotations.SerializedName;

import org.cse390.githubhotness.models.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sudars on 10/23/16.
 */

public class SearchResponse {
  @SerializedName("items")
  private ArrayList<Repo> repos;

  public List<Repo> getRepos() {
    return repos;
  }

  public void setRepos(ArrayList<Repo> repos) {
    this.repos = repos;
  }

  @Override
  public String toString() {
    return "SearchResponse{" +
        "repos=" + repos +
        '}';
  }
}
