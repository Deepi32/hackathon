package com.example.myapplication5.ui.home;

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

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
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
import com.example.myapplication5.utils.Consts;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.error);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        final EditText editText = root.findViewById(R.id.booking_id);
        final Button checkBooking = root.findViewById(R.id.check_booking);
        checkBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookingId = editText.getText().toString();
                if (bookingId.isEmpty()) {
                    homeViewModel.setText("Please enter valid booking id");
                    return;
                }
                getData(Consts.URL_ADDRESS + Consts.URL_BOOKING_ID + bookingId);
            }
        });
        homeViewModel.getBookingDetails().observe(this, new Observer<BookingDetails>() {
            @Override
            public void onChanged(@Nullable BookingDetails booking) {
                if (booking != null) {
                    homeViewModel.setText(booking.toString());
                }
            }
        });

        final Button submit = root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeFrag", "OnClick, submit");
                Activity activity = getActivity();
                if (activity != null && activity instanceof MainActivity) {
                    ((MainActivity) activity).saveBookingData(
                            homeViewModel.getBookingDetails().getValue());
                    ((MainActivity) activity).switchToNextTab(R.id.navigation_home);
                }

                /*String bookingId = editText.getText().toString();
                if (bookingId.isEmpty()) {
                    homeViewModel.setText("Please enter valid booking id");
                    return;
                }
                getData(Consts.SERVER_ADDRESS_EMULATOR + "flight?pnrNo=" + bookingId);*/
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
                        homeViewModel.setText("response: " + response);
                        //hiding the progressbar after completion
                        //progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);
                            if (obj.getInt("status") != 200) {
                                homeViewModel.setText("Error: " + obj.getString("message"));
                                return;
                            }

                            JSONObject data = obj.getJSONObject("data");
                            BookingDetails booking = new BookingDetails();
                            booking.initFrom(data);
                            homeViewModel.setBookingDetails(booking);
                            Log.d("HomeFragment", "getData, booking: "
                                    + booking.toString());

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
                        Toast.makeText(HomeFragment.this.getContext(),
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