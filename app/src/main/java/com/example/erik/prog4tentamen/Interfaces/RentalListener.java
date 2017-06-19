package com.example.erik.prog4tentamen.Interfaces;

import com.example.erik.prog4tentamen.objects.Rental;

import java.util.ArrayList;

/**
 * Created by Teunvz on 19-6-2017.
 */

public interface RentalListener {
    void onRentalsAvailible(ArrayList<Rental> rentals);

    void onRentalMade(String status);

    void onRentalReturned(String status);
}
