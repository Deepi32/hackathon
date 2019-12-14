package com.example.myapplication5.ui.payment;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication5.MainActivity;
import com.example.myapplication5.R;
import com.example.myapplication5.model.BookingDetails;
import com.example.myapplication5.model.SimDetails;
import com.example.myapplication5.ui.home.HomeViewModel;
import com.example.myapplication5.utils.Consts;

import org.json.JSONException;
import org.json.JSONObject;

public class PaymentFragment extends Fragment {

    private PaymentViewModel paymentViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        paymentViewModel =
                ViewModelProviders.of(this).get(PaymentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_payment, container, false);

        final TextView payResult = root.findViewById(R.id.pay_result);
        paymentViewModel.getPayResult().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                payResult.setText(s);
            }
        });

        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity) {
            SimDetails simDetails = ((MainActivity) activity).getSimDetails();
            if (simDetails != null) {
                paymentViewModel.setSimDetails(simDetails);
            }
            BookingDetails bookingDetails = ((MainActivity) activity).getBookingDetails();
            if (bookingDetails != null) {
                paymentViewModel.setBookingDetails(bookingDetails);
            }
        }

        final Button pay = root.findViewById(R.id.pay_button);
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pay
                BookingDetails booking = paymentViewModel.getBookingDetails().getValue();
                if (booking != null && booking.getUserDetails() != null) {
                    int userId = booking.getUserDetails().getId();
                    getData(Consts.URL_ADDRESS + Consts.URL_PAYMENT + userId);
                }
            }
        });

        final TextView payHeader = root.findViewById(R.id.pay_header);
        final Button submit = root.findViewById(R.id.submit);

        paymentViewModel.getSimDetails().observe(this, new Observer<SimDetails>() {
            @Override
            public void onChanged(@Nullable SimDetails simDetails) {
                if (simDetails != null) {
                    String payHeaderString = getText(R.string.pay_amount) + simDetails.getPrice();
                    payHeader.setText(payHeaderString);
                    pay.setVisibility(View.VISIBLE);
                } else {
                    pay.setVisibility(View.INVISIBLE);
                }
                submit.setEnabled(false);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeFrag", "OnClick, submit");
                Activity activity = getActivity();
                if (activity != null && activity instanceof MainActivity) {
                    ((MainActivity) activity).savePaymentDetails(
                            paymentViewModel.getIsPaymentDone().getValue(),
                            paymentViewModel.getPayResult().getValue());
                    ((MainActivity) activity).switchToNextTab(R.id.navigation_payment);
                }
            }
        });

        paymentViewModel.getIsPaymentDone().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean isPaymentDone) {
                if (isPaymentDone) {
                    pay.setEnabled(false);
                    submit.setEnabled(true);
                }
            }
        });
        return root;
    }

    private void getData(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("HomeFragment", "getData, onResponse : " + response);
                        //homeViewModel.setText("response: " + response);
                        //hiding the progressbar after completion
                        //progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("status") == 200) {
                                paymentViewModel.setPayResult(obj.getString("message"));
                                paymentViewModel.setIsPaymentDone(true);
                            }

                            /*JSONObject data = obj.getJSONObject("data");
                            BookingDetails booking = new BookingDetails();
                            booking.initFrom(data);
                            homeViewModel.setBookingDetails(booking);
                            */

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            /*JSONArray heroArray = obj.getJSONArray("heroes");

                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);

                                //creating a hero object and giving them the values from json object
                                Hero hero = new Hero(heroObject.getString("name"), heroObject.getString("imageurl"));

                                //adding the hero to herolist
                                heroList.add(hero);
                            }

                            //creating custom adapter object
                            ListViewAdapter adapter = new ListViewAdapter(heroList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);*/

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurs
                        Toast.makeText(PaymentFragment.this.getContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }
}