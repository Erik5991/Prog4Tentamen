package com.example.erik.prog4tentamen.activities.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Activity.MainActivity;
import com.example.erik.prog4tentamen.activities.Data.BaseAPI;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Teunvz on 13-6-2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText_firstname, editText_lastname, editText_email, editText_password, editText_comfirmPassword;
    Button button_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editText_firstname = (EditText) findViewById(R.id.editText_firstname);
        editText_lastname = (EditText) findViewById(R.id.editText_lastname);
        editText_email = (EditText) findViewById(R.id.editText_email);
        editText_password = (EditText) findViewById(R.id.editText_password);
        editText_comfirmPassword = (EditText) findViewById(R.id.editText_comfirmpassword);

        button_register = (Button) findViewById(R.id.button_registreren);
        button_register.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String firstname = editText_firstname.getText().toString();
        String lastname = editText_lastname.getText().toString();
        String email = editText_email.getText().toString();
        String password = editText_password.getText().toString();
        String comfirmpassword = editText_comfirmPassword.getText().toString();

        if(password.equals(comfirmpassword) && !firstname.isEmpty() && !lastname.isEmpty() && !email.isEmpty() && !password.isEmpty() && !comfirmpassword.isEmpty()){
            Log.i("register", firstname + lastname + email + password + comfirmpassword);
            handleRegister(email, password, firstname, lastname);
        }
        else {
            Log.i("registreren", "Mislukt" );
        }

    }

    private void handleRegister(String username, String password, String firstname, String lastname) {

        String body = "{\"username\":\"" + username + "\",\"password\":\"" + password + "\",\"firstname\":\"" + firstname + "\",\"lastname\":\"" + lastname + "\"}";
        Log.i("TAG", "handleLogin - body = " + body);

        try {
            JSONObject jsonBody = new JSONObject(body);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, BaseAPI.URL_REGISTER, jsonBody, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            displayMessage("Succesvol registreerd!");
                            Intent main = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(main);
                            finish();
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
            VolleyRequestQueue.getInstance(this).addToRequestQueue(jsObjRequest);
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
                    displayMessage(json);
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

    // TODO Verplaats displayMessage naar een centrale 'utility class' voor gebruik in alle classes.
    public void displayMessage(String toastString){
        Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG).show();
    }
}