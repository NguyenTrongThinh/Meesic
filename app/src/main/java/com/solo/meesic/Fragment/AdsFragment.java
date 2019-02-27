package com.solo.meesic.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.meesic.Adapter.AdsAdapter;
import com.solo.meesic.Model.QuangCao;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.FragmentAdsBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdsFragment extends Fragment {


    private View view;
    private AdsAdapter adsAdapter;
    private Runnable runnable;
    private Handler handler;
    private int currentItem;
    private FragmentAdsBinding binding;
    public AdsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_ads, container, false);
        GetData();
        view = binding.getRoot();
        return view;
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        Call<List<QuangCao>> callback = webhostService.GetDataBanner();
        callback.enqueue(new Callback<List<QuangCao>>() {
            @Override
            public void onResponse(Call<List<QuangCao>> call, Response<List<QuangCao>> response) {
                ArrayList<QuangCao> banner = (ArrayList<QuangCao>) response.body();
                adsAdapter = new AdsAdapter(getActivity(), banner);
                binding.fragmentAdsViewpager.setAdapter(adsAdapter);
                binding.fragmentAdsIndicator.setViewPager(binding.fragmentAdsViewpager);
                handler = new Handler();
                runnable = new Runnable() {
                    @Override
                    public void run() {
                        currentItem = binding.fragmentAdsViewpager.getCurrentItem();
                        currentItem++;
                        if (currentItem >= binding.fragmentAdsViewpager.getAdapter().getCount()) {
                            currentItem = 0;
                        }
                        binding.fragmentAdsViewpager.setCurrentItem(currentItem, true);
                        handler.postDelayed(runnable, 4500);
                    }
                };
                handler.postDelayed(runnable, 4500);
            }

            @Override
            public void onFailure(Call<List<QuangCao>> call, Throwable t) {
                call.cancel();
            }
        });
    }

}
