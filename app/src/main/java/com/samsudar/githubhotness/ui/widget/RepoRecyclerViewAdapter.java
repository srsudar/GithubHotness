package com.samsudar.githubhotness.ui.widget;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.samsudar.githubhotness.models.Repo;

import java.util.List;

/**
 * Created by sudars on 10/23/16.
 */

public class RepoRecyclerViewAdapter extends RecyclerView.Adapter<RepoRecyclerViewAdapter
    .RepoViewHolder>{
  private List<Repo> dataset;
  private int lastAnimatedPosition = -1;
  View.OnClickListener openRepoListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      RecyclerView.ViewHolder viewHolder =
          (RecyclerView.ViewHolder) view.getTag();
      int position = viewHolder.getAdapterPosition();
      Repo repo = dataset.get(position);
      Uri uri = Uri.parse(repo.getHtmlUrl());
      Intent intent = new Intent(Intent.ACTION_VIEW);
      intent.setData(uri);
      view.getContext().startActivity(intent);
    }
  };

  public RepoRecyclerViewAdapter(List<Repo> dataset){
    this.dataset = dataset;
  }

  static class RepoViewHolder extends RecyclerView.ViewHolder {
    TextView name;
    TextView description;
    TextView stars;
    View wholeView ;

    RepoViewHolder(View view) {
      super(view);
      this.wholeView = view;
      this.name = (TextView) view.findViewById(com.samsudar.githubhotness.R.id.repo_name);
      this.description = (TextView) view.findViewById(com.samsudar.githubhotness.R.id.repo_description);
      this.stars = (TextView) view.findViewById(com.samsudar.githubhotness.R.id.repo_stars);
    }
  }

  public void replaceDataset(List<Repo> repos) {
    int originalSize = dataset.size();
    dataset.clear();
    notifyItemRangeRemoved(0, originalSize);
    this.dataset.addAll(repos);
    notifyItemRangeInserted(0, dataset.size());
  }

  @Override
  public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    View repoView = inflater.inflate(com.samsudar.githubhotness.R.layout.repo_view, parent, false);
    RepoViewHolder result = new RepoViewHolder(repoView);
    repoView.setTag(result);
    repoView.setOnClickListener(openRepoListener);
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
