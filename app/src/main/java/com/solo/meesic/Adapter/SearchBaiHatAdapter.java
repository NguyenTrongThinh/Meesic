package com.solo.meesic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.meesic.Activity.PlayMusicActivity;
import com.solo.meesic.Model.Song;
import com.solo.meesic.R;
import com.solo.meesic.databinding.DongSearchBaihatBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchBaiHatAdapter extends RecyclerView.Adapter<SearchBaiHatAdapter.ViewHolder> {

    private DongSearchBaihatBinding binding;
    private Context context;
    private ArrayList<Song> songArrayList;
    private View view;

    public SearchBaiHatAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.dong_search_baihat, viewGroup, false);

        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songArrayList.get(i);
        viewHolder.txtTitle.setText(song.getTenbaihat());
        viewHolder.txtSinger.setText(song.getCasi());
        Picasso.with(context).load(song.getHinhbaihat()).into(viewHolder.imgSong);

    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgSong;
        TextView txtTitle, txtSinger;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSinger = binding.dongSearchBaihatTextSinger;
            txtTitle = binding.dongSearchBaihatTextTitle;
            imgSong = binding.dongSearchBaihatImg;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlayMusicActivity.class);
                    intent.putExtra("cakhuc", songArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
