package com.solo.meesic.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.solo.meesic.Model.QuangCao;
import com.solo.meesic.R;
import com.solo.meesic.databinding.ActivitySongListBinding;

public class SongListActivity extends AppCompatActivity {

    private ActivitySongListBinding binding;
    QuangCao quangCao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_song_list);
        DataIntent();
        Init();

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
