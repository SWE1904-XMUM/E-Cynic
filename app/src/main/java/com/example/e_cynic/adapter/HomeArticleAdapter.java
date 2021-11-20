package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;

public class HomeArticleAdapter extends RecyclerView.Adapter<HomeArticleAdapter.MyViewHolder> {
    //retrieve the data from array
    String d1[], d2[];
    Context context;
    public HomeArticleAdapter(Context ct, String s1[], String s2[]) {
        context = ct;
        d1 = s1;
        d2 = s2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rowarticle, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title.setText(d1[position]);
        holder.description.setText(d2[position]);
    }

    @Override
    public int getItemCount() {
        //item card
        return d2.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView title, description;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tv_articleTitle);
            description = itemView.findViewById(R.id.tv_articleDescription);
        }
    }
}