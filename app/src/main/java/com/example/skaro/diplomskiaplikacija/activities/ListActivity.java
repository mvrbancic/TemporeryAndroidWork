package com.example.skaro.diplomskiaplikacija.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.skaro.diplomskiaplikacija.R;
import com.example.skaro.diplomskiaplikacija.adapters.MotorsListAdapter;
import com.example.skaro.diplomskiaplikacija.entities.Motor;
import com.example.skaro.diplomskiaplikacija.requests.ListRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements MotorsListAdapter.OnMotorClickListener {


    RecyclerView motorsRecyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    List<Motor> motoriList;

    private static final String TAG = ListActivity.class.getSimpleName();

    ListRequest listRequest;
    RequestQueue requestQueue;
    MotorsListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initData();
        initRecyclerView();
    }

    private void initRecyclerView() {
        motorsRecyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        motorsRecyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);
        motorsRecyclerView.setLayoutManager(recyclerViewlayoutManager);
        adapter = new MotorsListAdapter(this);
        motorsRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        motoriList = new ArrayList<>();
        getMotori();
    }

    public void getMotori() {

        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");

        listRequest = new ListRequest(category, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                jsonParseData(response);
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(listRequest);
    }

    public void jsonParseData(String response) {
        try {
            JSONObject responseObject = new JSONObject(response);

            if (responseObject.getBoolean("success")) {

                JSONArray motoriArray = responseObject.getJSONArray("motori");

                for (int i = 0; i < motoriArray.length(); i++) {
                    Motor motor = new Motor();
                    JSONObject motorJson = motoriArray.getJSONObject(i);
                    motor.name = motorJson.getString("name");
                    motor.imageURL = motorJson.getString("image");

                    // TODO bilokoji marko nek doda ostatak podataka

                    motoriList.add(motor);
                }
                adapter.setMotorsList(motoriList);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(Motor motor) {
        Toast.makeText(this, motor.name, Toast.LENGTH_SHORT).show();
    }
}


