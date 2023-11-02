package com.example.WifiSearch.data.api;

import com.example.WifiSearch.data.repository.credentials;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class Servicey {


    private static Retrofit.Builder retrofitBuilder=
            new Retrofit.Builder()
                    .baseUrl(credentials.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit=retrofitBuilder.build();

    private static StockApi stockApi=retrofit.create(StockApi.class);

    public static StockApi getStockApi(){
        return stockApi;
    }

}
