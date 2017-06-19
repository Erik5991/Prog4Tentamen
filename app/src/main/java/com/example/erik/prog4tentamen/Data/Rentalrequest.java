package com.example.erik.prog4tentamen.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.erik.prog4tentamen.Interfaces.RentalListener;
import com.example.erik.prog4tentamen.Utils.RentalMapper;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class Rentalrequest {

    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    private RentalListener rentalListener;
    private SharedPrefferenceController sharedPrefferenceController;

    /**
     * Constructor
     *
     * @param context
    //   * @param listener
     */
    public Rentalrequest(Context context, RentalListener rentalListener) {
        this.context = context;
        this.rentalListener = rentalListener;
    }


    public void makeRental(Integer inventoryID, Integer userID) {

        sharedPrefferenceController = new SharedPrefferenceController(context.getApplicationContext());
        final String token = sharedPrefferenceController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.POST,
                BaseAPI.URL_MAKERENTAL + userID + "/" + inventoryID,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        rentalListener.onRentalMade("Rental made");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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

    public void getRentalsByID(Integer userID) {

        sharedPrefferenceController = new SharedPrefferenceController(context.getApplicationContext());
        final String token = sharedPrefferenceController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                BaseAPI.URL_MAKERENTAL + userID,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        rentalListener.onRentalsAvailible(RentalMapper.getRentalByID(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, error.toString());
                        Log.e("error hier", error.toString());
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

    public void makeRentalreturn(Integer inventoryID, Integer userID) {

        sharedPrefferenceController = new SharedPrefferenceController(context.getApplicationContext());
        final String token = sharedPrefferenceController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.PUT,
                BaseAPI.URL_MAKERENTAL + userID + "/" + inventoryID,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        rentalListener.onRentalReturned("Movie returned");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
