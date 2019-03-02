package com.solo.meesic.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.solo.meesic.Model.ChuDe;
import com.solo.meesic.Model.DailyCategory;
import com.solo.meesic.Model.TheLoai;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.FragmentGroupBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class GroupFragment extends Fragment {

    private FragmentGroupBinding binding;
    private View view;
    public GroupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_group, container, false);
        GetData();
        view = binding.getRoot();
        return view;
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        Call<DailyCategory> call = webhostService.GetCategoryMusic();
        call.enqueue(new Callback<DailyCategory>() {
            @Override
            public void onResponse(Call<DailyCategory> call, Response<DailyCategory> response) {
                DailyCategory dailyCategory = response.body();
                final ArrayList<ChuDe> chuDeArrayList = new ArrayList<>();
                chuDeArrayList.addAll(dailyCategory.getChuDe());
                final ArrayList<TheLoai> theLoaiArrayList = new ArrayList<>();
                theLoaiArrayList.addAll(dailyCategory.getTheLoai());
                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(580, 250);
                layoutParams.setMargins(10, 20, 10, 30);

                for (int i = 0; i < chuDeArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (chuDeArrayList.get(i).getHinhChuDe() != null ) {
                        Picasso.with(getActivity()).load(chuDeArrayList.get(i).getHinhChuDe()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                for (int i = 0; i < chuDeArrayList.size(); i++) {
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if (theLoaiArrayList.get(i).getHinhTheLoai() != null ) {
                        Picasso.with(getActivity()).load(theLoaiArrayList.get(i).getHinhTheLoai()).into(imageView);
                    }
                    cardView.setLayoutParams(layoutParams);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                }
                binding.fragmentGroupHorizontalScrollView.addView(linearLayout);

            }

            @Override
            public void onFailure(Call<DailyCategory> call, Throwable t) {
                call.cancel();
            }
        });

    }

}
