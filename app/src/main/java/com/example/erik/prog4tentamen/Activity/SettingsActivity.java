package com.example.erik.prog4tentamen.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.erik.prog4tentamen.Data.Loginrequest;
import com.example.erik.prog4tentamen.Data.Registerrequest;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.Utils.displayToastMessage;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class SettingsActivity extends AppCompatActivity{

    private EditText editTextCount;
    private Button buttonSave, buttoncountup, buttoncountdown;
    displayToastMessage dp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        final SharedPrefferenceController sharedPrefferenceController = new SharedPrefferenceController(getApplicationContext());

        editTextCount = (EditText) findViewById(R.id.editTextCount);

        dp = new displayToastMessage(getApplicationContext());

        Log.i("count", sharedPrefferenceController.getCount());
        updateCount(Integer.parseInt(sharedPrefferenceController.getCount()));

        buttonSave = (Button) findViewById(R.id.buttonSavePreference);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Count in settings", getCount() + "");
                sharedPrefferenceController.setCount(getCount());
                dp.displayMessage("Films per request geupdate naar: " + getCount());
            }
        });

        buttoncountup = (Button) findViewById(R.id.buttonCountPlus);
        buttoncountup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateCount(getCount() + 1);
            }
        });

        buttoncountdown = (Button) findViewById(R.id.buttonCountMin);
        buttoncountdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getCount() > 1){
                    updateCount(getCount() - 1);
                }
            }
        });
    }

    public void updateCount(Integer count){
        editTextCount.setText(count + "");
    }

    public Integer getCount(){
        return Integer.parseInt(editTextCount.getText().toString());
    }
}
