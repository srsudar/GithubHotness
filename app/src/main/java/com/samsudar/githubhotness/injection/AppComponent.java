package com.samsudar.githubhotness.injection;

import com.samsudar.githubhotness.injection.net.GithubModule;
import com.samsudar.githubhotness.injection.ui.activity.RepoListActivityModule;
import com.samsudar.githubhotness.injection.ui.view.RepoListViewComponent;
import com.samsudar.githubhotness.injection.ui.view.RepoListViewModule;
import com.samsudar.githubhotness.injection.ui.activity.RepoListActivityComponent;

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
