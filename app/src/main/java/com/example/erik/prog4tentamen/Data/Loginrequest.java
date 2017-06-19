package com.example.erik.prog4tentamen.Data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.erik.prog4tentamen.Activity.HomeActivity;
import com.example.erik.prog4tentamen.Utils.displayToastMessage;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class Loginrequest {
    private Context context;
    private Activity activity;
    private displayToastMessage dp;

    public Loginrequest(Context context, Activity activity) {
        this.activity = activity;
        this.context = context;
    }

    public void login(String username, String password) {

        dp = new displayToastMessage(context);

        String body = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}";
        Log.i("TAG", "handleLogin - body = " + body);

        try {
            JSONObject jsonBody = new JSONObject(body);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, BaseAPI.URL_LOGIN, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            dp.displayMessage("Succesvol ingelogd");
                            try {
                                String token = response.getString("token");
                                Integer id = Integer.parseInt(response.getString("id"));

                                SharedPrefferenceController SharedPrefferenceController = new SharedPrefferenceController(context);

                                SharedPrefferenceController.setToken(token);
                                SharedPrefferenceController.setID(id);

                                Intent main = new Intent(context, HomeActivity.class);
                                context.startActivity(main);
                                activity.finish();

                            } catch (JSONException e) {

                            }
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                            String json = null;
                            NetworkResponse response = error.networkResponse;
                            if (response != null && response.data != null) {
                                json = new String(response.data);
                                json = trimMessage(json, "error");
                                if (json != null) {
                                    json = "Error " + response.statusCode + ": " + json;
                                    dp.displayMessage(json);
                                }
                            } else {
                                Log.e("tag", "handleErrorResponse: kon geen networkResponse vinden.");
                            }
                        }
                    });

            jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(
                    1500, // SOCKET_TIMEOUT_MS,
                    2, // DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            // Access the RequestQueue through your singleton class.
            VolleyRequestQueue.getInstance(context).addToRequestQueue(jsObjRequest);
        } catch (JSONException e) {

        }
        return;
    }

    public String trimMessage(String json, String key){
        String trimmedString = null;

        try{
            JSONObject obj = new JSONObject(json);
            trimmedString = obj.getString(key);
        } catch(JSONException e){
            e.printStackTrace();
            return null;
        }
        return trimmedString;
    }
}
