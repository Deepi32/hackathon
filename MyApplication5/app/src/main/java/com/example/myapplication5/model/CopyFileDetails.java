package com.example.myapplication5.model;

public class CopyFileDetails {
    private FileDetails fromFile;
    private FileDetails toFile;

    public CopyFileDetails() {}

    public FileDetails getFromFile() {
        return fromFile;
    }

    public void setFromFile(FileDetails fromFile) {
        this.fromFile = fromFile;
    }

    public FileDetails getToFile() {
        return toFile;
    }

    public void setToFile(FileDetails toFile) {
        this.toFile = toFile;
    }
}
