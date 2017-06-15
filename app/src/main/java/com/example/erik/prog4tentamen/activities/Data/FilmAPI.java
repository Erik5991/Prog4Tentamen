package com.example.erik.prog4tentamen.activities.Data;

import android.content.Context;

import com.example.erik.prog4tentamen.activities.Utils.NetworkController;

/**
 * Created by Erik on 13-6-2017.
 */

public class FilmAPI {

    private static final String TAG = FilmAPI.class.getName();

    private static String API_KEY = "";
    private static final String URL_API = "localhost:3000/api/v1/";
    private static final String URL_RENTALS = "/rentals";
    private static final String URL_RENTAL_INSERT = "/rentals/insert";
    private static final String URL_RENTAL_DELETE = "/rentals/delete";


    private NetworkController networkController;

    public FilmAPI(Context context) {

        API_KEY = "";
        networkController = NetworkController.getInstance(context);
    }



}