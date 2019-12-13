package com.example.myapplication5.utils;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;

import com.example.myapplication5.utils.Consts;

public class DownloadIntentService extends IntentService {
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DownloadIntentService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void copyFile() {
//        getContentResolver().
    }
    private void getDataFromNet(Intent intent) {
        String url = intent.getStringExtra(Consts.URL_ADDRESS);


        int viewModel = intent.getIntExtra(Consts.VIEW_MODEL_CLASS, -1);
        switch (viewModel) {
            case Consts.VIEW_MODEL_HOME:
                break;
            case Consts.VIEW_MODEL_DASHBOARD:
                break;
            case Consts.VIEW_MODEL_NOTIFICATION:
                break;
            default:
                break;
        }
    }

    private String getData(String address) {
        String data = null;


        return data;
    }

}
