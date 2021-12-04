package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RedeemListAdapter extends RecyclerView.Adapter<RedeemListAdapter.MyViewHolder>
{
    Context context;

    public RedeemListAdapter(@NonNull Context context)
    {

    }

    @NonNull
    @Override
    public RedeemListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemListAdapter.MyViewHolder holder, int position)
    {

    }

    @Override
    public int getItemCount()
    {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
