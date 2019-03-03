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

import com.solo.meesic.Model.AlbumHot;
import com.solo.meesic.R;
import com.solo.meesic.databinding.SingleAlbumHotContentBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AlbumHotAdapter extends RecyclerView.Adapter<AlbumHotAdapter.ViewHoler> {
    SingleAlbumHotContentBinding binding;
    private Context context;
    private View view;
    ArrayList<AlbumHot> albumHotArrayList;

    public AlbumHotAdapter(Context context, ArrayList<AlbumHot> albumHotArrayList) {
        this.context = context;
        this.albumHotArrayList = albumHotArrayList;
    }

    @NonNull
    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(context);
        binding = DataBindingUtil.inflate(inflater, R.layout.single_album_hot_content, viewGroup, false);
        view = binding.getRoot();
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler viewHoler, int i) {
        AlbumHot albumHot = albumHotArrayList.get(i);
        viewHoler.txtSingerName.setText(albumHot.getTencasiAlbum());
        viewHoler.txtAlbumName.setText(albumHot.getTenAlbum());
        Picasso.with(context).load(albumHot.getHinhanhAlbum()).into(viewHoler.imgAlbumImage);
    }

    @Override
    public int getItemCount() {
        return albumHotArrayList.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder {
        ImageView imgAlbumImage;
        TextView txtAlbumName, txtSingerName;

        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            imgAlbumImage = binding.singleAlbumHotImageAlbum;
            txtAlbumName = binding.singleAlbumHotTextAlbumName;
            txtSingerName = binding.singleAlbumHotTextSingerName;
        }
    }

}
