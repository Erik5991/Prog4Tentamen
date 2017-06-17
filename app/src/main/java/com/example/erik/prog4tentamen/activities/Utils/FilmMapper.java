package com.example.erik.prog4tentamen.activities.Utils;

/**
 * Created by Erik on 16-6-2017.
 */

import android.util.Log;

import com.example.erik.prog4tentamen.objects.Film;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Deze class vertaalt JSON objecten naar (lijsten van) ToDos.
 */
public class FilmMapper {

    public static final String FILM_ID = "film_id";
    public static final String TODO_TITLE = "Titel";
    public static final String TODO_DESCRIPTION = "Beschrijving";
    public static final String TODO_UPDATED_AT = "LaatstGewijzigdOp";
    public static final String TODO_STATUS = "Status";

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
            Log.e("ToDoMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
