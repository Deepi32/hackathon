package com.example.myapplication5.ui.notifications;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.FileDetails;
import com.example.myapplication5.model.VisaDetails;

public class NotificationsViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<VisaDetails> visaDetails;
    private MutableLiveData<FileDetails> idFile;
    private MutableLiveData<FileDetails> photoFile;

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        visaDetails = new MutableLiveData<>();
        idFile = new MutableLiveData<>();
        photoFile = new MutableLiveData<>();
        //mText.setValue("This is notifications fragment");
        visaDetails.setValue(new VisaDetails(false, false));
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setLogs(String log) {
        Log.d("NotificationsViewModel", "setLogs, " + log);
    }

    public MutableLiveData<VisaDetails> getVisaDetails() {
        return visaDetails;
    }

    public void setVisaDetails(VisaDetails visaDetails) {
        this.visaDetails.setValue(visaDetails);
    }

    public void setVisaOnArrival(boolean visaOnArrival) {
        VisaDetails oldDetails = this.visaDetails.getValue();
        if (oldDetails == null) {
            this.visaDetails.setValue(new VisaDetails(visaOnArrival, false, null));
        } else {
            this.visaDetails.setValue(new VisaDetails(visaOnArrival, false,
                    oldDetails.getVisaDocument()));
        }
    }

    public void setVisaIssued(boolean visaIssued) {
        VisaDetails oldDetails = this.visaDetails.getValue();
        if (oldDetails == null) {
            this.visaDetails.setValue(new VisaDetails(false, visaIssued, null));
        } else {
            this.visaDetails.setValue(new VisaDetails(false, visaIssued,
                    oldDetails.getVisaDocument()));
        }
    }

    public void setVisaDocument(FileDetails visaDocument) {
        VisaDetails oldDetails = this.visaDetails.getValue();
        if (oldDetails == null) {
            this.visaDetails.setValue(new VisaDetails(false, false, visaDocument));
        } else {
            this.visaDetails.setValue(new VisaDetails(oldDetails.isVisaOnArrival(),
                    oldDetails.isVisaIssued(), visaDocument));
        }
    }

    public MutableLiveData<FileDetails> getIdFile() {
        return idFile;
    }

    public void setIdFile(FileDetails idFile) {
        this.idFile.setValue(idFile);
    }

    public MutableLiveData<FileDetails> getPhotoFile() {
        return photoFile;
    }

    public void setPhotoFile(FileDetails photoFile) {
        this.photoFile.setValue(photoFile);
    }
}