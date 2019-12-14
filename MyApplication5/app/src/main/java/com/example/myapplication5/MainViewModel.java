package com.example.myapplication5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.BookingDetails;
import com.example.myapplication5.model.FileDetails;
import com.example.myapplication5.model.SimDetails;
import com.example.myapplication5.model.VisaDetails;

public class MainViewModel extends ViewModel {

    private MutableLiveData<String> bookingId;
    private MutableLiveData<BookingDetails> bookingDetails;
    private MutableLiveData<SimDetails> selectedSim;
    private MutableLiveData<VisaDetails> visaDetails;
    private MutableLiveData<FileDetails> idFile;
    private MutableLiveData<FileDetails> photoFile;

    public MainViewModel() {
        bookingId = new MutableLiveData<>();
        bookingDetails = new MutableLiveData<>();
        selectedSim = new MutableLiveData<>();
        visaDetails = new MutableLiveData<>();
        idFile = new MutableLiveData<>();
        photoFile = new MutableLiveData<>();
        //mText.setValue("This is home fragment");
    }

    public MutableLiveData<String> getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId.setValue(bookingId);
    }

    public MutableLiveData<BookingDetails> getBookingDetails() {
        return bookingDetails;
    }

    public void setBookingDetails(BookingDetails bookingDetails) {
        this.bookingDetails.setValue(bookingDetails);
    }

    public MutableLiveData<SimDetails> getSelectedSim() {
        return selectedSim;
    }

    public void setSelectedSim(SimDetails selectedSim) {
        this.selectedSim.setValue(selectedSim);
    }

    public MutableLiveData<VisaDetails> getVisaDetails() {
        return visaDetails;
    }

    public void setVisaDetails(VisaDetails visaDetails) {
        this.visaDetails.setValue(visaDetails);
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