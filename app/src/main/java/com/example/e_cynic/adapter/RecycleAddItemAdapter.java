package com.example.e_cynic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.activity.RecycleActivity;

import java.util.List;

public class RecycleAddItemAdapter extends RecyclerView.Adapter<RecycleAddItemAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<String> data;
    String[] itemSelectionList;
    Context context;

    public RecycleAddItemAdapter(Context ct, List<String> item){
        context = ct;
        data = item;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycle_add_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemNo.setText(data.get(position));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView itemNo;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //add item id
            itemNo = itemView.findViewById(R.id.arr_noOfItem);

                //Sort drop down list
            Spinner spinner = itemView.findViewById(R.id.sp_itemSelection);
            itemSelectionList = itemView.getResources().getStringArray(R.array.itemsSelection);
            ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(context,
                    android.R.layout.simple_list_item_1,
                    itemSelectionList);
            myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(myAdapter);
        }
    }
}
