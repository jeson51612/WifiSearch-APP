package com.example.WifiSearch.data.model;

public class StockModel {
    public String Code;
    public String Name;

    public void setCode(String code) {
        Code = code;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTradeVolume(String tradeVolume) {
        TradeVolume = tradeVolume;
    }

    public void setTradeValue(String tradeValue) {
        TradeValue = tradeValue;
    }

    public void setOpeningPrice(String openingPrice) {
        OpeningPrice = openingPrice;
    }

    public void setHighestPrice(String highestPrice) {
        HighestPrice = highestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        LowestPrice = lowestPrice;
    }

    public void setClosingPrice(String closingPrice) {
        ClosingPrice = closingPrice;
    }

    public void setChange(String change) {
        Change = change;
    }

    public void setTransaction(String transaction) {
        Transaction = transaction;
    }

    public String TradeVolume;
    public String TradeValue;
    public String OpeningPrice;
    public String HighestPrice;
    public String LowestPrice;
    public String ClosingPrice;
    public String Change;

    public String getCode() {
        return Code;
    }

    public String getName() {
        return Name;
    }

    public String getTradeVolume() {
        return TradeVolume;
    }

    public String getTradeValue() {
        return TradeValue;
    }

    public String getOpeningPrice() {
        return OpeningPrice;
    }

    public String getHighestPrice() {
        return HighestPrice;
    }

    public String getLowestPrice() {
        return LowestPrice;
    }

    public String getClosingPrice() {
        return ClosingPrice;
    }

    public String getChange() {
        return Change;
    }

    public String getTransaction() {
        return Transaction;
    }

    public String Transaction;

}
