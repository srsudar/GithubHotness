package org.cse390.githubhotness.injection.ui.activity;

import org.cse390.githubhotness.injection.ui.activity.ActivityScope;
import org.cse390.githubhotness.ui.activity.RepoListActivity;
import org.cse390.githubhotness.ui.widget.RepoListPagerAdapter;

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
}
