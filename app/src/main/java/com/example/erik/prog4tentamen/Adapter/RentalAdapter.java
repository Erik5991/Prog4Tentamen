package com.example.erik.prog4tentamen.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.objects.Rental;

import java.util.ArrayList;

/**
 * Created by Erik on 15-6-2017.
 */

public class RentalAdapter extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private LayoutInflater mInflator;
    private ArrayList<Rental> rentalArrayList;

    public RentalAdapter(Context mContext, LayoutInflater mInflator, ArrayList<Rental> rentalArrayList) {
        this.mContext = mContext;
        this.mInflator = mInflator;
        this.rentalArrayList = rentalArrayList;
    }

    @Override
    public int getCount() {
        int size = rentalArrayList.size();
        Log.i(TAG, "getCount() =  " + size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "getItem() at " + position);
        return rentalArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, "getView at " + position);

        ViewHolder viewHolder;
        if (convertView == null) {

            Log.i(TAG, "convertView is NULL - nieuwe maken");

            // Koppel de convertView aan de layout van onze eigen row
            convertView = mInflator.inflate(R.layout.list_rental_row, null);

            // Maak een ViewHolder en koppel de schermvelden aan de velden uit onze eigen row.
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            viewHolder.textViewDate = (TextView) convertView.findViewById(R.id.textViewRentalDate);
            viewHolder.textViewReturnDate = (TextView) convertView.findViewById(R.id.textViewReturnDate);
            viewHolder.textViewRentalRate = (TextView) convertView.findViewById(R.id.textViewRentalRate);

            // Sla de viewholder op in de convertView
            convertView.setTag(viewHolder);

        } else {
            Log.i(TAG, "convertView BESTOND AL - hergebruik");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Rental rental = rentalArrayList.get(position);
        String returndate = rental.getReturn_date();
        if(returndate.toString() == "null"){
            returndate = "Not yet returned";
        }

        viewHolder.textViewTitle.setText(rental.getTitle());
        viewHolder.textViewDate.setText(rental.getRental_date().split("T")[0]);
        viewHolder.textViewReturnDate.setText(returndate.split("T")[0]);
        viewHolder.textViewRentalRate.setText("€ " + rental.getRental_rate());

        return convertView;
    }

    private static class ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDate;
        public TextView textViewReturnDate;
        public TextView textViewRentalRate;
    }


}
