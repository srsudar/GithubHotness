package com.samsudar.githubhotness.ui.activity;

import com.google.firebase.analytics.FirebaseAnalytics;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * A base Activity class to support Dagger injection.
 * <p>
 * Based in part on Miroslaw Stanek's intro to dagger series:
 * http://frogermcs.github.io/
 */
public abstract class BaseActivity extends AppCompatActivity {
  protected FirebaseAnalytics mAnalytics;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mAnalytics = FirebaseAnalytics.getInstance(this);
    setupActivityComponent();
  }

  protected abstract void setupActivityComponent();
}
