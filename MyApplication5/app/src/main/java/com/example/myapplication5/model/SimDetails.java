package com.example.myapplication5.model;

public class SimDetails extends JsonClass {

    private int id;
    private String networkProvider;
    private String dataBand;
    private String price;
    private String callingTime;
    private String dataLimit;
    private String validity;

    public SimDetails() {}

    public SimDetails(int id, String networkProvider, String dataBand, String price,
                      String callingTime, String dataLimit, String validity) {
        this.id = id;
        this.networkProvider = networkProvider;
        this.dataBand = dataBand;
        this.price = price;
        this.callingTime = callingTime;
        this.dataLimit = dataLimit;
        this.validity = validity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNetworkProvider() {
        return networkProvider;
    }

    public void setNetworkProvider(String networkProvider) {
        this.networkProvider = networkProvider;
    }

    public String getDataBand() {
        return dataBand;
    }

    public void setDataBand(String dataBand) {
        this.dataBand = dataBand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCallingTime() {
        return callingTime;
    }

    public void setCallingTime(String callingTime) {
        this.callingTime = callingTime;
    }

    public String getDataLimit() {
        return dataLimit;
    }

    public void setDataLimit(String dataLimit) {
        this.dataLimit = dataLimit;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }
}
