package com.samsudar.thehotness.injection.ui.view;

import com.samsudar.thehotness.ui.view.RepoListView;

import dagger.Subcomponent;

/**
 * Created by sudars on 11/3/16.
 */
@ViewScope
@Subcomponent(
    modules = RepoListViewModule.class
)
public interface RepoListViewComponent {
  RepoListView inject(RepoListView repoListView);
}
