package com.example.skaro.diplomskiaplikacija.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.skaro.diplomskiaplikacija.R;
import com.example.skaro.diplomskiaplikacija.adapters.GetDataAdapter;
import com.example.skaro.diplomskiaplikacija.adapters.RecyclerViewAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerView.LayoutManager recyclerViewlayoutManager;
    List<GetDataAdapter> motoriListArray;
    RecyclerView.Adapter recyclerViewadapter;

    private static final String TAG = ListActivity.class.getSimpleName();
    String LIST_REQUEST_URL = "http://mojdiplomski.netai.net/List2.php?category=";

    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        motoriListArray = new ArrayList<>();


        recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerView.setHasFixedSize(true);
        recyclerViewlayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(recyclerViewlayoutManager);


        getMotori();
    }

    public void getMotori() {

        Intent intent = getIntent();
        final String category = intent.getStringExtra("category");

        jsonArrayRequest = new JsonArrayRequest(LIST_REQUEST_URL,

                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        jsonParseData(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(jsonArrayRequest);

    }

    public void jsonParseData(JSONArray array) {

        for (int i = 0; i < array.length(); i++) {

            GetDataAdapter motor = new GetDataAdapter();

            JSONObject json = null;
            try {

                json = array.getJSONObject(i);

                //GetDataAdapter2.setImageTitleNamee(json.getString(JSON_IMAGE_TITLE_NAME));

                //GetDataAdapter2.setImageServerUrl(json.getString(JSON_IMAGE_URL));

                motor.setManufacturer(json.getString("manufacturer"));
                motor.setName(json.getString("name"));
                motor.setCategory(json.getString("category"));
                motor.setImage(json.getString("image"));

            } catch (JSONException e) {

                e.printStackTrace();
            }
            motoriListArray.add(motor);
        }

        recyclerViewadapter = new RecyclerViewAdapter(motoriListArray, this);

        recyclerView.setAdapter(recyclerViewadapter);
    }
}


