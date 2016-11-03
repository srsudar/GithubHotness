package org.cse390.githubhotness.ui.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.cse390.githubhotness.GithubHotnessApplication;
import org.cse390.githubhotness.R;
import org.cse390.githubhotness.injection.ui.view.RepoListViewModule;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.ui.view.presenter.RepoListViewPresenter;
import org.cse390.githubhotness.widgets.RepoRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by sudars on 11/3/16.
 */

public class RepoListView extends LinearLayout implements
    RepoListViewPresenter.PresenterCallbacks {
  public enum ViewState {
    LOADING, LOADED, EMPTY, ERROR
  }

  @BindView(R.id.repos) RecyclerView rvRepos;
  @BindView(R.id.repos_error) TextView tvError;
  @BindView(R.id.repos_empty) TextView tvEmpty;
  @BindView(R.id.swipe_refresh_repos) SwipeRefreshLayout refreshLayout;

  @Inject RepoRecyclerViewAdapter adapter;
  @Inject RecyclerView.LayoutManager layoutManager;
  @Inject RepoListViewPresenter presenter;
  @Inject RepoListViewPresenter.SearchPeriod searchPeriod;

  public RepoListView(Context context, AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public RepoListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  public void setSearchPeriod(RepoListViewPresenter.SearchPeriod
      searchPeriod) {
    this.searchPeriod = searchPeriod;
  }

  private void init(Context context) {
    LayoutInflater.from(context).inflate(R.layout.view_repo_list, this,
        true);
    ButterKnife.bind(this);
    setupViewComponent(context);

    refreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            loadSearchResults();
          }
        });
    setupRepoListView();
  }

  protected void setupViewComponent(Context context) {
    GithubHotnessApplication.get(context)
        .getAppComponent()
        .plus(new RepoListViewModule(this))
        .inject(this);
  }

  public void loadSearchResults() {
    presenter.loadSearchResults(searchPeriod);
  }

  @Override
  public void setRepos(List<Repo> repos) {
    adapter.replaceDataset(repos);
  }

  private void setupRepoListView() {
    rvRepos.setAdapter(adapter);
    rvRepos.setLayoutManager(layoutManager);
  }

  @Override
  public void updateViewState(ViewState state) {
    switch (state) {
      case LOADING:
        tvError.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        rvRepos.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(true);
        break;
      case EMPTY:
        tvError.setVisibility(View.GONE);
        rvRepos.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(false);
        break;
      case LOADED:
        tvError.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        rvRepos.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(false);
        break;
      case ERROR:
        rvRepos.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.GONE);
        tvError.setVisibility(View.VISIBLE);
        refreshLayout.setRefreshing(false);
        break;
      default:
        Timber.e("Unrecognized view state: %i", state);
    }
  }
}
