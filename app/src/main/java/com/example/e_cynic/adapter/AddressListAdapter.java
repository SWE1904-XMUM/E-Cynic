package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.entity.Address;

import java.util.List;

public class AddressListAdapter extends RecyclerView.Adapter<AddressListAdapter.MyViewHolder> {
    private Context context;
    private List<Address> addressList;

    public AddressListAdapter(Context context, List<Address> addressList) {
        this.context = context;
        this.addressList = addressList;
    }

    @NonNull
    @Override
    public AddressListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_addresses_list, parent, false);
        return new AddressListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddressListAdapter.MyViewHolder holder, int position) {
        holder.tv_address_id.setText(String.valueOf(addressList.get(position).addressId));
        holder.tv_address_index.setText(String.valueOf(position + 1));
        holder.tv_address_line1.setText(addressList.get(position).firstLine);
        if(!addressList.get(position).secondLine.equals("")) {
            holder.tv_address_line2.setText(addressList.get(position).secondLine);
        }
        else {
            holder.tv_address_line2.setVisibility(View.GONE);
        }
        if(!addressList.get(position).thirdLine.equals("")) {
            holder.tv_address_line3.setText(addressList.get(position).thirdLine);
        }
        else {
            holder.tv_address_line3.setVisibility(View.GONE);
        }
        holder.tv_address_postcode_city.setText(String.valueOf(addressList.get(position).postcode) + " " + addressList.get(position).city);
        holder.tv_address_state.setText(addressList.get(position).state);
    }

    @Override
    public int getItemCount() {
        return addressList != null ? addressList.size() : 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_address_id;
        TextView tv_address_index;
        TextView tv_address_line1;
        TextView tv_address_line2;
        TextView tv_address_line3;
        TextView tv_address_postcode_city;
        TextView tv_address_state;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_address_id = itemView.findViewById(R.id.tv_address_id);
            tv_address_index = itemView.findViewById(R.id.tv_address_index) ;
            tv_address_line1 = itemView.findViewById(R.id.tv_address_line1) ;
            tv_address_line2 = itemView.findViewById(R.id.tv_address_line2) ;
            tv_address_line3 = itemView.findViewById(R.id.tv_address_line3) ;
            tv_address_postcode_city = itemView.findViewById(R.id.tv_address_postcode_city) ;
            tv_address_state = itemView.findViewById(R.id.tv_address_state) ;
        }
    }
}
