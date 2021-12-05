package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.entity.UserReward;
import com.example.e_cynic.utils.DateUtil;

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
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rewards_history_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardHistoryAdapter.ViewHolder holder, int position)
    {
        if (userRewardList != null)
        {
            //holder.redeemImgList.setImageResource();
            holder.redeemNameList.setText(userRewardList.get(position).rewardItem);
            holder.redeemPointList.setText(String.valueOf(userRewardList.get(position).points));
            holder.redeemDateList.setText(String.valueOf(DateUtil.getDateTimeByTimestamp(userRewardList.get(position).date)));
        }
    }

    @Override
    public int getItemCount()
    {
        return userRewardList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView redeemImgList;
        TextView redeemNameList,redeemPointList,redeemDateList;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            redeemImgList = itemView.findViewById(R.id.redeemImgList);
            redeemNameList = itemView.findViewById(R.id.redeemNameList);
            redeemPointList = itemView.findViewById(R.id.redeemPointList);
            redeemDateList = itemView.findViewById(R.id.redeemDateList);
        }
    }
}
