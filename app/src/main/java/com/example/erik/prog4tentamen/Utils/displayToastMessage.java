package com.example.erik.prog4tentamen.Utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class displayToastMessage {
    private Context context;

    public displayToastMessage(Context context){
        this.context = context;
    }

    public void displayMessage(String toastString){
        Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
    }
}
