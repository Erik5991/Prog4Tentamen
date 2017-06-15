package com.example.erik.prog4tentamen.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.erik.prog4tentamen.R;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private Button buttonLogin, buttonRegister;

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

                Log.i("Log", username + password);
            }
        });

        buttonRegister = (Button) findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });
    }
}
