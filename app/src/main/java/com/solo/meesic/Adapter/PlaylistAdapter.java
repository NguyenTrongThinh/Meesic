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
    private OnPlaylistItemClickListener listener;
    public PlaylistAdapter(Context context, List<Playlist> playlistList, OnPlaylistItemClickListener listener) {
        this.context = context;
        this.playlistList = playlistList;
        this.listener = listener;
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
        viewHolder.bind(playlistList.get(i), listener);
    }

    @Override
    public int getItemCount() {
        return playlistList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgBackground;
        ImageView imgListImage;
        TextView txtTitle;
        Context context;
        private List<Playlist> playlistList;
        public ViewHolder(@NonNull View itemView, Context context, List<Playlist> playlists) {
            super(itemView);
            this.context = context;
            this.playlistList = playlists;
            imgListImage = binding.singlePlaylistContentImgList;
            imgBackground = binding.singlePlaylistContentImgBackground;
            txtTitle = binding.singlePlaylistContentTxtName;
        }
        public void bind(final Playlist item, final OnPlaylistItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onPlaylistItemClick(item);
                }
            });
        }
    }
}
