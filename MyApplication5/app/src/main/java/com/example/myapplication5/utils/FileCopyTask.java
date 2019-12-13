package com.example.myapplication5.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import com.example.myapplication5.model.FileDetails;
import com.example.myapplication5.ui.notifications.NotificationsViewModel;

import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileCopyTask extends AsyncTask<FileDetails, String, String> {
    private static final String TAG = FileCopyTask.class.getSimpleName();
    private ProgressDialog progressDialog;
    private boolean isDownloaded;
    private NotificationsViewModel viewModel;
    private Context context;

    public FileCopyTask(Context context, NotificationsViewModel viewModel) {
        this.context = context;
        this.viewModel = viewModel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.progressDialog = new ProgressDialog(context);
        this.progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        this.progressDialog.setCancelable(false);
        this.progressDialog.show();
    }

    @Override
    protected String doInBackground(FileDetails... files) {
        if (files == null && files.length < 2) {
            return "invalid files";
        }

        FileChannel input = null;
        FileChannel out = null;
        try {
            FileDetails fromFile = files[0];
            FileDetails toFile = files[1];

            /*String pathTo = strings.length > 1 ? strings[1] : null;
            if (pathTo == null || pathTo.isEmpty()) {
                pathTo = getFilePath(path);
            }*/

            /*File srcFile = new File(path);
            File toFile = new File(pathTo);
            // getting file length
            if (!srcFile.exists()) {
                return "Source file missing";
            }
            long lengthOfFile = srcFile.length();

            // input stream to read file - with 8k buffer
            input = new FileInputStream(srcFile).getChannel();
            out = new FileOutputStream(toFile).getChannel();

            input.transferTo(0, input.size(), out);

            // closing streams
            out.close();
            input.close();
            publishProgress("100", "Changed permission for: " + pathTo + ", -> "
                    + toFile.setWritable(false, true));
            Log.d("FileCopy", "Change permission: " + toFile.setWritable(false,
                    false));
            changeFilePermissions(toFile);*/
            return "Copied at: " /*+ pathTo*/;
        /*} catch (IOException e) {
            e.printStackTrace();*/
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Something went wrong";
    }

    @Override
    protected void onProgressUpdate(String... progress) {
        super.onProgressUpdate(progress);
        progressDialog.setProgress(Integer.parseInt(progress[0]));
        if (progress.length > 1) {
            viewModel.setLogs(progress[1]);
        }
        showLogs("onProgressUpdate: " + progress.toString());
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        this.progressDialog.dismiss();
        viewModel.setLogs(s);

        // Display File path after downloading
        //Toast.makeText(context, s, Toast.LENGTH_LONG).show();
    }

    private final int CHMOD = 0x0666;
    private boolean changeFilePermissions(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        //return file.setWritable(false, false);
        String command = "chmod 0666 " + file.getPath();
        Log.d("FileCopy", " path: " + file.getPath());
        try {
            Runtime.getRuntime().exec(command, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        /*try {
            publishProgress("100", "Change permission: " + NetworkUtils.chmod(file, CHMOD));
        } catch (Exception e) {
            publishProgress("100", "Cannot changed permission: " + e.getMessage());
            return false;
        }*/
        return true;
    }

    private String getFileName(String path) {
        String fileName = ".jpg";
        if (path != null && !path.isEmpty()) {
            fileName = path.contains("/") ?
                    path.substring(path.lastIndexOf('/') + 1) : path;
        }
        String timestamp = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(new Date());
        return timestamp + "__" + fileName;
    }

    private String getFilePath(String path) {
        String fileName = getFileName(path);
        path = getTargetStorageRoot(false) + File.separator + "pankaj";
        //path = NetworkUtils.getCopyFromPath(2) + File.separator + "pankaj";
        File targetDir = new File(path);
        if (!targetDir.exists()) {
            boolean createDir = targetDir.mkdirs();
            publishProgress("0", createDir ? "Directory created: " + path
                    : "Error, can't create Dir: " + path);
        }

        return path + File.separator + fileName;
    }

    private String getTargetStorageRoot(boolean isSDCard) {

        return isSDCard ? "/storage/1209-370D"
                : Environment.getExternalStorageDirectory().toString();
    }

    private void showLogs(String logs) {
        Utils.showLogs(Utils.LOG_DEBUG, "FileCopy", logs);
    }
}
