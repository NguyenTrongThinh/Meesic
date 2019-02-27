package com.solo.meesic.Activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.solo.meesic.Adapter.MainViewAdapter;
import com.solo.meesic.Fragment.HomeFragment;
import com.solo.meesic.Fragment.SearchFragment;
import com.solo.meesic.R;
import com.solo.meesic.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        init();
    }

    private void init() {
        MainViewAdapter mainViewAdapter = new MainViewAdapter(getSupportFragmentManager());
        mainViewAdapter.addFragment(new HomeFragment(), getResources().getString(R.string.text_trang_chu));
        mainViewAdapter.addFragment(new SearchFragment(), getResources().getString(R.string.text_search));
        binding.activityMainViewpager.setAdapter(mainViewAdapter);
        binding.activityMainTablayout.setupWithViewPager(binding.activityMainViewpager);
        binding.activityMainTablayout.getTabAt(0).setIcon(R.drawable.icontrangchu);
        binding.activityMainTablayout.getTabAt(1).setIcon(R.drawable.iconsearch);
    }
}
