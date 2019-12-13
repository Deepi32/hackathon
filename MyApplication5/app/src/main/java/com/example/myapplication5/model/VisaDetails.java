package com.example.myapplication5.model;

import android.provider.DocumentsProvider;

import androidx.documentfile.provider.DocumentFile;

public class VisaDetails extends JsonClass {
    private boolean isVisaOnArrival;
    private boolean isVisaIssued;
    private DocumentsProvider visaDocument1;
    private DocumentFile visaDocument2;

    public VisaDetails() {

    }

    public VisaDetails(boolean isVisaOnArrival, boolean isVisaIssued,
                       DocumentsProvider visaDocument1, DocumentFile visaDocument2) {
        this.isVisaOnArrival = isVisaOnArrival;
        this.isVisaIssued = isVisaIssued;
        this.visaDocument1 = visaDocument1;
        this.visaDocument2 = visaDocument2;
    }

    public boolean isVisaOnArrival() {
        return isVisaOnArrival;
    }

    public void setVisaOnArrival(boolean visaOnArrival) {
        isVisaOnArrival = visaOnArrival;
    }

    public boolean isVisaIssued() {
        return isVisaIssued;
    }

    public void setVisaIssued(boolean visaIssued) {
        isVisaIssued = visaIssued;
    }

    public DocumentsProvider getVisaDocument1() {
        return visaDocument1;
    }

    public void setVisaDocument1(DocumentsProvider visaDocument1) {
        this.visaDocument1 = visaDocument1;
    }

    public DocumentFile getVisaDocument2() {
        return visaDocument2;
    }

    public void setVisaDocument2(DocumentFile visaDocument2) {
        this.visaDocument2 = visaDocument2;
    }
}
