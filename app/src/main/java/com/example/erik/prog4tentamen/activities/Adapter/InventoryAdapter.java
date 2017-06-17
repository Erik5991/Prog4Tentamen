package com.example.erik.prog4tentamen.activities.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Utils.FilmMapper;
import com.example.erik.prog4tentamen.objects.Film;
import com.example.erik.prog4tentamen.objects.Inventoryid;

import java.util.ArrayList;

/**
 * Created by Erik on 15-6-2017.
 */

public class InventoryAdapter extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private LayoutInflater mInflator;
    private ArrayList<Inventoryid> inventoryArrayList;

    public InventoryAdapter(Context mContext, LayoutInflater mInflator, ArrayList<Inventoryid> inventoryArrayList) {
        this.mContext = mContext;
        this.mInflator = mInflator;
        this.inventoryArrayList = inventoryArrayList;

        Log.i("Inventory adapter", "Komt hier");
        Log.i("inventory size", inventoryArrayList.size() + " hier");
    }

    @Override
    public int getCount() {
        int size = inventoryArrayList.size();
        Log.i(TAG, "getCount() =  " + size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "getItem() at " + position);
        return inventoryArrayList.get(position);
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
            convertView = mInflator.inflate(R.layout.list_inventory_row, null);

            // Maak een ViewHolder en koppel de schermvelden aan de velden uit onze eigen row.
            viewHolder = new ViewHolder();
            viewHolder.textViewInventoryID = (TextView) convertView.findViewById(R.id.textViewInventoryID);
            viewHolder.textViewAvailible = (TextView) convertView.findViewById(R.id.textViewAvailible);

            // Sla de viewholder op in de convertView
            convertView.setTag(viewHolder);

        } else {
            Log.i(TAG, "convertView BESTOND AL - hergebruik");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Inventoryid inventoryid = inventoryArrayList.get(position);
        Log.i("inventoryadapert", inventoryid.toString());
        viewHolder.textViewInventoryID.setText("" + inventoryid.getInventoryid());
        viewHolder.textViewAvailible.setText(inventoryid.getStatus());

        return convertView;
    }

    private static class ViewHolder {
        public TextView textViewInventoryID;
        public TextView textViewAvailible;
    }
}
