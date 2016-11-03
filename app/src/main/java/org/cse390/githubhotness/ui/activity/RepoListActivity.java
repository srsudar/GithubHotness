package org.cse390.githubhotness.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import org.cse390.githubhotness.GithubHotnessApplication;
import org.cse390.githubhotness.R;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.ui.activity.module.RepoListActivityModule;
import org.cse390.githubhotness.ui.activity.presenter.RepoListActivityPresenter;
import org.cse390.githubhotness.widgets.RepoRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by sudars on 10/30/16.
 */
public class RepoListActivity extends BaseActivity implements
    RepoListActivityPresenter.PresenterCallbacks {
  @BindView(R.id.repos) RecyclerView rvRepos;
  @BindView(R.id.repos_error) TextView tvError;
  @BindView(R.id.repos_empty) TextView tvEmpty;
  @BindView(R.id.repo_fab) FloatingActionButton fab;
  @BindView(R.id.swipe_refresh_repos) SwipeRefreshLayout refreshLayout;

  @Inject RepoRecyclerViewAdapter adapter;
  @Inject LinearLayoutManager layoutManager;
  @Inject RepoListActivityPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);
    ButterKnife.bind(this);

    refreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            presenter.loadSearchResults();
          }
        });
    setupRepoListView();
    presenter.loadSearchResults();
  }

  @Override
  protected void setupActivityComponent() {
    GithubHotnessApplication.get(this)
        .getAppComponent()
        .plus(new RepoListActivityModule(this))
        .inject(this);
  }

  @SuppressWarnings("unused")
  @OnClick(R.id.repo_fab)
  void fabClicked() {
    presenter.loadSearchResults();
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
  public void updateViewState(RepoListActivityPresenter.ViewState state) {
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_repo_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
