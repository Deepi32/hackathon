package com.example.myapplication5.utils;

import android.os.AsyncTask;

public class DownloadTask extends AsyncTask<String,String, String> {
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onProgressUpdate(String... strings) {
        super.onProgressUpdate(strings);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onPostExecute(String string) {
        super.onPostExecute(string);
    }
}
