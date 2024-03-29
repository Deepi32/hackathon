package com.example.myapplication5.ui.summary;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.BookingDetails;

public class SummaryViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> response;
    private MutableLiveData<BookingDetails> bookingDetails;

    public SummaryViewModel() {
        mText = new MutableLiveData<>();
        response = new MutableLiveData<>();
        bookingDetails = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText.setValue(text);
    }

    public MutableLiveData<String> getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response.setValue(response);
    }

    public MutableLiveData<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails.setValue(bookingDetails);
    }
}