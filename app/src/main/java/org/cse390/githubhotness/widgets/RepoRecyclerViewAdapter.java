package org.cse390.githubhotness.widgets;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.cse390.githubhotness.R;
import org.cse390.githubhotness.models.Repo;

import java.util.List;

/**
 * Created by sudars on 10/23/16.
 */

public class RepoRecyclerViewAdapter extends RecyclerView.Adapter<RepoRecyclerViewAdapter
    .RepoViewHolder>{
  private List<Repo> dataset;

  public RepoRecyclerViewAdapter(List<Repo> dataset){
    this.dataset = dataset;
  }

  static class RepoViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    TextView stars;

    RepoViewHolder(View view) {
      super(view);
      this.name = (TextView) view.findViewById(R.id.repo_name);
      this.description = (TextView) view.findViewById(R.id.repo_description);
      this.stars = (TextView) view.findViewById(R.id.repo_stars);
    }
  }

  @Override
  public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View repoView = inflater.inflate(R.layout.repo_view, parent, false);
    RepoViewHolder result = new RepoViewHolder(repoView);
    return result;
  }

  @Override
  public void onBindViewHolder(RepoViewHolder holder, int position) {
    Repo repo = this.dataset.get(position);
    holder.name.setText(repo.getFullName());
    holder.description.setText(repo.getDescription());
    holder.stars.setText(Integer.toString(repo.getNumStars()));
  }

  @Override
  public int getItemCount() {
    return this.dataset.size();
  }
}
