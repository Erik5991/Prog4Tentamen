package com.example.erik.prog4tentamen.activities.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.erik.prog4tentamen.activities.FilmListener;
import com.example.erik.prog4tentamen.activities.Utils.FilmMapper;
import com.example.erik.prog4tentamen.controller.TokenController;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;
import com.example.erik.prog4tentamen.objects.Film;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik on 16-6-2017.
 */

public class Filmrequest {


    private Context context;
    public final String TAG = this.getClass().getSimpleName();

    // De aanroepende class implementeert deze interface.
    private FilmListener listener;
    private TokenController tokenController;

    /**
     * Constructor
     *
     * @param context
    //   * @param listener
     */
    public Filmrequest(Context context, Filmrequest.FilmListener listener) {
        this.context = context;
        this.listener = listener;
    }

    /**
     * Verstuur een GET request om alle ToDo's op te halen.
     */
    public void getAllFilms() {

        tokenController = new TokenController(context.getApplicationContext());
        final String token = tokenController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                BaseAPI.URL_GET_MOVIES,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Succesvol response
                        Log.i(TAG, response.toString());
                        listener.onFilmsAvailible(FilmMapper.filmArrayList(response));
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

    public void getAllFilmsByName(String title) {

        tokenController = new TokenController(context.getApplicationContext());
        final String token = tokenController.getToken();

        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                BaseAPI.URL_GET_MOVIES + "?title=" + title,
                new JSONObject(),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Succesvol response
                        Log.i(TAG, response.toString());
                        listener.onFilmsAvailible(FilmMapper.filmArrayList(response));
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

    public interface FilmListener {
        // Callback function to return a fresh list of ToDos
        void onFilmsAvailible(ArrayList<Film> films);

        // Callback function to handle a single added ToDo.
        void onFilmAvailible(Film film);

        // Callback to handle serverside API errors
        void onToDosError(String message);
    }

}