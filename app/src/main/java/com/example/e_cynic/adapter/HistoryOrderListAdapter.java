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
import com.example.e_cynic.db.ItemDatabase;
import com.example.e_cynic.entity.Order;
import com.example.e_cynic.utils.DateUtil;

import java.util.List;

public class HistoryOrderListAdapter extends RecyclerView.Adapter<HistoryOrderListAdapter.MyViewHolder>
{
    Context context;
    private List<Order> historyOrders;

    public HistoryOrderListAdapter(Context context,List<Order> historyOrders)
    {
        this.context = context;
        this.historyOrders = historyOrders;
    }

    @NonNull
    @Override
    public HistoryOrderListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.orders_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        try {
            holder.orderImageList.setImageBitmap(ItemDatabase.getFirstItemImageByOrderId(historyOrders.get(position).orderId));
            holder.orderList.setText("Order " + String.valueOf(historyOrders.get(position).orderId));
            holder.orderStatusList.setText(String.valueOf(historyOrders.get(position).status));
            holder.orderDateList.setText(String.valueOf(DateUtil.getDateTimeByTimestamp(historyOrders.get(position).date)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount()
    {
        return historyOrders != null ? historyOrders.size():0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView orderImageList;
        TextView orderList,orderStatusList,orderDateList;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            orderImageList = itemView.findViewById(R.id.orderImageList);
            orderList = itemView.findViewById(R.id.orderList);
            orderStatusList = itemView.findViewById(R.id.orderStatusList);
            orderDateList = itemView.findViewById(R.id.orderDateList);
        }
    }
}
