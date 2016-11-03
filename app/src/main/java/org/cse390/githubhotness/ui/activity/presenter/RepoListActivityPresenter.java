package org.cse390.githubhotness.ui.activity.presenter;

import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.net.SearchManager;
import org.cse390.githubhotness.net.SearchResponse;

import java.util.List;

import rx.Observer;
import timber.log.Timber;

/**
 * Created by sudars on 10/31/16.
 */

public class RepoListActivityPresenter {
  public enum ViewState {
    LOADING, LOADED, EMPTY, ERROR
  }

  public interface PresenterCallbacks {
    void updateViewState(ViewState viewState);
    void setRepos(List<Repo> repos);
  }

  private PresenterCallbacks callbacks;
  private SearchManager searchManager;

  public RepoListActivityPresenter(PresenterCallbacks callbacks,
      SearchManager searchManager) {
    this.callbacks = callbacks;
    this.searchManager = searchManager;
  }

  public void loadSearchResults() {
    callbacks.updateViewState(ViewState.LOADING);
    searchManager.getSearchResponse().subscribe(new Observer<SearchResponse>() {
      @Override
      public void onCompleted() {
        // No op?
      }

      @Override
      public void onError(Throwable e) {
        Timber.e(e);
        callbacks.updateViewState(ViewState.ERROR);
      }

      @Override
      public void onNext(SearchResponse searchResponse) {
        callbacks.setRepos(searchResponse.getRepos());
        if (searchResponse.getRepos().isEmpty()) {
          callbacks.updateViewState(ViewState.EMPTY);
        } else {
          callbacks.updateViewState(ViewState.LOADED);
        }
      }
    });
  }
}
