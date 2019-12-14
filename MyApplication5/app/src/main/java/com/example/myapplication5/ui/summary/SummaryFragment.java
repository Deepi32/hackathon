package com.example.myapplication5.ui.summary;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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

public class SummaryFragment extends Fragment {

    private SummaryViewModel summaryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        summaryViewModel =
                ViewModelProviders.of(this).get(SummaryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final WebView summaryContent = root.findViewById(R.id.summary_content);
        summaryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //summaryContent.set;
            }
        });


        final Button submit = root.findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("HomeFrag", "OnClick, submit");
                Activity activity = getActivity();
                if (activity != null && activity instanceof MainActivity) {
                    ((MainActivity) activity).switchToNextTab(R.id.navigation_summary);
                }
            }
        });

        String userId = "";
        Activity activity = getActivity();
        if (activity != null && activity instanceof MainActivity) {
            BookingDetails bookingDetails = ((MainActivity) activity).getBookingDetails();
            if (bookingDetails != null) {
                userId = bookingDetails.getUserId();
            }
        }
        getData(Consts.URL_ADDRESS + Consts.URL_SUMMARY + userId);
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
                            /*if (obj.getInt("status") != 200) {
                                homeViewModel.setText("Error: " + obj.getString("message"));
                                return;
                            }

                            JSONObject data = obj.getJSONObject("data");
                            BookingDetails booking = new BookingDetails();
                            booking.initFrom(data);
                            homeViewModel.setBookingDetails(booking);*/

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
                        Toast.makeText(SummaryFragment.this.getContext(),
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