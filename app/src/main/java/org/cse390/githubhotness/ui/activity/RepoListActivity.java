package org.cse390.githubhotness.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import org.cse390.githubhotness.BuildConfig;
import org.cse390.githubhotness.GithubHotnessApplication;
import org.cse390.githubhotness.R;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityModule;
import org.cse390.githubhotness.ui.activity.presenter.RepoListActivityPresenter;
import org.cse390.githubhotness.ui.view.presenter.RepoListViewPresenter;
import org.cse390.githubhotness.ui.view.RepoListView;
import org.cse390.githubhotness.ui.widget.RepoListPagerAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Created by sudars on 10/30/16.
 */
public class RepoListActivity extends BaseActivity implements
    RepoListActivityPresenter.PresenterCallbacks {

  @BindView(R.id.repos_view_pager) ViewPager viewPager;
  @BindView(R.id.repos_tab_layout) TabLayout tabLayout;
  @BindView(R.id.repos_toolbar) Toolbar toolbar;

  @Inject RepoListPagerAdapter pagerAdapter;
  @Inject RepoListActivityPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);
    ButterKnife.bind(this);
    setSupportActionBar(toolbar);

    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);

    // For now, keep all the views since they're not massive and it's
    // annoying to refresh them.
    viewPager.setOffscreenPageLimit(RepoListPagerAdapter.PagerPositions
        .values().length);

  }

  @Override
  protected void setupActivityComponent() {
    GithubHotnessApplication.get(this)
        .getAppComponent()
        .plus(new RepoListActivityModule(this))
        .inject(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_repo_list, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      presenter.receiveStartSettingsEvent(this);
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override
  public void startActivityForIntent(Intent intent) {
    startActivity(intent);
  }
}
