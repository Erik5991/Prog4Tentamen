package com.example.erik.prog4tentamen.activities;

import com.example.erik.prog4tentamen.objects.Film;

import java.util.ArrayList;

/**
 * Created by Erik on 16-6-2017.
 */

public interface FilmListener {

    void FilmAvailable(ArrayList<Film> filmArrayList);
    void onFilmAvailible(Film film);
    void onError();
}




