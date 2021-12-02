package com.example.e_cynic.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.entity.Item;
import com.example.e_cynic.entity.Order;
import com.example.e_cynic.utils.ImageUtils;

import java.util.ArrayList;

public class HistoryOrderListAdapter extends RecyclerView.Adapter<HistoryOrderListAdapter.MyViewHoler>
{
    Context context;
    private ArrayList<Order> historyOrders;
    private ArrayList<Item> historyItem;

    public HistoryOrderListAdapter(Context context,ArrayList<Order> historyOrders,ArrayList<Item> historyItem)
    {
        this.context = context;
        this.historyOrders = historyOrders;
        this.historyItem = historyItem;
    }

    @NonNull
    @Override
    public HistoryOrderListAdapter.MyViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.orders_list,parent,false);
        return new MyViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoler holder, int position)
    {
        Bitmap bitmap = ImageUtils.byteArrayToBitmap(historyItem.get(position).image);
        if(bitmap != null)
        {
            holder.orderImageList.setImageBitmap(bitmap);
        }
        holder.orderList.setText(String.valueOf(historyOrders.get(position).orderId));
        holder.orderStatusList.setText(String.valueOf(historyOrders.get(position).status));
        holder.orderDateList.setText(String.valueOf(historyOrders.get(position).date));
    }

    @Override
    public int getItemCount()
    {
        return historyOrders.size();
    }

    public class MyViewHoler extends RecyclerView.ViewHolder
    {
        ImageView orderImageList;
        TextView orderList,orderStatusList,orderDateList;

        public MyViewHoler(@NonNull View itemView)
        {
            super(itemView);

            orderImageList = itemView.findViewById(R.id.orderImageList);
            orderList = itemView.findViewById(R.id.orderList);
            orderStatusList = itemView.findViewById(R.id.orderStatusList);
            orderDateList = itemView.findViewById(R.id.orderDateList);
        }
    }
}
