package com.example.erik.prog4tentamen.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.erik.prog4tentamen.Interfaces.FilmListener;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.Activity.FilmDetailActivity;
import com.example.erik.prog4tentamen.Adapter.FilmAdapter;
import com.example.erik.prog4tentamen.Data.Filmrequest;
import com.example.erik.prog4tentamen.controller.SharedPrefferenceController;
import com.example.erik.prog4tentamen.objects.Film;

import java.util.ArrayList;

/**
 * Created by Erik on 13-6-2017.
 */

public class HomeFragment extends BaseFragment implements FilmListener{

    private ListView filmListView;
    private BaseAdapter filmAdapter;
    private ArrayList<Film> filmArrayList =new ArrayList<>();
    private EditText zoekString;
    private Button zoekKnop;
    private Integer count, offset;
    private Boolean filmsloading = true;
    private SharedPrefferenceController sharedPrefferenceController;
    private String title = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment_layout, container, false);
        getActivity().getFragmentManager();
        super.onAttach(getContext());

        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        filmListView = (ListView) getView().findViewById(R.id.filmListView);
        filmAdapter = new FilmAdapter(getActivity(),  getActivity().getLayoutInflater(), filmArrayList);
        filmListView.setAdapter(filmAdapter);
        filmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Film film = filmArrayList.get(position);
                Intent intent = new Intent(getActivity().getApplicationContext(), FilmDetailActivity.class);
                intent.putExtra("film", film);
                startActivity(intent);
            }
        });

        filmAdapter.notifyDataSetChanged();

        zoekString = (EditText) getView().findViewById(R.id.editTextStrig);
        zoekKnop = (Button) getView().findViewById(R.id.zoekKnop);
        zoekKnop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFilmsByName(zoekString.getText().toString(), count, offset);
                filmArrayList.clear();
                title = zoekString.getText().toString();
            }
        });

        sharedPrefferenceController = new SharedPrefferenceController(getContext());
        if(sharedPrefferenceController.getCount() == null){
            sharedPrefferenceController.setCount(10);
        }
        count = Integer.parseInt(sharedPrefferenceController.getCount());
        offset = 0;

        getFilms(count, offset);
        filmsloading = true;

            filmListView.setOnScrollListener(new AbsListView.OnScrollListener() {

                public void onScrollStateChanged(AbsListView view, int scrollState) {

                }

                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {

                    if (firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0) {
                        if (!filmsloading) {
                            filmsloading = true;
                            offset = offset + count;
                            if(!title.isEmpty()){
                                getFilmsByName(title, count, offset);
                            }
                            else {
                                getFilms(count, offset);
                            }
                        }
                    }
                }
            });
    }

    @Override
    public void onResume()
    {
        super.onResume();
        if(!filmsloading) {
            filmsloading = true;
            count = Integer.parseInt(sharedPrefferenceController.getCount());
            offset = 0;
            filmArrayList.clear();
            if (!title.isEmpty()) {
                getFilmsByName(title, count, offset);
            } else {
                getFilms(count, offset);
            }
        }
    }

    @Override
    public void onFilmsAvailible(ArrayList<Film> films) {

        for(int i = 0; i < films.size(); i++) {
            filmArrayList.add(films.get(i));
        }
        filmAdapter.notifyDataSetChanged();
        filmsloading = false;
    }

    @Override
    public void onFilmAvailible(Film film) {

    }

    private void getFilms(Integer count, Integer offset){
        Filmrequest request = new Filmrequest(getContext(), this);
        request.getAllFilms(count, offset);
    }

    private void getFilmsByName(String title, Integer count, Integer offset){
        Filmrequest request = new Filmrequest(getContext(), this);
        request.getAllFilmsByName(title, count, offset);

    }
}