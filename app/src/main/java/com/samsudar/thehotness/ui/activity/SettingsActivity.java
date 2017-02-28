package com.samsudar.thehotness.ui.activity;

import android.annotation.TargetApi;
import android.os.Build;
import android.preference.PreferenceFragment;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.Window;

import com.samsudar.thehotness.R;

public class SettingsActivity extends BaseActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      setupAnimation();
    }

    getFragmentManager().beginTransaction().replace(android.R.id.content,
        new PrefsFragment()).commit();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  protected void setupAnimation() {
    getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
    getWindow().setEnterTransition(new Slide(Gravity.BOTTOM));
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == android.R.id.home) {
      finish();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  protected void setupActivityComponent() {

  }

  public static class PrefsFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);

      addPreferencesFromResource(R.xml.pref_general);
    }
  }


}
