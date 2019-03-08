package com.solo.meesic.Fragment;


import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import com.solo.meesic.R;
import com.solo.meesic.databinding.FragmentDiaNhacBinding;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class DiaNhacFragment extends Fragment {


    private FragmentDiaNhacBinding binding;
    private View view;
    private ObjectAnimator objectAnimator;
    public DiaNhacFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dia_nhac, container, false);
        objectAnimator = ObjectAnimator.ofFloat(binding.fragmentDiaNhacCircleimgView, "rotation", 0f, 360f);
        objectAnimator.setDuration(10000);
        objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        view = binding.getRoot();
        return view;
    }
    public void PlayMusic(String imgLink) {
        Picasso.with(getContext()).load(imgLink).into(binding.fragmentDiaNhacCircleimgView);
        objectAnimator.start();
    }

}
