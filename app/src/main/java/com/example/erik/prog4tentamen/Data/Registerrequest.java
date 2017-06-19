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
import com.example.erik.prog4tentamen.Activity.MainActivity;
import com.example.erik.prog4tentamen.Utils.displayToastMessage;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class Registerrequest {
    private Context context;
    private Activity activity;
    private displayToastMessage dp;

    public Registerrequest(Context context, Activity activity) {
        this.context = context;
        this.activity = activity;
    }

    public void register(final String username, final String password, String firstname, String lastname) {

        dp = new displayToastMessage(context);

        String body = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\"}";
        Log.i("TAG", "handleLogin - body = " + body);

        try {
            JSONObject jsonBody = new JSONObject(body);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, BaseAPI.URL_REGISTER, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            dp.displayMessage("Succesvol registreerd!");

                            Intent mainactivity = new Intent(context, MainActivity.class);
                            mainactivity.putExtra("username", username);
                            mainactivity.putExtra("password", password);
                            context.startActivity(mainactivity);

                            activity.finish();
                            MainActivity mainActivity = new MainActivity();
                            mainActivity.finishMainActivity();

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            handleErrorResponse(error);
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

    public void handleErrorResponse(VolleyError error) {
        if(error instanceof com.android.volley.AuthFailureError) {
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
        } else if(error instanceof com.android.volley.NoConnectionError) {
        }
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
