package com.example.erik.prog4tentamen.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.erik.prog4tentamen.Interfaces.FilmListener;
import com.example.erik.prog4tentamen.Utils.FilmMapper;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 16-6-2017.
 */

public class Filmrequest {


    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    private FilmListener filmListener;
    private SharedPrefferenceController sharedPrefferenceController;

    /**
     * Constructor
     *
     * @param context
    //   * @param listener
     */
    public Filmrequest(Context context, FilmListener listener) {
        this.context = context;
        this.filmListener = listener;
    }

    /**
     * Verstuur een GET request om alle ToDo's op te halen.
     */
    public void getAllFilms(Integer count, Integer offset) {

        sharedPrefferenceController = new SharedPrefferenceController(context.getApplicationContext());
        final String token = sharedPrefferenceController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                BaseAPI.URL_GET_MOVIES + "?count=" + count + "&offset=" + offset,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Succesvol response
                        Log.i(TAG, response.toString());
                        filmListener.onFilmsAvailible(FilmMapper.filmArrayList(response));
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

    public void getAllFilmsByName(String title, Integer count, Integer offset) {

        sharedPrefferenceController = new SharedPrefferenceController(context.getApplicationContext());
        final String token = sharedPrefferenceController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                BaseAPI.URL_GET_MOVIES + "?title=" + title + "&offset=" + offset + "&count=" + count,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Succesvol response
                        Log.i(TAG, response.toString());
                        filmListener.onFilmsAvailible(FilmMapper.filmArrayList(response));
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