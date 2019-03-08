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
import com.solo.meesic.Model.AlbumHot;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleAllAlbumContentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AllAlbumAdapter extends RecyclerView.Adapter<AllAlbumAdapter.ViewHolder> {
    private SingleAllAlbumContentBinding binding;
    private Context context;
    private ArrayList<AlbumHot> albumHotArrayList;
    private View view;

    public AllAlbumAdapter(Context context, ArrayList<AlbumHot> albumHotArrayList) {
        this.context = context;
        this.albumHotArrayList = albumHotArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_all_album_content, viewGroup, false);

        view = binding.getRoot();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        AlbumHot albumHot = albumHotArrayList.get(i);
        Picasso.with(context).load(albumHot.getHinhanhAlbum()).into(viewHolder.imgAlbumImage);
        viewHolder.txtAlbumTitle.setText(albumHot.getTenAlbum());
    }

    @Override
    public int getItemCount() {
        return albumHotArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgAlbumImage;
        TextView txtAlbumTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAlbumImage = binding.singleAllPlaylistContentImgAlbum;
            txtAlbumTitle = binding.singleAllAlbumContentTextAlbumName;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SongListActivity.class);
                    intent.putExtra("album", albumHotArrayList.get(getAdapterPosition()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
