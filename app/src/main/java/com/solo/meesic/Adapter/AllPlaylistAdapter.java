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

import com.solo.meesic.Activity.SongListActivity;
import com.solo.meesic.Model.Playlist;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleAllPlaylistContentBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AllPlaylistAdapter extends RecyclerView.Adapter<AllPlaylistAdapter.ViewHolder> {
    private SingleAllPlaylistContentBinding binding;
    private Context context;
    private List<Playlist> playlistList;
    private View view;
    public AllPlaylistAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_all_playlist_content, viewGroup, false);

        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist = playlistList.get(i);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(viewHolder.imgPlaylistImage);
        viewHolder.txtPlaylistName.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPlaylistImage;
        TextView txtPlaylistName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPlaylistImage = binding.singleAllPlaylistContentImgPlaylists;
            txtPlaylistName = binding.singleAllPlaylistContentTextPlaylistName;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("itemplaylist", playlistList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
