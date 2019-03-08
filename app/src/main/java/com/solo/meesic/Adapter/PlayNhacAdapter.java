package com.solo.meesic.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.databinding.DongPlayBaiHatBinding;

import java.util.ArrayList;

public class PlayNhacAdapter extends RecyclerView.Adapter<PlayNhacAdapter.ViewHolder> {
    private DongPlayBaiHatBinding binding;
    private Context context;
    private ArrayList<Song> songArrayList;
    private View view;
    public PlayNhacAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.dong_play_bai_hat, viewGroup, false);

        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songArrayList.get(i);
        viewHolder.txtTitle.setText(song.getTenbaihat());
        viewHolder.txtSinger.setText(song.getCasi());
        viewHolder.txtSongIndex.setText(String.valueOf(i + 1));
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle, txtSinger, txtSongIndex;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSinger = binding.dongPlayBaiHatTextSongSinger;
            txtSongIndex = binding.dongPlayBaiHatTextSongIndex;
            txtTitle = binding.dongPlayBaiHatTextSongTitle;
        }
    }
}
