package com.solo.meesic.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.solo.meesic.Adapter.AllPlaylistAdapter;
import com.solo.meesic.Model.Playlist;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.ActivityPlaylistListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaylistListActivity extends AppCompatActivity {

    ActivityPlaylistListBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_playlist_list);
        init();
        GetData();
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<Playlist>> callback = webhostService.GetDanhSachPlaylists();
        callback.enqueue(new Callback<List<Playlist>>() {
            @Override
            public void onResponse(Call<List<Playlist>> call, Response<List<Playlist>> response) {
                ArrayList<Playlist> playlistArrayList = (ArrayList<Playlist>) response.body();
                AllPlaylistAdapter allPlaylistAdapter = new AllPlaylistAdapter(PlaylistListActivity.this, playlistArrayList);
                binding.activityPlaylistListRecyclerview.setLayoutManager(new GridLayoutManager(PlaylistListActivity.this, 2));
                binding.activityPlaylistListRecyclerview.setAdapter(allPlaylistAdapter);
            }

            @Override
            public void onFailure(Call<List<Playlist>> call, Throwable t) {
                callback.cancel();
            }
        });
    }

    private void init() {
        setSupportActionBar(binding.activityPlaylistListToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Playlist");
        binding.activityPlaylistListToolbar.setTitleTextColor(getResources().getColor(R.color.purple));
        binding.activityPlaylistListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
