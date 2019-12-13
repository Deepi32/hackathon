package com.example.myapplication5.model;

import android.provider.DocumentsProvider;

import androidx.documentfile.provider.DocumentFile;

public class VisaDetails extends JsonClass {
    private boolean isVisaOnArrival;
    private boolean isVisaIssued;
    private FileDetails visaDocument;

    public VisaDetails() {

    }

    public VisaDetails(boolean isVisaOnArrival, boolean isVisaIssued) {
        this.isVisaOnArrival = isVisaOnArrival;
        this.isVisaIssued = isVisaIssued;
    }

    public VisaDetails(boolean isVisaOnArrival, boolean isVisaIssued, FileDetails visaDocument) {
        this.isVisaOnArrival = isVisaOnArrival;
        this.isVisaIssued = isVisaIssued;
        this.visaDocument = visaDocument;
    }

    public boolean isVisaOnArrival() {
        return isVisaOnArrival;
    }

    public void setVisaOnArrival(boolean visaOnArrival) {
        isVisaOnArrival = visaOnArrival;
        if (visaOnArrival) {
            isVisaIssued = false;
        }
    }

    public boolean isVisaIssued() {
        return isVisaIssued;
    }

    public void setVisaIssued(boolean visaIssued) {
        isVisaIssued = visaIssued;
        if (visaIssued) {
            isVisaOnArrival = false;
        }
    }

    public FileDetails getVisaDocument() {
        return visaDocument;
    }

    public void setVisaDocument(FileDetails visaDocument) {
        this.visaDocument = visaDocument;
    }
}
