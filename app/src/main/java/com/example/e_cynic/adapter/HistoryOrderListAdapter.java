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

public class HistoryOrderListAdapter extends RecyclerView.Adapter<HistoryOrderListAdapter.MyViewHoler>
{
    Context context;
    private byte [] orderImage;
    private String order,orderStatus;
    private int earnedPoints;
    private long orderDate;

    public HistoryOrderListAdapter(Context context,
                                   byte [] orderImage,
                                   String order,
                                   int earnedPoints,
                                   String orderStatus,
                                   long orderDate)
    {
        this.context = context;
        this.orderImage = orderImage;
        this.order = order;
        this.earnedPoints = earnedPoints;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
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
        // TODO -> display image from db
        //holder.orderImageList.setImageBitmap(orderImage);
        holder.orderList.setText(String.valueOf(order));
        holder.earnedPointsList.setText(String.valueOf(earnedPoints));
        holder.orderStatusList.setText(String.valueOf(orderStatus));
        holder.orderDateList.setText(String.valueOf(orderDate));
    }

    @Override
    public int getItemCount()
    {
        // TODO -> return size of list store in db
        return 0;
    }

    public class MyViewHoler extends RecyclerView.ViewHolder
    {
        ImageView orderImageList;
        TextView orderList,earnedPointsList,orderStatusList,orderDateList;

        public MyViewHoler(@NonNull View itemView)
        {
            super(itemView);

            orderImageList = itemView.findViewById(R.id.orderImageList);
            orderList = itemView.findViewById(R.id.orderList);
            earnedPointsList = itemView.findViewById(R.id.earnedPointsList);
            orderStatusList = itemView.findViewById(R.id.orderStatusList);
            orderDateList = itemView.findViewById(R.id.orderDateList);
        }
    }
}
