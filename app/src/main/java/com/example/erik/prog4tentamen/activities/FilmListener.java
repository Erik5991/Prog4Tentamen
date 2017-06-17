package com.example.erik.prog4tentamen.activities;

import com.example.erik.prog4tentamen.objects.Film;

import java.util.ArrayList;

/**
 * Created by Erik on 16-6-2017.
 */

public interface FilmListener {

  void FilmAvailable(ArrayList<Film> filmArrayList);

    // Callback function to handle a single added ToDo.
    void onFilmAvailible(Film film);

    // Callback to handle serverside API errors
 //   void onToDosError(String message);
}




