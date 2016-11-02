package org.cse390.githubhotness;

import org.cse390.githubhotness.ui.activity.RepoListActivity;
import org.cse390.githubhotness.ui.activity.component.RepoListActivityComponent;
import org.cse390.githubhotness.ui.activity.module.RepoListActivityModule;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sudars on 11/2/16.
 */

public class TestGithubHotnessApplication extends GithubHotnessApplication {
  private AppComponent appComponent;
  private RepoListActivityComponent repoListActivityComponent;

  @Override
  public AppComponent getAppComponent() {
    if (appComponent == null) {
      appComponent = mock(AppComponent.class);
      when(appComponent.plus(any(RepoListActivityModule.class)))
          .thenReturn(repoListActivityComponent);
    }

    return appComponent;
  }

  public void setRepoListActivityComponent(RepoListActivityComponent
      component) {
    this.repoListActivityComponent = component;
  }
}
