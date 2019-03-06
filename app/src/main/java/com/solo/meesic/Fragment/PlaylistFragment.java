package com.solo.meesic.Fragment;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.meesic.Activity.SongListActivity;
import com.solo.meesic.Adapter.OnPlaylistItemClickListener;
import com.solo.meesic.Adapter.PlaylistAdapter;
import com.solo.meesic.Model.Playlist;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.FragmentPlaylistBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlaylistFragment extends Fragment {

    View view;
    FragmentPlaylistBinding binding;
    private PlaylistAdapter playlistAdapter;
    private ArrayList<Playlist> playlistArrayList;

    public PlaylistFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_playlist, container, false);
        GetData();
        view = binding.getRoot();
        return view;
    }


    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        Call<List<Playlist>> callback = webhostService.GetPlaylistCurrentDay();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                playlistArrayList = (ArrayList<Playlist>) response.body();
                playlistAdapter = new PlaylistAdapter(getActivity(), playlistArrayList, new OnPlaylistItemClickListener() {
                    @Override
                    public void onPlaylistItemClick(Playlist item) {
                        Intent intent = new Intent(getContext(), SongListActivity.class);
                        intent.putExtra("itemplaylist", item);
                        startActivity(intent);
                    }
                });
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                binding.fragmentPlaylistRecycleViewList.setLayoutManager(linearLayoutManager);
                binding.fragmentPlaylistRecycleViewList.setAdapter(playlistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                call.cancel();
            }
        });
    }
}
