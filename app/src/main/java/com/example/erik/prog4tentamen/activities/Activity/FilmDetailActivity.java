package com.example.erik.prog4tentamen.activities.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.objects.Film;

import static com.example.erik.prog4tentamen.R.id.titleTextView;
import static com.example.erik.prog4tentamen.activities.Fragments.HomeFragment.EXTRA_FILM;


/**
 * Created by Erik on 15-6-2017.
 */

public class FilmDetailActivity extends AppCompatActivity {

    private TextView titleTextView;
    private TextView textViewavailable;
    private TextView rateTextView;
    private TextView textViewDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_film);

        titleTextView = (TextView) findViewById(R.id.textViewTitle);
        textViewavailable = (TextView) findViewById(R.id.textViewavailable);
        rateTextView = (TextView) findViewById(R.id.rateTextView);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);

        Bundle extras = getIntent().getExtras();

        Film film = (Film) extras.getSerializable(EXTRA_FILM);
        titleTextView.setText("test");
        // textViewavailable.setText("");
        //  rateTextView.setText(film.getRental_rate().toString());
        //  textViewDescription.setText(film.getDescription());


}}