package com.example.erik.prog4tentamen.activities.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Adapter.FilmAdapter;
import com.example.erik.prog4tentamen.activities.Objects.Film;

import java.util.ArrayList;

/**
 * Created by Erik on 13-6-2017.
 */

public class HomeFragment extends BaseFragment {

    public final String TAG = this.getClass().getSimpleName();
    public final static String EXTRA_FILM = "FILM";

    private ListView filmListView;
    private TextView textView;
    private BaseAdapter filmAdapter;
    private ArrayList<Film> filmArrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment_layout, container, false);
        getActivity().getFragmentManager();

        filmListView = (ListView) view.findViewById(R.id.filmListView);
        filmAdapter = new FilmAdapter(this, getActivity().getLayoutInflater(), filmArrayList);
        filmListView.setAdapter(filmAdapter);
        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film film = filmArrayList.get(position);
               // Intent intent = new Intent(getActivity().getApplicationContext(), FilmDetailActivity.class);
             //   intent.putExtra(EXTRA_FILM,film.toString());
              //  startActivity(intent);
                createInfoDialog();
            }
        });

        filmAdapter.notifyDataSetChanged();
    return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        Film film = new Film("hi", "hi", 55);
        filmArrayList.add(film);
    }

        private void createInfoDialog() {
            //View for alertDialog
            View mView = mActivity.getLayoutInflater().inflate(R.layout.detail_film_layout, null);
            AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(mView).create();
            dialog.show();

    }

}