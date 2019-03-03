package com.solo.meesic.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.meesic.Adapter.MostLikeAdapter;
import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.FragmentMostLikeBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostLikeFragment extends Fragment {


    private FragmentMostLikeBinding binding;
    private View view;
    public MostLikeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_most_like, container, false);
        GetData();
        view = binding.getRoot();
        return view;
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<Song>> callback = webhostService.GetBaiHatHot();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
                MostLikeAdapter mostLikeAdapter = new MostLikeAdapter(getActivity(), songArrayList);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                binding.fragmentMostLikeRecycleViewSong.setLayoutManager(layoutManager);
                binding.fragmentMostLikeRecycleViewSong.setAdapter(mostLikeAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                callback.cancel();

            }
        });
    }

}
