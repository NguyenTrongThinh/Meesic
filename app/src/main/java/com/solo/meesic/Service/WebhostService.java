package com.solo.meesic.Service;

import com.solo.meesic.Model.AlbumHot;
import com.solo.meesic.Model.DailyCategory;
import com.solo.meesic.Model.Playlist;
import com.solo.meesic.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebhostService {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();
    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();
    @GET("chudevatheloaitrongngay.php")
    Call<DailyCategory> GetCategoryMusic();
    @GET("albumhot.php")
    Call<List<AlbumHot>> GetAlbumHot();
}
