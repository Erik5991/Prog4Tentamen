package com.example.erik.prog4tentamen.Utils;

import android.util.Log;

import com.example.erik.prog4tentamen.objects.Rental;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class RentalMapper {

    public static ArrayList<Rental> getRentalByID(JSONObject response){

        ArrayList<Rental> result = new ArrayList<>();

        try{
            JSONArray jsonObj = response.getJSONArray("result");
            Integer jsonLength = jsonObj.length();

            for(int i = 0; i < jsonLength; i++){
                JSONObject jsonObject = jsonObj.getJSONObject(i);

                Rental rental = new Rental(
                        jsonObject.getInt("inventory_id"),
                        jsonObject.getInt("rental_duration"),
                        jsonObject.getDouble("rental_rate"),
                        jsonObject.getString("title"),
                        jsonObject.getString("rental_date"),
                        jsonObject.getString("return_date")
                );

                result.add(rental);
                Log.i("Jsonobject film", rental.getTitle());
            }
        } catch( JSONException ex) {
            Log.e("ToDoMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }

        return result;
    }
}
