package com.example.erik.prog4tentamen.activities.Fragments;

import android.app.Activity;
import android.content.Context;
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
 * Created by Erik on 16-6-2017.
 */

public class RentHistoryFragment extends BaseFragment {

    private ListView filmListView;
    private TextView textView;
    private BaseAdapter filmAdapter;
    private ArrayList<Film> filmArrayList = new ArrayList<>();
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.rent_history_layout, container, false);
        getActivity().getFragmentManager();

     //   LayoutInflater inflaterv = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );


        filmListView = (ListView) view.findViewById(R.id.filmListView);
        filmAdapter = new FilmAdapter(getActivity(), getActivity().getLayoutInflater(), filmArrayList);
        filmListView.setAdapter(filmAdapter);
        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film film = filmArrayList.get(position);
                // Intent intent = new Intent(getActivity().getApplicationContext(), FilmDetailActivity.class);
                //   intent.putExtra(EXTRA_FILM,film.toString());
                //  startActivity(intent);
                returnMovieDialog();
            }
        });

        filmAdapter.notifyDataSetChanged();
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


        Film film = new Film("hi", "hi", 55);
        filmArrayList.add(film);
    }

    private void returnMovieDialog() {
        //View for alertDialog
        View mView = mActivity.getLayoutInflater().inflate(R.layout.detail_film_layout, null);
        AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(mView).create();
        dialog.show();

    }}