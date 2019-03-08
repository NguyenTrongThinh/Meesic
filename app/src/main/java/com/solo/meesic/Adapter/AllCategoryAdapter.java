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

import com.solo.meesic.Activity.TypeListByCategoryActivity;
import com.solo.meesic.Model.ChuDe;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleAllCategoryContentBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.ViewHolder> {
    private SingleAllCategoryContentBinding binding;
    private Context context;
    private List<ChuDe> chuDeList;
    private View view;
    public AllCategoryAdapter(Context context, List<ChuDe> chuDeList) {
        this.context = context;
        this.chuDeList = chuDeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_all_category_content, viewGroup, false);

        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChuDe chuDe = chuDeList.get(i);
        Picasso.with(context).load(chuDe.getHinhChuDe()).into(viewHolder.imgCategpory);
    }

    @Override
    public int getItemCount() {
        return chuDeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCategpory;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCategpory = binding.singleAllCategoryContentImage;
            imgCategpory.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, TypeListByCategoryActivity.class);
                    intent.putExtra("chude", chuDeList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
