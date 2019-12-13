package com.example.myapplication5.model;

import android.net.Uri;

import androidx.annotation.NonNull;

public class FileDetails {
    private Uri uri;
    private String filePath;
    private String name;
    private long size;
    private String type;
    private boolean isDirectory;

    public FileDetails(Uri uri, String filePath, String name, long size) {
        this(uri, filePath, name, size, "");
    }

    public FileDetails(Uri uri, String filePath, String name, long size, String type) {
        this(uri, filePath, name, size, type, false);
    }

    public FileDetails(Uri uri, String filePath, String name, long size, String type, boolean isDirectory) {
        this.uri = uri;
        this.name = name;
        this.size = size;
        this.type = type;
        this.isDirectory = isDirectory;
    }

    public Uri getUri() {
        return uri;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getName() {
        return name;
    }

    public long getSize() {
        return size;
    }

    public String getType() {
        return type;
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    @NonNull
    @Override
    public String toString() {
        return "\n\tName: " + name + "\n\turi: " + uri + "\n\tpath: " + filePath + "\n\tsize: "
                + size + "\n\ttype: " + type + "\n\tisDirectory: " + isDirectory;
    }
}
