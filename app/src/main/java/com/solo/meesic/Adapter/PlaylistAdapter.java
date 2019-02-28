package com.solo.meesic.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.solo.meesic.Model.Playlist;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SinglePlaylistContentBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    SinglePlaylistContentBinding binding;
    public PlaylistAdapter(@NonNull Context context, int resource, @NonNull List<Playlist> objects) {
        super(context, resource, objects);
    }

    private class ViewHolder {
        TextView txtPlayListName;
        ImageView imgBackground, imgListImage;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            binding = DataBindingUtil.inflate(inflater, R.layout.single_playlist_content, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.txtPlayListName = binding.singlePlaylistContentTxtName;
            viewHolder.imgBackground = binding.singlePlaylistContentImgBackground;
            viewHolder.imgListImage = binding.singlePlaylistContentImgList;
            convertView = binding.getRoot();
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Playlist playlist = getItem(position);
        Picasso.with(getContext()).load(playlist.getHinhPlaylist()).into(viewHolder.imgBackground);
        Picasso.with(getContext()).load(playlist.getIcon()).into(viewHolder.imgListImage);
        viewHolder.txtPlayListName.setText(playlist.getTen());

        return convertView;
    }
}
