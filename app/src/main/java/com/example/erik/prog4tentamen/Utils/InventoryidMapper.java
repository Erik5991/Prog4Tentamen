package com.example.erik.prog4tentamen.Utils;

import android.util.Log;

import com.example.erik.prog4tentamen.objects.Inventoryid;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Teunvz on 19-6-2017.
 */

public class InventoryidMapper {

    public static ArrayList<Inventoryid> getinvnetoryByID (JSONObject response){

        ArrayList<Inventoryid> inventoryidList = new ArrayList<>();
        ArrayList<Integer> allCopysoutlist = new ArrayList<>();

        try{

            JSONArray allcopys = response.getJSONArray("allcopys");
            JSONArray allcopysout = response.getJSONArray("rentedout");
            Integer allcopysLength = allcopys.length();
            Integer allcopysoutLength = allcopysout.length();

            for(int i = 0; i < allcopysoutLength; i++) {
                JSONObject jsonObject = allcopysout.getJSONObject(i);
                allCopysoutlist.add(jsonObject.getInt("inventory_id"));
            }

            for(int i = 0; i < allcopysLength; i++){
                JSONObject jsonObject = allcopys.getJSONObject(i);
                Integer inventoryid = jsonObject.getInt("inventory_id");

                if(allCopysoutlist.contains(inventoryid)){
                    Inventoryid id = new Inventoryid(inventoryid, "Unavailable");
                    inventoryidList.add(id);
                }
                else {
                    Inventoryid id = new Inventoryid(inventoryid, "Available");
                    inventoryidList.add(id);
                }
            }


        } catch( JSONException ex) {
            Log.e("ToDoMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return inventoryidList;
    }
}
