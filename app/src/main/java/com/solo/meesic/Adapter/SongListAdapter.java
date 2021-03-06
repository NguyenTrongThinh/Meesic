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
import com.solo.meesic.databinding.SingleSongListItemBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Song> songArrayList;
    private SingleSongListItemBinding binding;
    private View view;
    private OnItemClickListener listener;
    public SongListAdapter(Context context, ArrayList<Song> songArrayList, OnItemClickListener listener) {
        this.context = context;
        this.songArrayList = songArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_song_list_item, viewGroup, false);
        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Song song = songArrayList.get(i);
        viewHolder.txtSinger.setText(song.getCasi());
        viewHolder.txtSongName.setText(song.getTenbaihat());
        viewHolder.txtIndex.setText(String.valueOf(i + 1));
        viewHolder.bind(songArrayList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtIndex, txtSongName, txtSinger;
        ImageView imgLove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIndex = binding.singleSongListItemTextListIndex;
            txtSinger = binding.singleSongListItemTextSimgerName;
            txtSongName = binding.singleSongListItemTextSongName;
            imgLove = binding.singleSongListItemImgLove;
            imgLove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgLove.setImageResource(R.drawable.iconloved);
                    WebhostService webhostService = WebhostServiceAPI.getService();
                    final Call<String> callback = webhostService.UpdateLuotThich("1", songArrayList.get(getAdapterPosition()).getIdbaihat());
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
