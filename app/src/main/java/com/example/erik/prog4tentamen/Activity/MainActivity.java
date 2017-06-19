package com.example.erik.prog4tentamen.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.erik.prog4tentamen.Data.Loginrequest;
import com.example.erik.prog4tentamen.R;


import com.example.erik.prog4tentamen.Utils.displayToastMessage;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;


public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonRegister;
    displayToastMessage dp;
    Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPassword = (EditText) findViewById(R.id.edittext_password);
        editTextUsername = (EditText) findViewById(R.id.edittext_username);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                if(username.isEmpty()){
                    dp.displayMessage("Vul je email in");
                }
                else if(password.isEmpty()){
                    dp.displayMessage("Vul je wachtwoord in");
                }
                else {
                    handleLogin(username, password);
                }
            }
        });

        buttonRegister = (Button) findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
                finish();
            }
        });

        SharedPrefferenceController sharedPrefferenceController = new SharedPrefferenceController(getApplicationContext());
        String token = sharedPrefferenceController.getToken();

        if(token != null){
            Intent i = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(i);
            finish();
        }

        Intent i = getIntent();
        String username = i.getStringExtra("username");
        String password = i.getStringExtra("password");

        if(username != null && password != null){
            Loginrequest loginrequest = new Loginrequest(getApplicationContext(), activity);
            loginrequest.login(username, password);
        }

        dp = new displayToastMessage(getApplicationContext());
    }

    private void handleLogin(String username, String password) {

        Loginrequest loginrequest = new Loginrequest(getApplicationContext(), activity);
        loginrequest.login(username, password);
    }

    public void finishMainActivity(){
        finish();
    }
}