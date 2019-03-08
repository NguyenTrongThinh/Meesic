package com.solo.meesic.Service;

import com.solo.meesic.Model.AlbumHot;
import com.solo.meesic.Model.ChuDe;
import com.solo.meesic.Model.DailyCategory;
import com.solo.meesic.Model.Playlist;
import com.solo.meesic.Model.QuangCao;
import com.solo.meesic.Model.Song;
import com.solo.meesic.Model.TheLoai;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebhostService {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();
    @GET("playlistforcurrentday.php")
    Call<List<Playlist>> GetPlaylistCurrentDay();
    @GET("chudevatheloaitrongngay.php")
    Call<DailyCategory> GetCategoryMusic();
    @GET("albumhot.php")
    Call<List<AlbumHot>> GetAlbumHot();
    @GET("baihatduocthich.php")
    Call<List<Song>> GetBaiHatHot();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhSachBaiHatTheoQuangCao(@Field("idquangcao") String idquangcao);
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhSachBaiHatTheoPlaylist(@Field("idplaylist") String idplaylist);
    @GET("danhsachcacplaylist.php")
    Call<List<Playlist>> GetDanhSachPlaylists();
    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhSachBaiHatTheoTheLoai(@Field("idtheloai") String idtheloai);
    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();
    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheLoaiTheoChuDe(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<AlbumHot>> GetAllAlbum();

    @FormUrlEncoded
    @POST("danhsachbaihat.php")
    Call<List<Song>> GetDanhSachBaiHatTheoAlbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);
    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Song>> GetSearchBaiHat(@Field("tukhoa") String tukhoa);
}
