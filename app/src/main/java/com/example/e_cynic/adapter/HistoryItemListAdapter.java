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
import com.example.e_cynic.arrayList.HistoryArrayLists;

import java.util.ArrayList;

public class HistoryItemListAdapter  extends RecyclerView.Adapter<HistoryItemListAdapter.MyViewHoler>
{
    Context context;
    private ArrayList<String> itemImage, itemName,
            numberOfItems,price,date;

    public HistoryItemListAdapter(Context context,
                                  ArrayList<String> itemImage,
                                  ArrayList<String> itemName,
                                  ArrayList<String> numberOfItems,
                                  ArrayList<String> price,
                                  ArrayList<String> date)
    {
        this.context = context;
        this.itemImage = itemImage;
        this.itemName = itemName;
        this.numberOfItems = numberOfItems;
        this.price = price;
        this.date = date;
    }

    @NonNull
    @Override
    public HistoryItemListAdapter.MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_list,parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryItemListAdapter.MyViewHoler holder, int position)
    {
        // TODO -> retrieve image & display
        //holder.itemImageList.setImageBitmap(....itemImage.get(position));
        holder.itemNameList.setText(String.valueOf(HistoryArrayLists.itemImage.get(position)));
        holder.numberOfItemsList.setText(String.valueOf(HistoryArrayLists.numberOfItems.get(position)));
        holder.priceList.setText(String.valueOf(HistoryArrayLists.price.get(position)));
        holder.dateList.setText(String.valueOf(HistoryArrayLists.date.get(position)));
    }

    @Override
    public int getItemCount()
    {
        return HistoryArrayLists.itemName.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder
    {
        ImageView itemImageList;
        TextView itemNameList,numberOfItemsList,priceList,dateList;

        public MyViewHoler(@NonNull View itemView)
        {
            super(itemView);

            itemImageList = itemView.findViewById(R.id.itemImageList);
            itemNameList = itemView.findViewById(R.id.itemNameList);
            numberOfItemsList = itemView.findViewById(R.id.numberOfItemsList);
            priceList = itemView.findViewById(R.id.priceList);
            dateList = itemView.findViewById(R.id.dateList);
        }
    }
}
