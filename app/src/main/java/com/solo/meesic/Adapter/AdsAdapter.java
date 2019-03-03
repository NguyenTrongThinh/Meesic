package com.solo.meesic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.solo.meesic.Activity.SongListActivity;
import com.solo.meesic.Model.QuangCao;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleAdsBannerBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdsAdapter extends PagerAdapter {
    Context context;
    ArrayList<QuangCao> quangCaoArrayList;
    private SingleAdsBannerBinding binding;
    private View view;
    public AdsAdapter(Context context, ArrayList<QuangCao> quangCaoArrayList) {
        this.context = context;
        this.quangCaoArrayList = quangCaoArrayList;
    }

    @Override
    public int getCount() {
        return quangCaoArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_ads_banner, container, false);
        Picasso.with(context).load(quangCaoArrayList.get(position).getHinhanh()).into(binding.singleAdsBannerImgBackground);
        Picasso.with(context).load(quangCaoArrayList.get(position).getHinhBaiHat()).into(binding.singleAdsBannerImgAds);
        binding.singleAdsBannerTxtTitle.setText(quangCaoArrayList.get(position).getTenBaiHat());
        binding.singleAdsBannerTxtContent.setText(quangCaoArrayList.get(position).getNoidung());
        view = binding.getRoot();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SongListActivity.class);
                intent.putExtra("ads", quangCaoArrayList.get(position));
                context.startActivity(intent);
            }
        });
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
