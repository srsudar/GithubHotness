package org.cse390.githubhotness.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import org.cse390.githubhotness.BuildConfig;
import org.cse390.githubhotness.GithubHotnessApplication;
import org.cse390.githubhotness.R;
import org.cse390.githubhotness.models.Repo;
import org.cse390.githubhotness.injection.ui.activity.RepoListActivityModule;
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
public class RepoListActivity extends BaseActivity {

  @BindView(R.id.repos_view_pager) ViewPager viewPager;
  @BindView(R.id.repos_tab_layout) TabLayout tabLayout;

  @Inject RepoListPagerAdapter pagerAdapter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);
    ButterKnife.bind(this);

    viewPager.setAdapter(pagerAdapter);
    tabLayout.setupWithViewPager(viewPager);
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
      return true;
    }

    return super.onOptionsItemSelected(item);
  }
}
