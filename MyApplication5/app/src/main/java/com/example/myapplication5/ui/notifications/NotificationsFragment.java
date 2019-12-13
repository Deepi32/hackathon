package com.example.myapplication5.ui.notifications;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication5.R;
import com.example.myapplication5.model.FileDetails;
import com.example.myapplication5.model.VisaDetails;
import com.example.myapplication5.utils.Consts;
import com.example.myapplication5.utils.Utils;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.upload_documents);
        notificationsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final CheckBox visaOnArrivalView = root.findViewById(R.id.visa_on_arrival);
        final CheckBox visaIssuedView = root.findViewById(R.id.visa_issued);
        final TextView uploadVisaView = root.findViewById(R.id.upload_visa);

        visaOnArrivalView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                Log.d("NottiFrag", "VisaOnArrival, checked: " + checked);
                /*visaIssuedView.setChecked(!checked);
                uploadVisaView.setVisibility(checked ? View.GONE : View.VISIBLE);*/
                notificationsViewModel.setVisaOnArrival(checked);
            }
        });

        visaIssuedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox)v).isChecked();
                Log.d("NottiFrag", "VisaIssued, checked: " + checked);
                /*visaOnArrivalView.setChecked(!checked);
                uploadVisaView.setVisibility(checked ? View.VISIBLE : View.GONE);*/
                notificationsViewModel.setVisaIssued(checked);
            }
        });

        uploadVisaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult(Utils.openFile(), Consts.ACTIVITY_RESULT_PICK_VISA_FILE);
                startActivityForResult(Utils.showFileChooser("Select a File to Upload"),
                        Consts.ACTIVITY_RESULT_PICK_VISA_FILE);
            }
        });

        notificationsViewModel.getVisaDetails().observe(this, new Observer<VisaDetails>() {
            @Override
            public void onChanged(@Nullable VisaDetails visa) {
                //textView.setText(s);
                if (visa != null) {
                    visaOnArrivalView.setChecked(visa.isVisaOnArrival());
                    visaIssuedView.setChecked(visa.isVisaIssued());
                    uploadVisaView.setText(
                            visa.getVisaDocument() != null ? visa.getVisaDocument().getName() : "");
                    uploadVisaView.setVisibility(visa.isVisaIssued() ? View.VISIBLE : View.GONE);
                }
            }
        });

        final TextView uploadIdView = root.findViewById(R.id.upload_id);
        uploadIdView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult(Utils.openFile(), Consts.ACTIVITY_RESULT_PICK_VISA_FILE);
                startActivityForResult(Utils.showFileChooser("Select a File to Upload"),
                        Consts.ACTIVITY_RESULT_PICK_ID_FILE);
            }
        });
        notificationsViewModel.getIdFile().observe(this, new Observer<FileDetails>() {
            @Override
            public void onChanged(@Nullable FileDetails file) {
                //textView.setText(s);
                if (file != null) {
                    uploadIdView.setText(file.getName());
                }
            }
        });

        final TextView uploadPhotoView = root.findViewById(R.id.upload_picture);
        uploadPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivityForResult(Utils.openFile(), Consts.ACTIVITY_RESULT_PICK_VISA_FILE);
                startActivityForResult(Utils.showFileChooser("Select a File to Upload"),
                        Consts.ACTIVITY_RESULT_PICK_PHOTO_FILE);
            }
        });
        notificationsViewModel.getPhotoFile().observe(this, new Observer<FileDetails>() {
            @Override
            public void onChanged(@Nullable FileDetails file) {
                //textView.setText(s);
                if (file != null) {
                    uploadPhotoView.setText(file.getName());
                }
            }
        });

        return root;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("GalleyFrag", "onActivityResult, req: " + requestCode
                + ", result: " + resultCode + ", data: " + (data == null ? null : data.getData()));
        switch (requestCode) {
            case Consts.ACTIVITY_RESULT_CREATE_FILE:
                // Create File
                break;
            case Consts.ACTIVITY_RESULT_PICK_FOLDER:
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = null;
                    if (data != null) {
                        uri = data.getData();
                        // Perform operations on the document using its URI.
                    }
                    if (uri != null) {
                        FileDetails file = new FileDetails(uri, null, uri.toString(), 0);
                        //notificationsViewModel.getText().setmCurrentDir(file);
                    }
                }
                // Access folder
                break;
            case Consts.ACTIVITY_RESULT_PICK_VISA_FILE:
                // Open File
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = null;
                    if (data != null) {
                        uri = data.getData();
                        // Perform operations on the document using its URI.
                    }
                    if (uri != null) {
                        //Utils.getPath(getContext(), uri);
                        FileDetails visaFile = Utils.getFileDetails(getContext(), uri);
                        notificationsViewModel.setVisaDocument(visaFile);
                    }
                }
                break;
            case Consts.ACTIVITY_RESULT_PICK_ID_FILE:
                // Open File
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = null;
                    if (data != null) {
                        uri = data.getData();
                        // Perform operations on the document using its URI.
                    }
                    if (uri != null) {
                        //Utils.getPath(getContext(), uri);
                        FileDetails idFile = Utils.getFileDetails(getContext(), uri);
                        notificationsViewModel.setIdFile(idFile);
                    }
                }
                break;
            case Consts.ACTIVITY_RESULT_PICK_PHOTO_FILE:
                // Open File
                if (resultCode == Activity.RESULT_OK) {
                    Uri uri = null;
                    if (data != null) {
                        uri = data.getData();
                        // Perform operations on the document using its URI.
                    }
                    if (uri != null) {
                        //Utils.getPath(getContext(), uri);
                        FileDetails photoFile = Utils.getFileDetails(getContext(), uri);
                        notificationsViewModel.setPhotoFile(photoFile);
                    }
                }
                break;
            default:
                break;
        }
    }
}