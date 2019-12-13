package com.example.myapplication5.model;

public class BookingDetails extends JsonClass {

    private int id;
    private String passengerName;
    private String bookingFrom;
    private String bookingTo;
    private String startTime;
    private String endTime;
    private String destinationCountry;

    public BookingDetails() {}

    public BookingDetails(int id, String passengerName, String bookingFrom, String bookingTo,
                          String startTime, String endTime, String destinationCountry) {
        this.id = id;
        this.passengerName = passengerName;
        this.bookingFrom = bookingFrom;
        this.bookingTo = bookingTo;
        this.startTime = startTime;
        this.endTime = endTime;
        this.destinationCountry = destinationCountry;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
