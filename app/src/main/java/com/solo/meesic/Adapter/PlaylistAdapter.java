package com.solo.meesic.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
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

public class PlaylistAdapter extends PagerAdapter {
    private Context context;
    private List<Playlist> playlistList;
    private View view;
    SinglePlaylistContentBinding binding;

    public PlaylistAdapter(Context context, List<Playlist> playlistList) {
        this.context = context;
        this.playlistList = playlistList;
    }

    @Override
    public int getCount() {
        return playlistList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    private class ViewHolder {
        TextView txtPlayListName;
        ImageView imgBackground, imgListImage;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_playlist_content, container, false);
        Picasso.with(context).load(playlistList.get(position).getHinhPlaylist()).into(binding.singlePlaylistContentImgBackground);
        Picasso.with(context).load(playlistList.get(position).getIcon()).into(binding.singlePlaylistContentImgList);
        binding.singlePlaylistContentTxtName.setText(playlistList.get(position).getTen());
        view = binding.getRoot();
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
