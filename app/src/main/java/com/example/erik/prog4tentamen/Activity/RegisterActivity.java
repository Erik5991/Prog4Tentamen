package com.example.erik.prog4tentamen.Activity;

import android.app.Activity;
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
import com.example.erik.prog4tentamen.Data.Registerrequest;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.Data.BaseAPI;
import com.example.erik.prog4tentamen.Utils.displayToastMessage;
import com.example.erik.prog4tentamen.controller.VolleyRequestQueue;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Teunvz on 13-6-2017.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editText_firstname, editText_lastname, editText_email, editText_password, editText_comfirmPassword;
    private Button button_register;
    private Activity activity = this;
    private displayToastMessage dp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dp = new displayToastMessage(getApplicationContext());

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
            if(!password.equals(comfirmpassword)){
                dp.displayMessage("Wachtwoorden komen niet overheen");
            }
            else if(email.isEmpty()){
                dp.displayMessage("Vul een uniek email in");
            }
            else if(password.isEmpty()){
                dp.displayMessage("Vul een wachtwoord in");
            }
            else if(firstname.isEmpty()){
                dp.displayMessage("Vul een voornaam in");
            }
            else if(lastname.isEmpty()){
                dp.displayMessage("Vul een achternaam in");
            }
        }

    }

    private void handleRegister(String username, String password, String firstname, String lastname) {
        Registerrequest registerrequest = new Registerrequest(getApplicationContext(), activity);
        registerrequest.register(username, password, firstname, lastname);
    }
}