package com.example.e_cynic.adapter;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e_cynic.R;
import com.example.e_cynic.activity.RecycleActivity;
import com.example.e_cynic.constants.RequestCode;
import com.example.e_cynic.entity.Item;
import com.example.e_cynic.permission.Permissions;
import com.example.e_cynic.utils.ImageUtils;

import java.util.List;

public class RecycleAddItemAdapter extends RecyclerView.Adapter<RecycleAddItemAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Item> data;
    private String[] itemSelectionList;
    private Context context;
    private AppCompatActivity activity;

    public RecycleAddItemAdapter(Context ct, List<Item> item, AppCompatActivity activity) {
        context = ct;
        data = item;
        this.activity = activity;
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
        holder.itemNo.setText(data.get(position).itemName);
        Bitmap bitmap = ImageUtils.byteArrayToBitmap(data.get(position).image);
        if(bitmap != null) {
            holder.imageView.setImageBitmap(bitmap);
        }
        int pos = position;

        holder.imageView.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (ActivityCompat.checkSelfPermission(holder.imageView.getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(holder.imageView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        Permissions permissions = new Permissions();
                        permissions.grantPhotoPermission(holder.imageView.getContext());
                    }
                    if (activity.getClass() == RecycleActivity.class) {
                        ((RecycleActivity) activity).clickedItem = pos;
                    }
                    selectImg(holder.imageView.getContext(), activity);
                }
            }
        );
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView itemNo;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //add item id
            itemNo = itemView.findViewById(R.id.arr_noOfItem);
            imageView = itemView.findViewById(R.id.uploadImg);

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

    private void selectImg(Context context, AppCompatActivity activity) {
        final CharSequence[] options = {"Snap photo", "Choose from gallery", "Cancel"};
        AlertDialog.Builder ad = new AlertDialog.Builder(context);
        ad.setTitle("Upload photo");

        ad.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (options[i].equals("Snap photo")) {
                    Intent snapPhoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    activity.startActivityForResult(snapPhoto, RequestCode.SNAP_PHOTO);
                } else if (options[i].equals("Choose from gallery")) {
                    Intent chooseFromGallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    activity.startActivityForResult(chooseFromGallery, RequestCode.CHOOSE_FROM_GALLERY);
                } else if (options[i].equals("Cancel")) {
                    dialogInterface.dismiss();
                }
                System.out.println("clicked");
            }
        });

        ad.show();
    }
}
