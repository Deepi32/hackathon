package com.example.myapplication5.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.BookingDetails;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<BookingDetails> bookingDetails;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        bookingDetails = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText.setValue(text);
    }

    public MutableLiveData<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails.setValue(bookingDetails);
    }
}