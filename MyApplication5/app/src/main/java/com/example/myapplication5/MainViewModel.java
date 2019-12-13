package com.example.myapplication5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.BookingDetails;
import com.example.myapplication5.model.SimDetails;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> bookingId;
    private MutableLiveData<BookingDetails> bookingdetails;
    private MutableLiveData<SimDetails> selectedSim;

    public MainViewModel() {
        bookingId = new MutableLiveData<>();
        bookingdetails = new MutableLiveData<>();
        selectedSim = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public MutableLiveData<String> getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId.setValue(bookingId);
    }

    public MutableLiveData<BookingDetails> getBookingdetails() {
        return bookingdetails;
    }

    public void setBookingdetails(BookingDetails bookingdetails) {
        this.bookingdetails.setValue(bookingdetails);
    }

    public MutableLiveData<SimDetails> getSelectedSim() {
        return selectedSim;
    }

    public void setSelectedSim(SimDetails selectedSim) {
        this.selectedSim.setValue(selectedSim);
    }
}