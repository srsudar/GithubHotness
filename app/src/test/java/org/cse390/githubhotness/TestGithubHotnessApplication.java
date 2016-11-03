package org.cse390.githubhotness;

import org.cse390.githubhotness.injection.AppComponent;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityComponent;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityModule;
import org.cse390.githubhotness.injection.ui.view.RepoListViewComponent;
import org.cse390.githubhotness.injection.ui.view.RepoListViewModule;
import org.cse390.githubhotness.ui.view.RepoListView;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sudars on 11/2/16.
 */

public class TestGithubHotnessApplication extends GithubHotnessApplication {
  private AppComponent appComponent;
  private RepoListActivityComponent repoListActivityComponent;
  private RepoListViewComponent setRepoListViewComponent;

  @Override
  public AppComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = mock(AppComponent.class);
      when(appComponent.plus(any(RepoListActivityModule.class)))
          .thenReturn(repoListActivityComponent);
      when(appComponent.plus(any(RepoListViewModule.class)))
          .thenReturn(setRepoListViewComponent);
    }

    return appComponent;
  }

  public void setRepoListActivityComponent(RepoListActivityComponent
      component) {
    this.repoListActivityComponent = component;
  }

  public void setSetRepoListViewComponent(RepoListViewComponent component) {
    this.setRepoListViewComponent = component;
  }
}
