package com.example.myapplication5.model;

import org.json.JSONException;
import org.json.JSONObject;

public class SimDetails extends JsonClass {

    private int id;
    private String networkProvider;
    private String dataBand;
    private String price;
    private String callingTime;
    private String dataLimit;
    private String validity;
    private String packageDetails;
    private boolean isDataAvailable;

    public SimDetails() {}

    public SimDetails(int id, String networkProvider, String dataBand, String price,
                      String callingTime, String dataLimit, String validity, String packageDetails,
                      boolean isDataAvailable) {
        this.id = id;
        this.networkProvider = networkProvider;
        this.dataBand = dataBand;
        this.price = price;
        this.callingTime = callingTime;
        this.dataLimit = dataLimit;
        this.validity = validity;
        this.packageDetails = packageDetails;
        this.isDataAvailable = isDataAvailable;
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

    public String getPackageDetails() {
        return packageDetails;
    }

    public void setPackageDetails(String packageDetails) {
        this.packageDetails = packageDetails;
    }

    public boolean isDataAvailable() {
        return isDataAvailable;
    }

    public void setDataAvailable(boolean dataAvailable) {
        isDataAvailable = dataAvailable;
    }

    public boolean isEmpty() {
        return (networkProvider == null) || networkProvider.isEmpty()
                && (price == null || price.isEmpty())
                && (validity == null || validity.isEmpty());
    }

    public void initFrom(JSONObject object) throws JSONException {
        setId(object.getInt("id"));
        setNetworkProvider(object.getString("operators"));
        setValidity(object.getString("numberOfDays"));
        setPrice(object.getString("price"));
        setDataAvailable(object.getBoolean("isDataAvailable"));
        setDataBand(object.getString("dataSpeed"));
        setPackageDetails(object.getString("packageDetails"));
        setDataLimit(object.getString("availableDataVolume"));
    }
}
