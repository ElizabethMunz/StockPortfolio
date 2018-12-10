package edu.temple.munz.stockportfolio;

import org.json.JSONObject;

public class Stock {
    private String name, symbol;
    private double lastPrice, change;

    public Stock(String name, String symbol, double lastPrice, double change) {
        this.name = name;
        this.symbol = symbol;
        this.lastPrice = lastPrice;
        this.change = change;
    }

    public Stock(JSONObject obj) {
        try {
            this.name = obj.getString("Name");
            this.symbol = obj.getString("Symbol");
            this.lastPrice = obj.getDouble("LastPrice");
            this.change = obj.getDouble("Change");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public double getLastPrice() {
        return lastPrice;
    }

    public void setLastPrice(double lastPrice) {
        this.lastPrice = lastPrice;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }
}
