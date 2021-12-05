package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.e_cynic.entity.UserReward;
import java.util.List;

public class RewardHistoryAdapter extends RecyclerView.Adapter<RewardHistoryAdapter.ViewHolder>
{
    Context context;
    List<UserReward> userRewardList;

    public RewardHistoryAdapter(Context context, List<UserReward> userRewardList)
    {
        this.context = context;
        this.userRewardList = userRewardList;
    }

    @NonNull
    @Override
    public RewardHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RewardHistoryAdapter.ViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
