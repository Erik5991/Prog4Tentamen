package com.example.erik.prog4tentamen.Utils;

/**
 * Created by Erik on 16-6-2017.
 */

import android.util.Log;

import com.example.erik.prog4tentamen.objects.Film;
import com.example.erik.prog4tentamen.objects.Inventoryid;
import com.example.erik.prog4tentamen.objects.Rental;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FilmMapper {

    public static ArrayList<Film> filmArrayList(JSONObject response){

        ArrayList<Film> result = new ArrayList<>();

        try{
            JSONArray jsonObj = response.getJSONArray("result");
            Integer jsonLength = jsonObj.length();

            for(int i = 0; i < jsonLength; i++){
                JSONObject jsonObject = jsonObj.getJSONObject(i);

                Film film = new Film(
                        jsonObject.getInt("film_id"),
                        jsonObject.getInt("rental_duration"),
                        jsonObject.getInt("length"),
                        jsonObject.getString("title"),
                        jsonObject.getString("description"),
                        jsonObject.getString("release_year"),
                        jsonObject.getString("rating"),
                        jsonObject.getDouble("rental_rate"),
                        jsonObject.getDouble("replacement_cost")
                );
                result.add(film);
                Log.i("Jsonobject film", film.getTitle());
            }
        } catch( JSONException ex) {
            Log.e("Filmmapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }

    public static Film getFilmByID (JSONObject response){

        Film result = null;

        try{
            JSONArray jsonObj = response.getJSONArray("result");
            Integer jsonLength = jsonObj.length();

            for(int i = 0; i < jsonLength; i++){
                JSONObject jsonObject = jsonObj.getJSONObject(i);

                Film film = new Film(
                        jsonObject.getInt("film_id"),
                        jsonObject.getInt("rental_duration"),
                        jsonObject.getInt("length"),
                        jsonObject.getString("title"),
                        jsonObject.getString("description"),
                        jsonObject.getString("release_year"),
                        jsonObject.getString("rating"),
                        jsonObject.getDouble("rental_rate"),
                        jsonObject.getDouble("replacement_cost")
                );
                result = film;
                Log.i("Jsonobject film", film.getTitle());
            }
        } catch( JSONException ex) {
            Log.e("Filmmapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
