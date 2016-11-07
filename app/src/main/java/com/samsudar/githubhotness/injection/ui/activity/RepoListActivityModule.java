package com.samsudar.githubhotness.injection.ui.activity;

import com.samsudar.githubhotness.ui.activity.RepoListActivity;
import com.samsudar.githubhotness.ui.widget.RepoListPagerAdapter;
import com.samsudar.githubhotness.ui.activity.presenter.RepoListActivityPresenter;

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
  RepoListPagerAdapter provideRepoListPagerAdapter() {
    return new RepoListPagerAdapter(repoListActivity);
  }

  @Provides
  @ActivityScope
  RepoListActivityPresenter provideRepoListActivityPresenter() {
    return new RepoListActivityPresenter(repoListActivity);
  }
}
