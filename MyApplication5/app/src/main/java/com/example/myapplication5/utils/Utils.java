package com.example.myapplication5.utils;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.DocumentsContract;
import android.util.Log;

import androidx.documentfile.provider.DocumentFile;

import com.example.myapplication5.model.CopyFileDetails;
import com.example.myapplication5.model.FileDetails;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URISyntaxException;

public class Utils {

    public static final int LOG_DEBUG = 0;
    public static final int LOG_ERROR = 1;
    public static final int LOG_INFO = 2;
    public static final int LOG_WARNING = 3;
    public static final int LOG_VERBOSE = 4;

    public static void showLogs(String logs) {
        showLogs(LOG_DEBUG, "Utils", logs);
    }
    public static void showLogs(int type, String tag, String log) {
        switch (type) {
            case LOG_DEBUG:
                Log.d(tag, log);
                break;
            case LOG_ERROR:
                Log.e(tag, log);
                break;
            case LOG_INFO:
                Log.i(tag, log);
                break;
            case LOG_WARNING:
                Log.w(tag, log);
                break;
            case LOG_VERBOSE:
                Log.v(tag, log);
                break;
        }
    }

    public static Intent openFile() {
        return openFile(null, null);
    }

    public static Intent openFile(Uri pickerInitialUri) {
        return openFile(pickerInitialUri, null);
    }

    public static Intent openFile(Uri pickerInitialUri, String type) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        if (type != null) {
            //intent.setType("application/pdf");
            intent.setType(type);
        }

        // Optionally, specify a URI for the file that should appear in the
        // system file picker when it loads.
        if (pickerInitialUri != null)
            intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri);

        //startActivityForResult(intent, PICK_PDF_FILE);
        return intent;
    }

    public static Intent showFileChooser(String chooserTitle) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        return Intent.createChooser(intent, chooserTitle);

        /*try {
            startActivityForResult(
                    Intent.createChooser(intent, "Select a File to Upload"),
                    FILE_SELECT_CODE);
        } catch (android.content.ActivityNotFoundException ex) {
            // Potentially direct the user to the Market with a Dialog
            Toast.makeText(this, "Please install a File Manager.",
                    Toast.LENGTH_SHORT).show();
        }*/
    }

    public static String getPath(Context context, Uri uri) {
        if (uri == null)
            return null;

        if ("content".equalsIgnoreCase(uri.getScheme())) {
            String[] projection = { "_data" };
            Cursor cursor = null;

            try {
                cursor = context.getContentResolver().query(uri, projection, null, null, null);
                int column_index_data = cursor.getColumnIndexOrThrow("_data");
                /*int column_index_document_id = cursor.getColumnIndexOrThrow("document_id");
                int column_index_mime_type = cursor.getColumnIndexOrThrow("mime_type");
                int column_index_display_name = cursor.getColumnIndexOrThrow("_display_name");
                int column_index_last_modified = cursor.getColumnIndexOrThrow("last_modified");
                int column_index_flags = cursor.getColumnIndexOrThrow("flags");
                int column_index__size = cursor.getColumnIndexOrThrow("_size");*/
                if (cursor.moveToFirst()) {
                    /*String str = "\n\t id: " + cursor.getString(column_index_document_id) +
                            "\n\t type: " + cursor.getString(column_index_mime_type) +
                            "\n\t name: " + cursor.getString(column_index_display_name) +
                            "\n\t modify: " + cursor.getString(column_index_last_modified) +
                            "\n\t flags: " + cursor.getString(column_index_flags) +
                            "\n\t size: " + cursor.getString(column_index__size);*/
                    String str = "\n\t id: " + cursor.getString(column_index_data);

                    Log.d("Utils", "getPath, > " + str);
                    //return cursor.getString(column_index_document_id);
                }
            } catch (Exception e) {
                // Eat it
            }
        }
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    public static FileDetails getFileDetails(Context context, Uri uri) {
        DocumentFile file = DocumentFile.fromSingleUri(context, uri);
        FileDetails newFile = new FileDetails(file.getUri(), null, file.getName(), file.length(),
                file.getType(), file.isDirectory());
        Log.d("Utils", "getPath, > " + newFile.toString());
        return newFile;
    }

    public static boolean saveToInternalStorage(CopyFileDetails copyFileDetails) {
        if (copyFileDetails == null || copyFileDetails.getFromFile() == null
                || copyFileDetails.getToFile() == null) {
            return false;
        }

        FileOutputStream fos = null;
        FileInputStream fin = null;
        try {
            /*File fromFile = new File(copyFileDetails.getFromFile().getUri());
            fos = new FileOutputStream(copyFileDetails.getToFile().);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();*/
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
