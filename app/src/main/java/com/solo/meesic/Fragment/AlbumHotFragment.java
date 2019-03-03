package com.solo.meesic.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.meesic.Adapter.AlbumHotAdapter;
import com.solo.meesic.Model.AlbumHot;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.FragmentAlbumHotBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumHotFragment extends Fragment {

    private View view;
    private FragmentAlbumHotBinding binding;
    public AlbumHotFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_album_hot, container, false);
        GetData();
        view = binding.getRoot();
        return view;
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<AlbumHot>> callback = webhostService.GetAlbumHot();
        callback.enqueue(new Callback<List<AlbumHot>>() {
            @Override
            public void onResponse(Call<List<AlbumHot>> call, Response<List<AlbumHot>> response) {
                ArrayList<AlbumHot> albumHotArrayList = (ArrayList<AlbumHot>) response.body();
                AlbumHotAdapter albumHotAdapter = new AlbumHotAdapter(getActivity(), albumHotArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                binding.fragmentAlbumHotRecycleViewAlbum.setLayoutManager(linearLayoutManager);
                binding.fragmentAlbumHotRecycleViewAlbum.setAdapter(albumHotAdapter);
            }

            @Override
            public void onFailure(Call<List<AlbumHot>> call, Throwable t) {
                callback.cancel();
            }
        });
    }

}
