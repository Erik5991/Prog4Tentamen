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

    // De JSON attributen die we uitlezen
    public static final String FILM_ID = "film_id";
    public static final String TODO_TITLE = "Titel";
    public static final String TODO_DESCRIPTION = "Beschrijving";
    public static final String TODO_UPDATED_AT = "LaatstGewijzigdOp";
    public static final String TODO_STATUS = "Status";

    /**
     * Map het JSON response op een arraylist en retourneer deze.
     */
    public static ArrayList<Film> filmArrayList(JSONObject response){

        ArrayList<Film> result = new ArrayList<>();

        try{
            JSONArray jsonArray = response.getJSONArray(FILM_ID);

            for(int i = 0; i < jsonArray.length(); i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // Convert stringdate to Date
                String timestamp = jsonObject.getString(TODO_UPDATED_AT);


                Film film = new Film(
                      jsonObject.getInt("film_id"),
                       jsonObject.getInt("rental_duration"),
                        jsonObject.getInt("lenght"),
                        jsonObject.getString("title"),
                        jsonObject.getString("description"),
                        jsonObject.getString("release_year"),
                        jsonObject.getString("rating"),
                        jsonObject.getDouble("rental_rate"),
                        jsonObject.getDouble("replacement_cose")
                      //  todoDateTime
                );
                // Log.i("ToDoMapper", "ToDo: " + toDo);
                result.add(film);

            }
        } catch( JSONException ex) {
            Log.e("ToDoMapper", "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
        return result;
    }
}
