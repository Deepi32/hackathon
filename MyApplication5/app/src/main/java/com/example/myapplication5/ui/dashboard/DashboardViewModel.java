package com.example.myapplication5.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.myapplication5.model.SimDetails;

import java.util.ArrayList;

public class DashboardViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<ArrayList<SimDetails>> mSimList;
    private MutableLiveData<SimDetails> mSelectedSim;

    public DashboardViewModel() {
        mText = new MutableLiveData<>();
        mSimList = new MutableLiveData<>();
        mSelectedSim = new MutableLiveData<>();
        //mText.setValue("This is dashboard fragment");
        //addDummyData();
        mSelectedSim.setValue(null);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void setText(String text) {
        this.mText.setValue(text);
    }

    public MutableLiveData<ArrayList<SimDetails>> getSimList() {
        return mSimList;
    }

    public void setSimList(ArrayList<SimDetails> simList) {
        this.mSimList.setValue(simList);
    }

    public MutableLiveData<SimDetails> getSelectedSim() {
        return mSelectedSim;
    }

    public void setSelectedSim(SimDetails selectedSim) {
        this.mSelectedSim.setValue(selectedSim);
    }

    private void addDummyData() {
        ArrayList<SimDetails> dummyData = new ArrayList<>();
        dummyData.add(new SimDetails(1, "Airtel", "3G", "Rs 250/-",
                "Unlimited", "5GB", "1 month", "High data package",
                true));
        dummyData.add(new SimDetails(2, "Vodafone", "4G", "Rs 250/-",
                "Unlimited", "3GB", "1 month", "Average data package",
                true));
        setSimList(dummyData);
    }
}