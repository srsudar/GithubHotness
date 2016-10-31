package org.cse390.githubhotness.ui.activity.component;

import org.cse390.githubhotness.ui.activity.ActivityScope;
import org.cse390.githubhotness.ui.activity.RepoListActivity;
import org.cse390.githubhotness.ui.activity.module.RepoListActivityModule;

import dagger.Component;
import dagger.Subcomponent;

/**
 * Created by sudars on 10/31/16.
 */
@ActivityScope
@Subcomponent(
    modules = RepoListActivityModule.class
)
public interface RepoListActivityComponent {
  RepoListActivity inject(RepoListActivity repoListActivity);
}
