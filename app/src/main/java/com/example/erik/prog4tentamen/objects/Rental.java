package com.example.erik.prog4tentamen.objects;

import java.util.Date;

/**
 * Created by Teunvz on 18-6-2017.
 */

public class Rental {
    private Integer inventoryid, rental_duration;
    private Double rental_rate;
    private String title, rental_date, return_date;

    public Rental(Integer inventoryid, Integer rental_duration, Double rental_rate, String title, String rental_date, String return_date){
        this.inventoryid = inventoryid;
        this.rental_duration = rental_duration;
        this.rental_rate = rental_rate;
        this.title = title;
        this.rental_date = rental_date;
        this.return_date = return_date;
    }

    public Integer getInventoryID(){
        return inventoryid;
    }

    public Integer getRental_duration(){
        return rental_duration;
    }

    public Double getRental_rate(){
        return rental_rate;
    }

    public String getTitle(){
        return title;
    }

    public String getRental_date(){
        return rental_date;
    }

    public String getReturn_date(){
        return return_date;
    }
}
