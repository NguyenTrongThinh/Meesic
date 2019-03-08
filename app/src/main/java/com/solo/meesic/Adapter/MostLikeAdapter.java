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
import com.solo.meesic.Service.WebhostService;
import com.solo.meesic.Service.WebhostServiceAPI;
import com.solo.meesic.databinding.SingleMostLikeSongBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostLikeAdapter extends RecyclerView.Adapter<MostLikeAdapter.ViewHolder>{
    private SingleMostLikeSongBinding binding;
    private Context context;
    private View view;
    private List<Song> songList;
    private OnItemClickListener listener;
    public MostLikeAdapter(Context context, List<Song> songList, OnItemClickListener listener) {
        this.context = context;
        this.songList = songList;
        this.listener = listener;
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
        viewHolder.bind(songList.get(i), listener);
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
            imgLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLove.setImageResource(R.drawable.iconloved);
                    WebhostService webhostService = WebhostServiceAPI.getService();
                    final Call<String> callback = webhostService.UpdateLuotThich("1", songList.get(getAdapterPosition()).getIdbaihat());
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result = response.body();
                            if (result.equals("Success")) {
                                imgLove.setEnabled(false);
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            callback.cancel();
                        }
                    });
                }
            });
        }
        public void bind(final Song item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onSongListItemClick(item);
                }
            });
        }
    }
}
