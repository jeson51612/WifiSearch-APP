package com.example.WifiSearch.data.response;


import com.example.WifiSearch.data.model.DailyStockModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//This class is for multi stock request
public class StockResponse {

    @SerializedName("msgArray")
    @Expose
    private DailyStockModel NowStocks;

}

