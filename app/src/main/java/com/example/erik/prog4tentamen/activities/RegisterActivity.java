package com.example.erik.prog4tentamen.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.erik.prog4tentamen.R;

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
        }
        else {
            Log.i("registreren", "Mislukt" );
        }

    }
}
