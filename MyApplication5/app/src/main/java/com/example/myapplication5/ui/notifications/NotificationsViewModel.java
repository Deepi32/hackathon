package com.example.myapplication5.ui.notifications;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.VisaDetails;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<VisaDetails> visaDetails;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        //mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}