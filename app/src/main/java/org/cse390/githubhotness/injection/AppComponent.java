package org.cse390.githubhotness.injection;

import org.cse390.githubhotness.injection.AppModule;
import org.cse390.githubhotness.injection.ui.view.RepoListViewComponent;
import org.cse390.githubhotness.injection.ui.view.RepoListViewModule;
import org.cse390.githubhotness.injection.net.GithubModule;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityComponent;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityModule;

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
