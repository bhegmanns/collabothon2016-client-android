package de.comdirect.collabothon2016.collabothon2016.viewadapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.comdirect.collabothon2016.collabothon2016.R;
import de.comdirect.collabothon2016.collabothon2016.model.Vote;
import de.comdirect.collabothon2016.collabothon2016.util.ImageUtils;

public class VoteItemAdapter extends RecyclerView.Adapter<VoteItemAdapter.ViewHolder> {

    public interface OnVoteItemSelected {
        void voteItemSelected(Vote vote);
    }

    private OnVoteItemSelected mListener;

    private List<Vote> mVotes;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        @BindView(R.id.vote_stock_icon)
        public ImageView voteStockIcon;
        @BindView(R.id.vote_stock_name)
        public TextView voteStockName;
        @BindView(R.id.vote_stock_isin)
        public TextView voteStockIsin;
        @BindView(R.id.vote_invest_button)
        public Button voteInvestButton;
        @BindView(R.id.voteBackgroundBox)
        public LinearLayout voteBackgroundBox;

        public View view;

        public ViewHolder(View v) {
            super(v);
            view = v;
            ButterKnife.bind(this, v);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public VoteItemAdapter(List<Vote> votes, OnVoteItemSelected listener) {
        mVotes = votes;
        mListener = listener;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public VoteItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_vote_item, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        Vote vote = mVotes.get(position);
        setViewByVote(holder, vote);
        holder.view.setOnClickListener(v -> {
            mListener.voteItemSelected(vote);
        });

        holder.voteBackgroundBox.setBackground(new ColorDrawable(Color.WHITE));

        holder.voteInvestButton.setOnClickListener(v -> {
            holder.voteBackgroundBox.setBackground(new ColorDrawable(Color.rgb(0, 170, 0)));
        });


    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mVotes == null ? 0 : mVotes.size();
    }

    @Override
    public long getItemId(int position) {
        return mVotes.get(position).userId;
    }

    public static void setViewByVote(ViewHolder holder, Vote vote) {
        Context ctx = holder.view.getContext();

        int memberCount = 0;
//        Log.e(BuildConfig.LOG_TAG, "binding item: " + position);

        holder.voteStockName.setText(vote.title);
        holder.voteStockIsin.setText(vote.wertpapier);
        holder.voteStockIcon.setImageDrawable(ImageUtils.getStockByIsin(ctx, vote.wertpapier));

    }

}
