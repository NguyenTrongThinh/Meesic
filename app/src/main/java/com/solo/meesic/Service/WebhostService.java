package com.solo.meesic.Service;

import com.solo.meesic.Model.QuangCao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface WebhostService {
    @GET("songbanner.php")
    Call<List<QuangCao>> GetDataBanner();

}