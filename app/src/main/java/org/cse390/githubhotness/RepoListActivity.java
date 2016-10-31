package org.cse390.githubhotness;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.cse390.githubhotness.net.GithubService;
import org.cse390.githubhotness.net.SearchResponse;
import org.cse390.githubhotness.widgets.RepoRecyclerViewAdapter;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RepoListActivity extends AppCompatActivity {
  private static final String TAG = RepoListActivity.class.getSimpleName();

  private RecyclerView repoView;
  private RepoRecyclerViewAdapter adapter;
  private View loadingView;
  private TextView errorView;
  private TextView emptyView;

  private Observable<SearchResponse> mObservable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_repo_list);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
            .setAction("Action", null).show();
      }
    });

    repoView = (RecyclerView) findViewById(R.id.repos);
    repoView.setLayoutManager(new LinearLayoutManager(this));

    loadingView = findViewById(R.id.repos_loading);
    errorView = (TextView) findViewById(R.id.repos_error);
    emptyView = (TextView) findViewById(R.id.repos_empty);

    loadingView.setVisibility(View.VISIBLE);
    repoView.setVisibility(View.GONE);
    errorView.setVisibility(View.GONE);
    emptyView.setVisibility(View.GONE);

//    mObservable = service.getRepos();
//    mObservable.subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .subscribe(new Subscriber<SearchResponse>() {
//          @Override
//          public void onCompleted() {
//            Log.e(TAG, "onCompleted called");
//          }
//
//          @Override
//          public void onError(Throwable e) {
//            Log.e(TAG, "onError", e);
//            loadingView.setVisibility(View.GONE);
//            repoView.setVisibility(View.GONE);
//            errorView.setVisibility(View.VISIBLE);
//          }
//
//          @Override
//          public void onNext(SearchResponse searchResponse) {
//            Log.e(TAG, "onNextCalled");
//            loadingView.setVisibility(View.GONE);
//            repoView.setVisibility(View.VISIBLE);
//            adapter = new RepoRecyclerViewAdapter(searchResponse.getRepos());
//            repoView.setAdapter(adapter);
//          }
//        });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
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
