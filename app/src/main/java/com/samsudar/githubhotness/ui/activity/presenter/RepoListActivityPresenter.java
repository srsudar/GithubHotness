package com.samsudar.githubhotness.ui.activity.presenter;

import android.content.Context;
import android.content.Intent;

import com.samsudar.githubhotness.ui.activity.SettingsActivity;

/**
 * Created by sudars on 11/3/16.
 */

public class RepoListActivityPresenter {
  public interface PresenterCallbacks {
    void startActivityForIntent(Intent intent);
  }

  PresenterCallbacks callbacks;

  public RepoListActivityPresenter(PresenterCallbacks callbacks) {
    this.callbacks = callbacks;
  }

  public void receiveStartSettingsEvent(Context context) {
    Intent intent = new Intent(context, SettingsActivity.class);
    callbacks.startActivityForIntent(intent);
  }
}
