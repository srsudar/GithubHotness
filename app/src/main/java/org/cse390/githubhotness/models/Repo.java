package org.cse390.githubhotness.models;

import com.google.auto.value.AutoValue;

/**
 * Simple class representing a repository.
 */
@AutoValue
public abstract class Repo {
  public abstract int id();
  public abstract String name();
  public abstract String fullName();
  public abstract String description();
  public abstract int numStars();
  public abstract String language();
  public abstract String htmlUrl();

  static Repo create(int id, String name, String fullName, String description,
                     int numStars, String language, String htmlUrl) {
    // See "How do I...?" below for nested classes.
    return new AutoValue_Repo(id, name, fullName, description, numStars,
        language, htmlUrl);
  }

}
