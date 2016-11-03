package org.cse390.githubhotness.injection.ui.view;

import org.cse390.githubhotness.ui.view.RepoListView;

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
