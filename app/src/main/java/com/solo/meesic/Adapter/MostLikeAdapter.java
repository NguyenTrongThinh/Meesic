package com.solo.meesic.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleMostLikeSongBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MostLikeAdapter extends RecyclerView.Adapter<MostLikeAdapter.ViewHolder>{
    private SingleMostLikeSongBinding binding;
    private Context context;
    private View view;
    private List<Song> songList;

    public MostLikeAdapter(Context context, List<Song> songList) {
        this.context = context;
        this.songList = songList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_most_like_song, viewGroup, false);

        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songList.get(i);
        viewHolder.txtSongName.setText(song.getTenbaihat());
        viewHolder.txtSingerName.setText(song.getCasi());
        Picasso.with(context).load(song.getHinhbaihat()).into(viewHolder.imgSongImage);

    }

    @Override
    public int getItemCount() {
        return songList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtSongName, txtSingerName;
        ImageView imgSongImage, imgLove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSingerName = binding.singleMostLikeSingerName;
            txtSongName = binding.singleMostLikeSongName;
            imgLove = binding.singleMostLikeIconLove;
            imgSongImage = binding.singleMostLikeSongImage;

        }
    }
}
