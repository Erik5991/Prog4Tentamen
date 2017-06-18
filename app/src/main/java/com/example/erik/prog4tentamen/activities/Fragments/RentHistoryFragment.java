package com.example.erik.prog4tentamen.activities.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Adapter.FilmAdapter;
import com.example.erik.prog4tentamen.activities.Adapter.RentalAdapter;
import com.example.erik.prog4tentamen.activities.Data.Filmrequest;
import com.example.erik.prog4tentamen.controller.TokenController;
import com.example.erik.prog4tentamen.objects.Film;
import com.example.erik.prog4tentamen.objects.Inventoryid;
import com.example.erik.prog4tentamen.objects.Rental;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Erik on 16-6-2017.
 */

public class RentHistoryFragment extends BaseFragment implements Filmrequest.FilmListener {

    private ListView rentalListView;
    private BaseAdapter RentalAdapter;
    private ArrayList<Rental> rentalArrayList = new ArrayList<>();
    private Context context;
    private Integer inventoryid;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.rent_history_layout, container, false);
        getActivity().getFragmentManager();

        rentalListView = (ListView) view.findViewById(R.id.filmListView);
        RentalAdapter = new RentalAdapter(getActivity(), getActivity().getLayoutInflater(), rentalArrayList);
        rentalListView.setAdapter(RentalAdapter);
        rentalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Rental rental = rentalArrayList.get(position);
                if(rental.getReturn_date() == "null"){
                    showAlert(rental, rental.getInventoryID());
                }
                else {
                    showAlertnot(rental);
                }
            }
        });

        getAllRentals();
        RentalAdapter.notifyDataSetChanged();
        return view;
    }




    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context=activity;
    }



    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void getAllRentals(){
        Filmrequest request = new Filmrequest(getActivity().getApplicationContext(), this);
        TokenController tokenController = new TokenController(getActivity().getApplicationContext());
        request.getRentalsByID(Integer.parseInt(tokenController.getID()));
    }

    public void showAlert(Rental rental, final Integer inventoryid){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
        myAlert.setMessage("Do you agree to retun "+ rental.getTitle())
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        TokenController tokenController = new TokenController(context);
                        makeRentalreturn(inventoryid, Integer.parseInt(tokenController.getID()));
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

    public void showAlertnot(Rental rental){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
        myAlert.setMessage(rental.getTitle() + " Is allready returned")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        myAlert.show();
    }

    private void makeRentalreturn(Integer inventoryid, Integer customerID){
        Filmrequest request = new Filmrequest(context, this);
        request.makeRentalreturn(inventoryid, customerID);
    }

    public void displayMessage(String toastString){
        Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
    }


    @Override
    public void onFilmsAvailible(ArrayList<Film> films) {

    }

    @Override
    public void onInventoryAvailible(ArrayList<Inventoryid> inventoryid) {

    }

    @Override
    public void onRentalsAvailible(ArrayList<Rental> rentals) {
        rentalArrayList.clear();
        for(int i = 0; i < rentals.size(); i++) {
            rentalArrayList.add(rentals.get(i));
        }
        Collections.reverse(rentalArrayList);
        RentalAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRentalMade(String status) {

    }

    @Override
    public void onRentalReturned(String status) {
        displayMessage(status);
        getAllRentals();
    }

    @Override
    public void onFilmAvailible(Film film) {

    }

    @Override
    public void onToDosError(String message) {

    }
}