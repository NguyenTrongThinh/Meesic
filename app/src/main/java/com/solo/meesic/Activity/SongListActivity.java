package com.solo.meesic.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.solo.meesic.Adapter.SongListAdapter;
import com.solo.meesic.Model.QuangCao;
import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.ActivitySongListBinding;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListActivity extends AppCompatActivity {

    private ActivitySongListBinding binding;
    private QuangCao quangCao;
    private ArrayList<Song> songArrayList;
    private SongListAdapter songListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_song_list);
        DataIntent();
        Init();
        if (quangCao != null && !quangCao.getTenBaiHat().equals("")) {
            setValueInview(quangCao.getTenBaiHat(), quangCao.getHinhBaiHat());
            GetDataQuangCao(quangCao.getIdQuangCao());
        }
    }

    private void GetDataQuangCao(String idquangcao) {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<Song>> callback = webhostService.GetDanhSachBaiHatTheoQuangCao(idquangcao);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                songListAdapter = new SongListAdapter(SongListActivity.this, songArrayList);
                binding.activitySongSongList.setLayoutManager(new LinearLayoutManager(SongListActivity.this));
                binding.activitySongSongList.setAdapter(songListAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {
                callback.cancel();
            }
        });
    }

    private void setValueInview(String name, String image) {
        binding.activitySongCollapsingToolbar.setTitle(name);
        try {
            URL url = new URL(image);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(), bitmap);
            binding.activitySongCollapsingToolbar.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(image).into(binding.activitySongImageSongList);
    }

    private void Init() {
        setSupportActionBar(binding.activitySongToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        binding.activitySongToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        binding.activitySongCollapsingToolbar.setExpandedTitleColor(Color.WHITE);
        binding.activitySongCollapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void DataIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            if (intent.hasExtra("ads")) {
                quangCao = (QuangCao) intent.getSerializableExtra("ads");

            }
        }
    }
}
