package de.comdirect.collabothon2016.collabothon2016.viewadapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.model.Leader;
import de.comdirect.collabothon2016.collabothon2016.util.ImageUtils;

public class LeaderboardItemAdapter extends RecyclerView.Adapter<LeaderboardItemAdapter.ViewHolder> {

    public interface OnLeaderboardItemSelected {
        void leaderItemSelected(Leader leader);
    }

    private OnLeaderboardItemSelected mListener;

    private List<Leader> mLeaders;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.user_icon)
        public ImageView userIcon;
        @BindView(R.id.user_name)
        public TextView userName;
        @BindView(R.id.user_rank)
        public TextView userRank;

        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public LeaderboardItemAdapter(List<Leader> leaders, OnLeaderboardItemSelected listener) {
        mLeaders = leaders;
        mListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public LeaderboardItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_leaderboard_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Leader leader = mLeaders.get(position);
        setViewByLeader(holder, leader);
        holder.view.setOnClickListener(v -> {
            mListener.leaderItemSelected(leader);
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mLeaders == null ? 0 : mLeaders.size();
    }

    @Override
    public long getItemId(int position) {
        return mLeaders.get(position).nutzerId;
    }

    public static void setViewByLeader(ViewHolder holder, Leader leader) {
        Context ctx = holder.view.getContext();
        holder.userIcon.setImageDrawable(ImageUtils.getUserAvatar(ctx, leader.nutzerId));
        holder.userName.setText(leader.name);
        holder.userRank.setText("" + leader.position);
    }

}
