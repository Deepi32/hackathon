package com.example.myapplication5.ui.dashboard;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication5.R;
import com.example.myapplication5.model.SimDetails;
import com.example.myapplication5.ui.home.HomeFragment;
import com.example.myapplication5.utils.Consts;
import com.example.myapplication5.utils.ItemListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        final RecyclerView simList = root.findViewById(R.id.sim_list);
        final ItemListAdapter adapter = new ItemListAdapter();
        simList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        simList.setAdapter(adapter);
        dashboardViewModel.getSimList().observe(this, new Observer<ArrayList<SimDetails>>() {
            @Override
            public void onChanged(ArrayList<SimDetails> simDetails) {
                if (simDetails != null && !simDetails.isEmpty()) {
                    adapter.setSimList(simDetails);
                }
            }
        });
        Button check = root.findViewById(R.id.submit);
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getData("http://192.168.0.104:8080/sim/details?countryName=Indonesia");
                getData(Consts.SERVER_ADDRESS_EMULATOR + "sim/details?countryName=Indonesia");
            }
        });
        return root;
    }



    private void getData(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("DashboardFragment", "getData, onResponse : " + response);
                        dashboardViewModel.setText("response: " + response);
                        //hiding the progressbar after completion
                        //progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            ArrayList<SimDetails> simList = new ArrayList<>();
                            JSONObject obj = new JSONObject(response);
                            JSONArray simArray = obj.getJSONArray("data");
                            for (int i = 0 ; i < simArray.length(); i++) {
                                JSONObject sim = simArray.getJSONObject(i);
                                SimDetails simDetails = new SimDetails();
                                simDetails.setId(sim.getInt("id"));
                                simDetails.setNetworkProvider(sim.getString("companyName"));
                                simDetails.setValidity(sim.getString("numberOfDays"));
                                simDetails.setPrice(sim.getString("price"));
                                simDetails.setDataBand(sim.getBoolean("isDataAvailable") ?
                                        "4G" : "2G");
                                simList.add(simDetails);
                            }
                            if (!simList.isEmpty()) {
                                dashboardViewModel.setSimList(simList);
                            }

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
                        Toast.makeText(DashboardFragment.this.getContext(),
                                error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this.getContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }
}