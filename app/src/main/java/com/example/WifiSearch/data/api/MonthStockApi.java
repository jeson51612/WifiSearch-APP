package com.example.WifiSearch.data.api;

import com.example.WifiSearch.data.model.MonthStockModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MonthStockApi {
    @GET("STOCK_DAY?response=json")
    Call<MonthStockModel> CallMonthStock(
            @Query("date") String History_Day,
            @Query("stockNo") String Code
    );
}
