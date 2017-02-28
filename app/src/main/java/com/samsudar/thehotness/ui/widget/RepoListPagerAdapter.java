package com.samsudar.thehotness.ui.widget;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.samsudar.thehotness.R;
import com.samsudar.thehotness.ui.view.presenter.RepoListViewPresenter;
import com.samsudar.thehotness.ui.view.RepoListView;

/**
 * Created by sudars on 11/3/16.
 */

public class RepoListPagerAdapter extends PagerAdapter {
  public enum PagerPositions {
    DAY(R.string.day, RepoListViewPresenter.SearchPeriod.DAY, R.id
        .repos_day),
    WEEK(R.string.week, RepoListViewPresenter.SearchPeriod.WEEK, R.id
        .repos_week),
    MONTH(R.string.month, RepoListViewPresenter.SearchPeriod.MONTH, R.id
        .repos_month);

    private int titleResId;
    private int viewId;
    private RepoListViewPresenter.SearchPeriod searchPeriod;

    PagerPositions(int titleResId, RepoListViewPresenter.SearchPeriod
        searchPeriod, int viewId) {
      this.titleResId = titleResId;
      this.searchPeriod = searchPeriod;
      this.viewId = viewId;
    }

    public int getTitleResId() { return titleResId; }
    public int getViewId() { return viewId; }

    public RepoListViewPresenter.SearchPeriod getSearchPeriod() {
      return searchPeriod;
    }
  }

  // dangerous to hold this?
  private Context context;

  public RepoListPagerAdapter(Context context) {
    this.context = context;
  }

  @Override
  public int getCount() {
    return PagerPositions.values().length;
  }

  @Override
  public boolean isViewFromObject(View view, Object object) {
    return view == object;
  }

  @Override
  public Object instantiateItem(ViewGroup container, int position) {
    PagerPositions pagerPositions = getPagerPosition(position);
    LayoutInflater inflater = LayoutInflater.from(context);
    RepoListView repoListView = (RepoListView) inflater.inflate(R.layout
        .inflatable_repo_list_view, container, false);
    container.addView(repoListView);
    repoListView.setId(pagerPositions.getViewId());
    repoListView.setSearchPeriod(pagerPositions.getSearchPeriod());
    repoListView.loadSearchResults();
    return repoListView;
  }

  @Override
  public void destroyItem(ViewGroup container, int position, Object object) {
    // Safe because we're returning a view from instantiateItem.
    View view = (View) object;
    container.removeView(view);
  }

  @Override
  public CharSequence getPageTitle(int position) {
    PagerPositions pagerPosition = getPagerPosition(position);
    return context.getString(pagerPosition.getTitleResId());
  }

  private PagerPositions getPagerPosition(int position) {
    return PagerPositions.values()[position];
  }
}
