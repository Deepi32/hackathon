package com.example.myapplication5.model;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

public class BookingDetails extends JsonClass {

    private int id;
    private String bookingId;
    private String passengerName;
    private String bookingFrom;
    private String bookingTo;
    private String startTime;
    private String endTime;
    private String destinationCountry;
    private String departureCountry;
    private String userId;
    private UserDetails userDetails;

    public BookingDetails() {}

    public BookingDetails(int id, String bookingId, String passengerName, String bookingFrom,
                          String bookingTo, String startTime, String endTime,
                          String destinationCountry, String departureCountry, UserDetails userDetails) {
        this.id = id;
        this.bookingId = bookingId;
        this.passengerName = passengerName;
        this.bookingFrom = bookingFrom;
        this.bookingTo = bookingTo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.destinationCountry = destinationCountry;
        this.departureCountry = departureCountry;
        this.userDetails = userDetails;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getBookingFrom() {
        return bookingFrom;
    }

    public void setBookingFrom(String bookingFrom) {
        this.bookingFrom = bookingFrom;
    }

    public String getBookingTo() {
        return bookingTo;
    }

    public void setBookingTo(String bookingTo) {
        this.bookingTo = bookingTo;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(String destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public String getDepartureCountry() {
        return departureCountry;
    }

    public void setDepartureCountry(String departureCountry) {
        this.departureCountry = departureCountry;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void initFrom(JSONObject object) throws JSONException {
        if (object == null) return;
        //setId(object.getInt("id"));
        setBookingId(object.getString("bookingId"));
        setBookingFrom(object.getString("destinationFrom"));
        setBookingTo(object.getString("destinationTo"));
        setStartTime(object.getString("startTime"));
        setEndTime(object.getString("endTime"));
        setDestinationCountry(object.getString("countryTo"));
        setDepartureCountry(object.getString("countryFrom"));
        setUserId(object.getString("userId"));
        /*UserDetails userDetails = new UserDetails();
        userDetails.initFrom(object.getJSONObject("user"));
        setUserDetails(userDetails);*/
    }

    @NonNull
    @Override
    public String toString() {
        String sTime = getStartTime() != null && !getStartTime().isEmpty() ? getStartTime() : "";
        String eTime = getEndTime() != null && !getEndTime().isEmpty() ? getEndTime() : "";
        String userInfo = getUserDetails() != null ? getUserDetails().toString() + "\n" : "";
        return userInfo + "PNR: " + getBookingId() + " / " + getBookingFrom() /*+ "(" + sTime
                + ")"*/ + " - " + getBookingTo() /*+ "(" + eTime + ")"*/;
    }
}
