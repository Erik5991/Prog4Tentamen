package com.example.erik.prog4tentamen.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.erik.prog4tentamen.Data.Inventoryrequest;
import com.example.erik.prog4tentamen.Data.Rentalrequest;
import com.example.erik.prog4tentamen.Interfaces.InventoryListener;
import com.example.erik.prog4tentamen.Interfaces.RentalListener;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.Adapter.InventoryAdapter;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.objects.Film;
import com.example.erik.prog4tentamen.objects.Inventoryid;
import com.example.erik.prog4tentamen.objects.Rental;

import java.util.ArrayList;

/**
 * Created by Erik on 15-6-2017.
 */

public class FilmDetailActivity extends AppCompatActivity  implements InventoryListener, RentalListener{
    private TextView textViewTitle, textViewDescription, textViewReleaseYear, textViewRentalDuration, textViewRentalRate, textViewLength, textViewReplacementCost, textViewRating;
    private ListView inventoryListView;
    private ArrayList<Inventoryid> inventoryids = new ArrayList<>();
    private InventoryAdapter inventoryAdapter;
    private Integer filmID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filmdetailed);

        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewDescription = (TextView) findViewById(R.id.textViewDescription);
        textViewReleaseYear = (TextView) findViewById(R.id.textViewReleaseYear);
        textViewRentalDuration = (TextView) findViewById(R.id.textViewRentalDuration);
        textViewRentalRate = (TextView) findViewById(R.id.textViewRentalRate);
        textViewLength = (TextView) findViewById(R.id.textViewLength);
        textViewReplacementCost = (TextView) findViewById(R.id.textViewReplacementCost);
        textViewRating = (TextView) findViewById(R.id.textViewRating);



        Intent intent = getIntent();
        final Film film = (Film)intent.getSerializableExtra("film");

        textViewTitle.setText(film.getTitle().toLowerCase());
        textViewDescription.setText(film.getDescription());
        textViewReleaseYear.setText(film.getRelease_year());
        textViewRentalDuration.setText(film.getRental_duration() + " Days");
        textViewRentalRate.setText("€ " + film.getRental_rate());
        textViewLength.setText(film.getLength() + " min");
        textViewReplacementCost.setText("€ " + film.getReplacement_cost());
        textViewRating.setText(film.getRating());

        inventoryListView = (ListView) findViewById(R.id.inventoryListView);
        inventoryAdapter = new InventoryAdapter(getApplicationContext(),  getLayoutInflater(), inventoryids);
        inventoryListView.setAdapter(inventoryAdapter);
        inventoryListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (inventoryids.get(position).getStatus() == "Available"){
                    showAlert(film, inventoryids.get(position));
                }
                else {
                    showAlertnot(film);
                }

            }
        });

        filmID = film.getFilm_id();
        getInventoryIDs(film.getFilm_id());
    }

    public void showAlert(Film film, final Inventoryid inventoryid){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage("Do you agree to rent "+ film.getTitle() +  " for €" + String.format("%.2f", film.getRental_rate()) +  " p/" + film.getRental_duration() + " days")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPrefferenceController sharedPrefferenceController = new SharedPrefferenceController(getApplicationContext());
                        makeRental(inventoryid.getInventoryid(), Integer.parseInt(sharedPrefferenceController.getID()));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create();
        myAlert.show();
    }

    public void showAlertnot(final Film film){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
        myAlert.setMessage(film.getTitle() +  " is unavailable to rent at this moment")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        myAlert.show();
    }

    private void getInventoryIDs(Integer filmID){
        Inventoryrequest request = new Inventoryrequest(getApplicationContext(), this);
        request.getInventoryByID(filmID);
    }

    private void makeRental(Integer inventoryID, Integer userID){
        Rentalrequest request = new Rentalrequest(getApplicationContext(), this);
        request.makeRental(inventoryID, userID);
    }

    public void displayMessage(String toastString){
        Toast.makeText(getApplicationContext(), toastString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onInventoryAvailible(ArrayList<Inventoryid> inventoryid) {
        inventoryids.clear();
        for(int i = 0; i < inventoryid.size(); i++) {
            inventoryids.add(inventoryid.get(i));
        }
        inventoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRentalsAvailible(ArrayList<Rental> rentals) {

    }

    @Override
    public void onRentalMade(String status) {
        displayMessage(status);
        getInventoryIDs(filmID);
    }

    @Override
    public void onRentalReturned(String status) {

    }
}