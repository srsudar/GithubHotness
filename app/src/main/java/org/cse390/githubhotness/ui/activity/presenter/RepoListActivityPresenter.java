package org.cse390.githubhotness.ui.activity.presenter;

import org.cse390.githubhotness.net.SearchManager;
import org.cse390.githubhotness.net.SearchResponse;
import org.cse390.githubhotness.ui.activity.RepoListActivity;

import rx.Observer;
import timber.log.Timber;

/**
 * Created by sudars on 10/31/16.
 */

public class RepoListActivityPresenter {
  private RepoListActivity repoListActivity;
  private SearchManager searchManager;

  public RepoListActivityPresenter(RepoListActivity repoListActivity,
      SearchManager searchManager) {
    this.repoListActivity = repoListActivity;
    this.searchManager = searchManager;
  }

  public void loadSearchResults() {
    repoListActivity.updateViewState(
        RepoListActivity.RecyclerViewState.LOADING);
    searchManager.getSearchResponse().subscribe(new Observer<SearchResponse>() {
      @Override
      public void onCompleted() {
        // No op?
      }

      @Override
      public void onError(Throwable e) {
        Timber.e(e);
        repoListActivity.updateViewState(
            RepoListActivity.RecyclerViewState.ERROR);
      }

      @Override
      public void onNext(SearchResponse searchResponse) {
        repoListActivity.setRepos(searchResponse.getRepos());
        repoListActivity.updateViewState(
            RepoListActivity.RecyclerViewState.LOADED);
      }
    });
  }
}
