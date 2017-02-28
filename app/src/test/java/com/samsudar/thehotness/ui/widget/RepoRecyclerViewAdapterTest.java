package com.samsudar.thehotness.ui.widget;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.samsudar.thehotness.BuildConfig;
import com.samsudar.thehotness.TestGithubHotnessApplication;
import com.samsudar.thehotness.models.Repo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowContextWrapper;
import org.robolectric.shadows.ShadowView;

import java.util.Arrays;
import java.util.List;

import static org.assertj.android.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by sudars on 11/4/16.
 */
@RunWith(RobolectricTestRunner.class)
@Config(
    constants = BuildConfig.class,
    sdk = 22,
    application = TestGithubHotnessApplication.class
)
public class RepoRecyclerViewAdapterTest {
  RepoRecyclerViewAdapter adapter;
  List<Repo> dataset;
  ViewGroup parentView;

  @Before
  public void before() {
    Repo repo1 = new Repo();
    repo1.setFullName("srsudar/mdr-original");
    repo1.setHtmlUrl("https://github.com/srsduar/mdr-original");
    repo1.setDescription("Some description");
    repo1.setNumStars(2);

    Repo repo2 = new Repo();
    repo2.setFullName("kritts/redirect");
    repo2.setHtmlUrl("https://github.com/kritts/redirect");
    repo2.setDescription("A Chrome extension");
    repo2.setNumStars(200);

    parentView = new LinearLayout(RuntimeEnvironment.application);
    dataset = Arrays.asList(repo1, repo2);
    adapter = new RepoRecyclerViewAdapter(dataset);
  }

  @Test
  public void clickingOpensUrl() {
    int repoPosition = 0;
    RepoRecyclerViewAdapter.RepoViewHolder mockViewHolder =
        mock(RepoRecyclerViewAdapter.RepoViewHolder.class);
    when(mockViewHolder.getAdapterPosition()).thenReturn(repoPosition);

    View view = new View(RuntimeEnvironment.application);
    view.setTag(mockViewHolder);

    adapter.openRepoListener.onClick(view);

    ShadowContextWrapper contextWrapper = Shadows.shadowOf(RuntimeEnvironment
        .application);
    Intent intent = contextWrapper.peekNextStartedActivity();
    assertThat(intent).hasAction(Intent.ACTION_VIEW);
    assertThat(intent).hasData(Uri.parse(dataset.get(repoPosition)
        .getHtmlUrl()));
  }

  @Test
  public void clickListenerIsSet() {
    int repoPosition = 0;
    RepoRecyclerViewAdapter.RepoViewHolder viewHolder = adapter
        .onCreateViewHolder(parentView, 0);
    adapter.onBindViewHolder(viewHolder, repoPosition);

    ShadowView shadowView = Shadows.shadowOf(viewHolder.wholeView);
    org.assertj.core.api.Assertions.assertThat(
        shadowView.getOnClickListener())
        .isEqualTo(adapter
        .openRepoListener);
  }
}
