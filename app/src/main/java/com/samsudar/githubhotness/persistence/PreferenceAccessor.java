package com.samsudar.githubhotness.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Handles accessing values saved in SharedPreferences.
 */
public class PreferenceAccessor {
  private static final class PreferenceDefaults {
    public static final String PER_PAGE = "25";
  }

  public static final class PreferenceKeys {
    public static final String PER_PAGE = "per_page";
  }

  public int getPerPagePreference(Context context) {
    SharedPreferences preferenceManager = PreferenceManager
        .getDefaultSharedPreferences(context);
    // Infuriatingly, I can't just back it with an integer array, I instead
    // have to back it with a String array and then handle parsing the
    // values myself. Typical Android being a hot mess.
    String strResult = preferenceManager.getString(PreferenceKeys.PER_PAGE,
        PreferenceDefaults.PER_PAGE);
    return Integer.parseInt(strResult);
  }
}
