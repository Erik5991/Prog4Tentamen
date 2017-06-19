package com.example.erik.prog4tentamen.Fragments;

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
import android.widget.Toast;

import com.example.erik.prog4tentamen.Adapter.RentalAdapter;
import com.example.erik.prog4tentamen.Data.Rentalrequest;
import com.example.erik.prog4tentamen.Interfaces.RentalListener;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.objects.Rental;

import java.util.ArrayList;
import java.util.Collections;


/**
 * Created by Erik on 16-6-2017.
 */

public class RentalReturnFragment extends BaseFragment implements RentalListener {

    private ListView rentalListView;
    private BaseAdapter RentalAdapter;
    private ArrayList<Rental> rentalArrayList = new ArrayList<>();
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.rent_history_layout, container, false);
        getActivity().getFragmentManager();

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rentalListView = (ListView) getView().findViewById(R.id.filmListView);
        RentalAdapter = new RentalAdapter(getActivity(), getActivity().getLayoutInflater(), rentalArrayList);
        rentalListView.setAdapter(RentalAdapter);
        rentalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Rental rental = rentalArrayList.get(position);
                if(rental.getReturn_date() == "null"){
                    popUpRentalAvailable(rental, rental.getInventoryID());
                }
                else {
                    popUpRentalNnavilable(rental);
                }
            }
        });

        getAllRentals();
    }

    private void getAllRentals(){
        Rentalrequest request = new Rentalrequest(getActivity().getApplicationContext(), this);
        SharedPrefferenceController sharedPrefferenceController = new SharedPrefferenceController(getActivity().getApplicationContext());
        request.getRentalsByID(Integer.parseInt(sharedPrefferenceController.getID()));
    }

    private void makeRentalreturn(Integer inventoryid, Integer customerID){
        Rentalrequest request = new Rentalrequest(context, this);
        request.makeRentalreturn(inventoryid, customerID);
    }

    public void displayMessage(String toastString){
        Toast.makeText(context, toastString, Toast.LENGTH_LONG).show();
    }


    public void popUpRentalAvailable(Rental rental, final Integer inventoryid){
        AlertDialog.Builder myAlert = new AlertDialog.Builder(context);
        myAlert.setMessage("Do you agree to retun "+ rental.getTitle())
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        SharedPrefferenceController sharedPrefferenceController = new SharedPrefferenceController(context);
                        makeRentalreturn(inventoryid, Integer.parseInt(sharedPrefferenceController.getID()));
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

    public void popUpRentalNnavilable(Rental rental){
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

    @Override
    public void onAttach(Activity activity) {
        // TODO Auto-generated method stub
        super.onAttach(activity);
        context=activity;
    }

    @Override
    public void onRentalsAvailible(ArrayList<Rental> rentals) {
        rentalArrayList.clear();
        for(int i = 0; i < rentals.size(); i++) {
            Log.i("return date", rentals.get(i).getReturn_date());
            if(rentals.get(i).getReturn_date() == "null") {
                rentalArrayList.add(rentals.get(i));
            }
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

}