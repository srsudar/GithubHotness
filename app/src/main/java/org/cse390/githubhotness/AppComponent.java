package org.cse390.githubhotness;

import org.cse390.githubhotness.net.GithubModule;
import org.cse390.githubhotness.ui.activity.RepoListActivity;
import org.cse390.githubhotness.ui.activity.component.RepoListActivityComponent;
import org.cse390.githubhotness.ui.activity.module.RepoListActivityModule;

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
}
