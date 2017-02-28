package com.samsudar.thehotness.injection;

import com.samsudar.thehotness.injection.ui.view.RepoListViewComponent;
import com.samsudar.thehotness.injection.ui.view.RepoListViewModule;
import com.samsudar.thehotness.injection.net.GithubModule;
import com.samsudar.thehotness.injection.ui.activity.RepoListActivityComponent;
import com.samsudar.thehotness.injection.ui.activity.RepoListActivityModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
    modules = {
        AppModule.class,
        GithubModule.class
    }
)
public interface AppComponent {
  RepoListActivityComponent plus(
      RepoListActivityModule repoListActivityModule);

  RepoListViewComponent plus(RepoListViewModule repoListViewModule);
}
