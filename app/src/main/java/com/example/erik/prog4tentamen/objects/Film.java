package com.example.erik.prog4tentamen.objects;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Teunvz on 15-6-2017.
 */

public class Film implements Serializable {
    private int film_id;
    private int rental_duration;
    private int length;
    private String title, description, release_year, rating;
    private ArrayList<String> special_features;
    private Double rental_rate, replacement_cost;

    public Film(){}

    public Film(Integer film_id, Integer rental_duration, Integer length, String title, String description, String release_year, String rating,  Double rental_rate, Double replacement_cost){
        this.film_id = film_id;
        this.rental_duration = rental_duration;
        this.length = length;
        this.title = title;
        this.description = description;
        this.release_year = release_year;
        this.rating = rating;
        this.special_features = special_features;
        this.rental_rate = rental_rate;
        this.replacement_cost = replacement_cost;
    }


    public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }

    public int getRental_duration() {
        return rental_duration;
    }

    public void setRental_duration(int rental_duration) {
        this.rental_duration = rental_duration;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_year() {
        return release_year;
    }

    public void setRelease_year(String release_year) {
        this.release_year = release_year;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public ArrayList<String> getSpecial_features() {
        return special_features;
    }

    public void setSpecial_features(ArrayList<String> special_features) {
        this.special_features = special_features;
    }

    public Double getRental_rate() {
        return rental_rate;
    }

    public void setRental_rate(Double rental_rate) {
        this.rental_rate = rental_rate;
    }

    public Double getReplacement_cost() {
        return replacement_cost;
    }

    public void setReplacement_cost(Double replacement_cost) {
        this.replacement_cost = replacement_cost;
    }

}
