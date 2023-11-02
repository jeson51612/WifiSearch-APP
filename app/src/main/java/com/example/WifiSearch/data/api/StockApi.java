package com.example.WifiSearch.data.api;

import com.example.WifiSearch.data.model.StockModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface StockApi {

    @GET("v1/exchangeReport/STOCK_DAY_ALL")
    Call<List<StockModel>> CallStock(

    );

}
