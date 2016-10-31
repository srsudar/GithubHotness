package org.cse390.githubhotness.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * A base Activity class to support Dagger injection.
 * <p>
 * Based in part on Miroslaw Stanek's intro to dagger series:
 * http://frogermcs.github.io/
 */
public abstract class BaseActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setupActivityComponent();
  }

  protected abstract void setupActivityComponent();
}
