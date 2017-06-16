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
import com.example.erik.prog4tentamen.activities.Objects.Film;

import java.util.ArrayList;

/**
 * Created by Erik on 15-6-2017.
 */

public class FilmAdapter extends BaseAdapter {

    private final String TAG = this.getClass().getSimpleName();


    private Context mContext;
    private LayoutInflater mInflator;
    private ArrayList<Film> filmArrayList;

    public FilmAdapter(Context mContext, LayoutInflater mInflator, ArrayList<Film> filmArrayList) {
        this.mContext = mContext;
        this.mInflator = mInflator;
        this.filmArrayList = filmArrayList;
    }

    @Override
    public int getCount() {
        int size = filmArrayList.size();
        Log.i(TAG, "getCount() =  " + size);
        return size;
    }

    @Override
    public Object getItem(int position) {
        Log.i(TAG, "getItem() at " + position);
        return filmArrayList.get(position);
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
            convertView = mInflator.inflate(R.layout.list_film_row, null);

            // Maak een ViewHolder en koppel de schermvelden aan de velden uit onze eigen row.
            viewHolder = new ViewHolder();
            viewHolder.textViewTitle = (TextView) convertView.findViewById(R.id.textViewTitle);
            viewHolder.textViewDescription = (TextView) convertView.findViewById(R.id.textViewDescription);
            viewHolder.textViewDuration = (TextView) convertView.findViewById(R.id.textViewDuration);
           // viewHolder.imageView = (ImageView) convertView.findViewById(R.id.smallImageView);

            // Sla de viewholder op in de convertView
            convertView.setTag(viewHolder);

        } else {
            Log.i(TAG, "convertView BESTOND AL - hergebruik");
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Film film = filmArrayList.get(position);
        viewHolder.textViewTitle.setText("Title" + film.getTitle());
        viewHolder.textViewDescription.setText("Description" + film.getDescription());
        viewHolder.textViewDuration.setText("Duration");
        //new ImageLoader(viewHolder.imageView).execute(product.getSmallImgUrl());

        return convertView;
    }

    private static class ViewHolder {
        public TextView textViewTitle;
        public TextView textViewDescription;
        public TextView textViewDuration;
        public ImageView imageView;
    }


}
