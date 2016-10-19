package org.cse390.githubhotness.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cse390.githubhotness.models.Repo;

import java.util.List;

/**
 * Created by sudars on 10/7/16.
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder> {
  private List<Repo> repos;

  public RepoAdapter(List<Repo> repos) {
    this.repos = repos;
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {

  }

  @Override
  public int getItemCount() {
    return 0;
  }


  public static class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public TextView mTextView;
    public ViewHolder(TextView v) {
      super(v);
      mTextView = v;
    }
  }
}
