package org.cse390.githubhotness;

import android.app.Application;
import android.content.Context;

import com.frogermcs.androiddevmetrics.AndroidDevMetrics;

import timber.log.Timber;

/**
 * Application class, which is commonly used to store global state in
 * Android. Dagger appears to use it to kick off initialization of the
 * dependency graph.
 * <p>
 * Based in part on Miroslaw Stanek's Dagger series:
 * http://frogermcs.github.io/
 */
public class GithubHotnessApplication extends Application {
  private AppComponent appComponent;

  public static GithubHotnessApplication get(Context context) {
    // Seems to be just a convenience method to static cast an application
    // contxt to a GithubHotnessApplication?
    return (GithubHotnessApplication) context.getApplicationContext();
  }

  @Override
  public void onCreate() {
    super.onCreate();
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
      AndroidDevMetrics.initWith(this);
    }
    initAppComponent();
  }

  private void initAppComponent() {
    this.appComponent = DaggerAppComponent.builder()
        .appModule(new AppModule(this))
        .build();
  }

  public AppComponent getAppComponent() {
    return appComponent;
  }
}
