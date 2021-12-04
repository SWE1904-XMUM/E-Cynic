package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.entity.Voucher;
import com.example.e_cynic.session.SessionManager;

import java.util.List;

public class VoucherListAdapter extends RecyclerView.Adapter<VoucherListAdapter.MyViewHolder>
{
    Context context;
    List<Voucher> voucherList;
    SessionManager sm;

    public VoucherListAdapter(Context context, List<Voucher> voucherList)
    {
        this.context = context;
        this.voucherList = voucherList;
        sm = new SessionManager(context);
    }

    @NonNull
    @Override
    public VoucherListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.voucher_list,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoucherListAdapter.MyViewHolder holder, int position)
    {
        holder.voucherImgList.setImageResource(voucherList.get(position).voucherImage);
        holder.voucherNameList.setText(voucherList.get(position).voucherName);
        holder.voucherPointList.setText(String.valueOf(voucherList.get(position).voucherPoints));

        /*if (Integer.parseInt(String.valueOf(holder.voucherPointList.getText())) < sm.getTotalPoints())
        {
            holder.redeemBtnList.setEnabled(false);
        }*/

        holder.redeemBtnList.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                //TODO -> link to redeem history page
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return voucherList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView voucherImgList;
        TextView voucherNameList,voucherPointList;
        Button redeemBtnList;

        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);

            voucherImgList = itemView.findViewById(R.id.voucherImgList);
            voucherNameList = itemView.findViewById(R.id.voucherNameList);
            voucherPointList = itemView.findViewById(R.id.voucherPointList);
            redeemBtnList = itemView.findViewById(R.id.redeemBtnList);
        }
    }
}
