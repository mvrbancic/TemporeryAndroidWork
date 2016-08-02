package com.example.skaro.diplomskiaplikacija;

import android.media.MediaRouter;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Skaro on 28.07.2016..
 */
public class ListRequest extends StringRequest {

        private static final String  LIST_REQUEST_URL = "http://mojdiplomski.netai.net/List.php";

        private Map<String, String> params;

        public ListRequest( String category,  Response.Listener<String> listener) {
            super (Request.Method.POST, LIST_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("category", category);



        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }


