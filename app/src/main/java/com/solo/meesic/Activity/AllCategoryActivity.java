package com.solo.meesic.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;

import com.solo.meesic.Adapter.AllCategoryAdapter;
import com.solo.meesic.Model.ChuDe;
import com.solo.meesic.R;
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.ActivityAllCategoryBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategoryActivity extends AppCompatActivity {

    ActivityAllCategoryBinding binding;
    AllCategoryAdapter allCategoryAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_category);
        init();
        GetData();
    }

    private void GetData() {
        WebhostService webhostService = WebhostServiceAPI.getService();
        final Call<List<ChuDe>> callback = webhostService.GetAllChuDe();
        callback.enqueue(new Callback<List<ChuDe>>() {
            @Override
            public void onResponse(Call<List<ChuDe>> call, Response<List<ChuDe>> response) {
                ArrayList<ChuDe> chuDeArrayList = (ArrayList<ChuDe>) response.body();
                allCategoryAdapter = new AllCategoryAdapter(AllCategoryActivity.this, chuDeArrayList);
                binding.activityAllCategoryRecyclerviewAllCategory.setLayoutManager(new GridLayoutManager(AllCategoryActivity.this, 1));
                binding.activityAllCategoryRecyclerviewAllCategory.setAdapter(allCategoryAdapter);
            }

            @Override
            public void onFailure(Call<List<ChuDe>> call, Throwable t) {
                callback.cancel();
            }
        });
    }

    private void init() {
        setSupportActionBar(binding.activityAllCategoryToolbarCategory);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("All Category");
        binding.activityAllCategoryToolbarCategory.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
