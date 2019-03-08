package com.solo.meesic.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.solo.meesic.Adapter.AllAlbumAdapter;
import com.solo.meesic.Model.AlbumHot;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.ActivityAllAlbumListBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllAlbumListActivity extends AppCompatActivity {
    private ActivityAllAlbumListBinding binding;
    private AllAlbumAdapter allAlbumAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_album_list);
        init();
        GetData();
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<AlbumHot>> callback = webhostService.GetAllAlbum();
        callback.enqueue(new Callback<List<AlbumHot>>() {
            @Override
            public void onResponse(Call<List<AlbumHot>> call, Response<List<AlbumHot>> response) {
                ArrayList<AlbumHot> albumHotArrayList = (ArrayList<AlbumHot>) response.body();
                allAlbumAdapter = new AllAlbumAdapter(AllAlbumListActivity.this, albumHotArrayList);
                binding.activityAllAlbumListRecyclerView.setLayoutManager(new GridLayoutManager(AllAlbumListActivity.this, 2));
                binding.activityAllAlbumListRecyclerView.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<AlbumHot>> call, Throwable t) {
                callback.cancel();
            }
        });
    }

    private void init() {
        setSupportActionBar(binding.activityAllAlbumListToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Albums");
        binding.activityAllAlbumListToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
