package org.cse390.githubhotness.ui.activity.module;

import android.support.v7.widget.LinearLayoutManager;

import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.net.GithubService;
import org.cse390.githubhotness.net.SearchManager;
import org.cse390.githubhotness.ui.activity.ActivityScope;
import org.cse390.githubhotness.ui.activity.RepoListActivity;
import org.cse390.githubhotness.ui.activity.presenter.RepoListActivityPresenter;
import org.cse390.githubhotness.widgets.RepoRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Module;
import dagger.Provides;

/**
 * Provides dependencies for
 * {@link RepoListActivity}.
 * <p>Based in part on
 * Miroslaw Stanek's Dagger intro series: http://frogermcs.github.io/.
 */
@Module
public class RepoListActivityModule {
  private RepoListActivity repoListActivity;

  public RepoListActivityModule(RepoListActivity repoListActivity) {
    this.repoListActivity = repoListActivity;
  }

  @Provides
  @ActivityScope
  RepoListActivity provideRepoListActivity() {
    return repoListActivity;
  }

  @Provides
  @ActivityScope
  RepoRecyclerViewAdapter provideRepoReyclerViewAdapter(List<Repo> repos) {
    return new RepoRecyclerViewAdapter(repos);
  }

  @Provides
  @ActivityScope
  List<Repo> provideRepos() {
    return new ArrayList<>();
  }

  @Provides
  @ActivityScope
  LinearLayoutManager provideLinearLayoutManager(
      RepoListActivity repoListActivity) {
    return new LinearLayoutManager(repoListActivity);
  }

  @Provides
  @ActivityScope
  RepoListActivityPresenter.PresenterCallbacks providePresenterCallbacks() {
    return repoListActivity;
  }

  @Provides
  @ActivityScope
  RepoListActivityPresenter provideRepoListActivityPresenter(
      RepoListActivityPresenter.PresenterCallbacks callbacks,
      SearchManager searchManager) {
    return new RepoListActivityPresenter(callbacks, searchManager);
  }
}
