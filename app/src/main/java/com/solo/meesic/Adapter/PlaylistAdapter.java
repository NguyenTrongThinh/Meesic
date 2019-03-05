package com.solo.meesic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.meesic.Activity.SongListActivity;
import com.solo.meesic.Model.Playlist;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SinglePlaylistContentBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {
    private SinglePlaylistContentBinding binding;
    private Context context;
    private View view;
    private List<Playlist> playlistList;

    public PlaylistAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_playlist_content, viewGroup, false);
        view = binding.getRoot();
        return new ViewHolder(view, context, playlistList);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Playlist playlist = playlistList.get(i);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(viewHolder.imgBackground);
        Picasso.with(context).load(playlist.getIcon()).into(viewHolder.imgListImage);
        viewHolder.txtTitle.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageButton imgBackground;
        ImageView imgListImage;
        TextView txtTitle;
        Context context;
        private List<Playlist> playlistList;
        public ViewHolder(@NonNull View itemView, Context context, List<Playlist> playlists) {
            super(itemView);
            this.context = context;
            this.playlistList = playlists;
            itemView.setOnClickListener(this);
            imgListImage = binding.singlePlaylistContentImgList;
            imgBackground = binding.singlePlaylistContentImgBackground;
            txtTitle = binding.singlePlaylistContentTxtName;
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Playlist playlist = playlistList.get(position);
            Intent intent = new Intent(context, SongListActivity.class);
            intent.putExtra("intemplayist", playlist);
            context.startActivity(intent);
        }
    }
}
