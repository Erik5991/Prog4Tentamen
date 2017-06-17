package com.example.erik.prog4tentamen.activities.Fragments;

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

import com.android.volley.Response;
import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Adapter.FilmAdapter;
import com.example.erik.prog4tentamen.activities.Data.Filmrequest;
import com.example.erik.prog4tentamen.activities.FilmListener;
import com.example.erik.prog4tentamen.activities.Utils.FilmMapper;
import com.example.erik.prog4tentamen.controller.TokenController;
import com.example.erik.prog4tentamen.objects.Film;

import java.util.ArrayList;

/**
 * Created by Erik on 13-6-2017.
 */

public class HomeFragment extends BaseFragment implements FilmListener{

    public final String TAG = this.getClass().getSimpleName();
    public final static String EXTRA_FILM = "FILM";

    private FilmMapper filmMapper;
    private ListView filmListView;
    private TextView textView;
    private Response.Listener listener;
    private BaseAdapter filmAdapter;
    private ArrayList<Film> filmArrayList = new ArrayList<>();
    private TokenController tokenController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment_layout, container, false);
        getActivity().getFragmentManager();
        super.onAttach(getContext());

        Filmrequest filmrequest = new Filmrequest(getContext());
        filmrequest.getAllFilms();

        tokenController = new TokenController(getContext());
        filmListView = (ListView) view.findViewById(R.id.filmListView);
        filmAdapter = new FilmAdapter(getActivity(),  getActivity().getLayoutInflater(), filmArrayList);
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



      //  Film film = new Film("hi", "hi", 55);
        //filmArrayList.add(film);
    }

        private void createInfoDialog() {
            //View for alertDialog
            View mView = mActivity.getLayoutInflater().inflate(R.layout.detail_film_layout, null);
            AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(mView).create();
            dialog.show();

    }


    @Override
    public void FilmAvailable(ArrayList<Film> filmArrayList) {
        Log.i(TAG, "We hebben " + filmArrayList.size() + " items in de lijst");

        filmArrayList.clear();
        for(int i = 0; i < filmArrayList.size(); i++) {
            filmArrayList.add(filmArrayList.get(i));
        }
        filmAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFilmAvailible(Film film) {
      //  filmArrayList.add(film);
      //  filmAdapter.notifyDataSetChanged();

    }

}
