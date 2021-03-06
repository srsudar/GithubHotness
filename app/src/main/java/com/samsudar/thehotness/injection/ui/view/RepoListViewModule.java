package com.samsudar.thehotness.injection.ui.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.samsudar.thehotness.logic.TimeStamper;
import com.samsudar.thehotness.models.Repo;
import com.samsudar.thehotness.net.SearchManager;
import com.samsudar.thehotness.persistence.PreferenceAccessor;
import com.samsudar.thehotness.ui.view.RepoListView;
import com.samsudar.thehotness.ui.view.presenter.RepoListViewPresenter;
import com.samsudar.thehotness.ui.widget.RepoRecyclerViewAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class RepoListViewModule {
  private RepoListView repoListView;

  public RepoListViewModule(RepoListView repoListView) {
    this.repoListView = repoListView;
  }

  @Provides
  @ViewScope
  RepoListView provideRepoListView() {
    return repoListView;
  }

  @Provides
  @ViewScope
  Context provideContext() {
    return repoListView.getContext();
  }

  @Provides
  @ViewScope
  RepoRecyclerViewAdapter provideRepoRecylerViewAdapter(ArrayList<Repo>
      repos) {
    return new RepoRecyclerViewAdapter(repos);
  }

  @Provides
  @ViewScope
  ArrayList<Repo> provideRepos() {
    return new ArrayList<>();
  }

  @Provides
  @ViewScope
  RecyclerView.LayoutManager provideLayoutManager(Context context) {
    return new LinearLayoutManager(context);
  }

  @Provides
  @ViewScope
  RepoListViewPresenter provideRepoListViewPresenter(SearchManager
      searchManager, TimeStamper timeStamper) {
    return new RepoListViewPresenter(repoListView, searchManager, timeStamper);
  }

  @Provides
  @ViewScope
  TimeStamper provideTimeStamper() {
    return new TimeStamper();
  }

  @Provides
  @ViewScope
  RepoListViewPresenter.SearchPeriod provideSearchPeriod() {
    // Month by default?
    return RepoListViewPresenter.SearchPeriod.MONTH;
  }

  @Provides
  @ViewScope
  PreferenceAccessor providePreferenceAccessor() {
    return new PreferenceAccessor();
  }
}
