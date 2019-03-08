package com.solo.meesic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.meesic.Activity.SongListActivity;
import com.solo.meesic.Model.TheLoai;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleTypeByCategoryContentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TypeByCategoryAdapter extends RecyclerView.Adapter<TypeByCategoryAdapter.ViewHolder> {
    private SingleTypeByCategoryContentBinding binding;
    private Context context;
    private  View view;
    private ArrayList<TheLoai> theLoaiList;

    public TypeByCategoryAdapter(Context context, ArrayList<TheLoai> theLoaiList) {
        this.context = context;
        this.theLoaiList = theLoaiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_type_by_category_content, viewGroup, false);
        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        TheLoai theLoai = theLoaiList.get(i);
        Picasso.with(context).load(theLoai.getHinhTheLoai()).into(viewHolder.imgType);
        viewHolder.txtTypeName.setText(theLoai.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        return theLoaiList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgType;
        TextView txtTypeName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgType = binding.singleTypeByCategoryContentImageTypeByCategory;
            txtTypeName= binding.singleTypeByCategoryContentTextTypeByCategory;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("idtheloai", theLoaiList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
