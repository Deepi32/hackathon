package com.example.myapplication5.ui.payment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.BookingDetails;
import com.example.myapplication5.model.SimDetails;

public class PaymentViewModel extends ViewModel {

    private MutableLiveData<String> payResult;
    private MutableLiveData<SimDetails> simDetails;
    private MutableLiveData<Boolean> isPaymentDone;
    private MutableLiveData<BookingDetails> bookingDetails;

    public PaymentViewModel() {
        payResult = new MutableLiveData<>();
        simDetails = new MutableLiveData<>();
        isPaymentDone = new MutableLiveData<>();
        bookingDetails = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public MutableLiveData<String> getPayResult() {
        return payResult;
    }

    public void setPayResult(String payResult) {
        this.payResult.setValue(payResult);
    }

    public MutableLiveData<SimDetails> getSimDetails() {
        return simDetails;
    }

    public void setSimDetails(SimDetails simDetails) {
        this.simDetails.setValue(simDetails);
    }

    public MutableLiveData<Boolean> getIsPaymentDone() {
        return isPaymentDone;
    }

    public void setIsPaymentDone(boolean isPaymentDone) {
        this.isPaymentDone.setValue(isPaymentDone);
    }

    public MutableLiveData<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails.setValue(bookingDetails);
    }
}