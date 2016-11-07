package com.samsudar.githubhotness.injection.ui.activity;

import com.samsudar.githubhotness.ui.activity.RepoListActivity;

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
