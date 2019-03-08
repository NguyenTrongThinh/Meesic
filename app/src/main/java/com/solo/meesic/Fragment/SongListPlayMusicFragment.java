package com.solo.meesic.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.solo.meesic.Activity.PlayMusicActivity;
import com.solo.meesic.Adapter.PlayNhacAdapter;
import com.solo.meesic.R;
import com.solo.meesic.databinding.FragmentSongListPlayMusicBinding;

/**
 * A simple {@link Fragment} subclass.
 */
public class SongListPlayMusicFragment extends Fragment {


    private FragmentSongListPlayMusicBinding binding;
    private View view;
    private PlayNhacAdapter playNhacAdapter;

    public SongListPlayMusicFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_song_list_play_music, container, false);
        if (PlayMusicActivity.songArrayList.size() > 0) {
            playNhacAdapter = new PlayNhacAdapter(getActivity(), PlayMusicActivity.songArrayList);
            binding.fragmentSongListPlayMusicRecyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
            binding.fragmentSongListPlayMusicRecyclerview.setAdapter(playNhacAdapter);
        }

        view = binding.getRoot();
        return view;
    }

}
