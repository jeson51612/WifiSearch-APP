package com.example.WifiSearch.data.api;

import com.example.WifiSearch.data.model.DailyStockModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NowStockApi {
    @GET("getStockInfo.jsp")
    Call<DailyStockModel> CallNowStock(
    @Query("ex_ch") String Code
    );
}
