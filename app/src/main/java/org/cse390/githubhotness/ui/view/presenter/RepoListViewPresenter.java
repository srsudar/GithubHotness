package org.cse390.githubhotness.ui.view.presenter;

import android.support.annotation.NonNull;

import org.cse390.githubhotness.logic.TimeStamper;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.net.SearchManager;
import org.cse390.githubhotness.net.SearchResponse;
import org.cse390.githubhotness.ui.view.RepoListView;
import org.joda.time.DateTime;

import java.util.List;

import rx.Observer;
import timber.log.Timber;

/**
 * Created by sudars on 10/31/16.
 */

public class RepoListViewPresenter {
  public enum SearchPeriod {
    MONTH, WEEK, DAY
  }

  public interface PresenterCallbacks {
    void updateViewState(RepoListView.ViewState viewState);
    void setRepos(List<Repo> repos);
  }

  private PresenterCallbacks callbacks;
  private SearchManager searchManager;
  private TimeStamper timeStamper;

  public RepoListViewPresenter(PresenterCallbacks callbacks,
      SearchManager searchManager, TimeStamper timeStamper) {
    this.callbacks = callbacks;
    this.searchManager = searchManager;
    this.timeStamper = timeStamper;
  }

  String getStringForPeriod(@NonNull SearchPeriod period) {
    DateTime now = new DateTime();
    String result;
    switch (period) {
      case MONTH:
        result = timeStamper.getOneMonthAgo(now);
        break;
      case WEEK:
        result = timeStamper.getOneWeekAgo(now);
        break;
      case DAY:
        result = timeStamper.get24HoursAgo(now);
        break;
      default:
        throw new IllegalArgumentException("unrecognized period: " + period);
    }
    return result;
  }

  public void loadSearchResults(final SearchPeriod searchPeriod) {
    callbacks.updateViewState(RepoListView.ViewState.LOADING);
    String dateString = getStringForPeriod(searchPeriod);
    searchManager.getSearchResponse(dateString,
        SearchManager.DEFAULT_PER_PAGE)
        .subscribe(new Observer<SearchResponse>() {
      @Override
      public void onCompleted() {
        // No op?
      }

      @Override
      public void onError(Throwable e) {
        Timber.e(e);
        callbacks.updateViewState(RepoListView.ViewState.ERROR);
      }

      @Override
      public void onNext(SearchResponse searchResponse) {
        callbacks.setRepos(searchResponse.getRepos());
        if (searchResponse.getRepos().isEmpty()) {
          callbacks.updateViewState(RepoListView.ViewState.EMPTY);
        } else {
          callbacks.updateViewState(RepoListView.ViewState.LOADED);
        }
      }
    });
  }
}
