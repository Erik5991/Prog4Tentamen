package com.example.erik.prog4tentamen.activities.Data;

import android.content.Context;

import com.example.erik.prog4tentamen.activities.Utils.NetworkController;

/**
 * Created by Erik on 13-6-2017.
 */

public class BaseAPI {

    private static final String BASE_URL = "https://prog4tent.herokuapp.com/";
    protected static final String FILM_ENDPOINT = BASE_URL + "/films";
    protected static final String CALL_ENDPOINT = BASE_URL + "/call";
    protected static final String INTERPRETER_ENDPOINT = BASE_URL + "/interpreter";


    protected NetworkController networkController;
    protected Context context;
    protected static final String TAG = BaseAPI.class.getSimpleName();

    public BaseAPI(Context context) {
        this.networkController = NetworkController.getInstance(context);
        this.context = context;
    }



}
