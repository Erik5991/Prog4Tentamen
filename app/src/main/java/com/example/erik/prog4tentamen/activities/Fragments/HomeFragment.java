package com.example.erik.prog4tentamen.activities.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Activity.FilmDetailActivity;
import com.example.erik.prog4tentamen.activities.Adapter.FilmAdapter;
import com.example.erik.prog4tentamen.activities.Data.Filmrequest;
import com.example.erik.prog4tentamen.activities.FilmListener;
import com.example.erik.prog4tentamen.activities.Utils.FilmMapper;
import com.example.erik.prog4tentamen.controller.TokenController;
import com.example.erik.prog4tentamen.objects.Film;
import com.example.erik.prog4tentamen.objects.Inventoryid;

import java.util.ArrayList;

/**
 * Created by Erik on 13-6-2017.
 */

public class HomeFragment extends BaseFragment implements Filmrequest.FilmListener{

    public final String TAG = this.getClass().getSimpleName();

    private FilmMapper filmMapper;
    private ListView filmListView;
    private TextView textView;
    private BaseAdapter filmAdapter;
    private ArrayList<Film> filmArrayList =new ArrayList<>();
    private TokenController tokenController;
    private Filmrequest.FilmListener listener;
    private EditText zoekString;
    private Button zoekKnop;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment_layout, container, false);
        getActivity().getFragmentManager();
        super.onAttach(getContext());


        listener = this;

        tokenController = new TokenController(getContext());
        filmListView = (ListView) view.findViewById(R.id.filmListView);
        filmAdapter = new FilmAdapter(getActivity(),  getActivity().getLayoutInflater(), filmArrayList);
        filmListView.setAdapter(filmAdapter);
        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film film = filmArrayList.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), FilmDetailActivity.class);
                intent.putExtra("film", film);
                startActivity(intent);
//                createInfoDialog();
            }
        });

        filmAdapter.notifyDataSetChanged();
    return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        zoekString = (EditText) getView().findViewById(R.id.editTextStrig);
        zoekKnop = (Button) getView().findViewById(R.id.zoekKnop);
        zoekKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFilmsByName(zoekString.getText().toString());
            }
        });

        getFilms();
        getRentalByID(1);
    }

    private void createInfoDialog() {
        //View for alertDialog
        View mView = mActivity.getLayoutInflater().inflate(R.layout.detail_film_layout, null);
        AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(mView).create();
        dialog.show();
    }

    @Override
    public void onFilmsAvailible(ArrayList<Film> films) {

        filmArrayList.clear();
        for(int i = 0; i < films.size(); i++) {
            filmArrayList.add(films.get(i));
        }
        filmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onInventoryAvailible(ArrayList<Inventoryid> inventoryid) {
        Log.i("Grote inventory id list" , inventoryid.size() + "");
        Log.i("alle items", inventoryid.toString());
    }

    @Override
    public void onFilmAvailible(Film film) {
        Log.i("Film titele", film.getTitle());
    }

    @Override
    public void onToDosError(String message) {

    }

    private void getFilms(){
        Filmrequest request = new Filmrequest(getContext(), this);
        request.getAllFilms();
    }

    private void getFilmsByName(String title){
        Filmrequest request = new Filmrequest(getContext(), this);
        request.getAllFilmsByName(title);
    }

    private void getRentalByID(Integer filmID){
        Filmrequest request = new Filmrequest(getContext(), this);
        request.getInventoryByID(filmID);
    }
}

