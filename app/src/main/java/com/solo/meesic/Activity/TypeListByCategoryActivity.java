package com.solo.meesic.Activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.solo.meesic.Adapter.TypeByCategoryAdapter;
import com.solo.meesic.Model.ChuDe;
import com.solo.meesic.Model.TheLoai;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.ActivityTypeListByCategoryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TypeListByCategoryActivity extends AppCompatActivity {

    private ChuDe chuDe;
    private ActivityTypeListByCategoryBinding binding;
    private TypeByCategoryAdapter typeByCategoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_type_list_by_category);
        GetIntent();
        init();
        GetData(chuDe.getIdChuDe());
    }

    private void GetData(String idchude) {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<TheLoai>> callback = webhostService.GetTheLoaiTheoChuDe(idchude);
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> theLoaiArrayList = (ArrayList<TheLoai>) response.body();
                typeByCategoryAdapter = new TypeByCategoryAdapter(TypeListByCategoryActivity.this, theLoaiArrayList);
                binding.activityTypeListByCategoryRecyclerView.setLayoutManager(new GridLayoutManager(TypeListByCategoryActivity.this, 2));
                binding.activityTypeListByCategoryRecyclerView.setAdapter(typeByCategoryAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {
                callback.cancel();
            }
        });
    }

    private void init() {
        setSupportActionBar(binding.activityTypeListByCategoryToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTenChuDe());
        binding.activityTypeListByCategoryToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("chude")) {
            chuDe = (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}
