package com.example.erik.prog4tentamen.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.erik.prog4tentamen.Interfaces.InventoryListener;
import com.example.erik.prog4tentamen.Utils.InventoryidMapper;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class Inventoryrequest {

    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    private InventoryListener inventoryListener;
    private SharedPrefferenceController sharedPrefferenceController;

    /**
     * Constructor
     *
     * @param context
    //   * @param listener
     */
    public Inventoryrequest(Context context, InventoryListener inventoryListener) {
        this.context = context;
        this.inventoryListener = inventoryListener;
    }

    public void getInventoryByID(Integer filmid) {

        sharedPrefferenceController = new SharedPrefferenceController(context.getApplicationContext());
        final String token = sharedPrefferenceController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                BaseAPI.URL_FILMBYID + filmid,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Succesvol response
                        Log.i(TAG, response.toString());
                        inventoryListener.onInventoryAvailible(InventoryidMapper.getinvnetoryByID(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // handleErrorResponse(error);
                        Log.e(TAG, error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                headers.put("Authorization", "Bearer " + token);
                return headers;
            }
        };

        // Access the RequestQueue through your singleton class.
        VolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
    }
}
