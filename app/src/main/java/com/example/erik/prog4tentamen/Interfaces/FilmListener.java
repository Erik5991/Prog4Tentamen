package com.example.erik.prog4tentamen.Interfaces;

import com.example.erik.prog4tentamen.objects.Film;

import java.util.ArrayList;

/**
 * Created by Teunvz on 19-6-2017.
 */

public interface FilmListener {
    void onFilmsAvailible(ArrayList<Film> films);

    void onFilmAvailible(Film film);
}
