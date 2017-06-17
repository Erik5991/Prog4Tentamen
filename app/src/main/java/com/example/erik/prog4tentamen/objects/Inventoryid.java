package com.example.erik.prog4tentamen.objects;

/**
 * Created by Teunvz on 17-6-2017.
 */

public class Inventoryid {
    private Integer inventoryid;
    private String status;

    public Inventoryid(Integer inventoryid, String status){
        this.inventoryid = inventoryid;
        this.status = status;
    }

    public void setInventoryid(Integer inventoryid){
        this.inventoryid = inventoryid;
    }

    public Integer getInventoryid(){
        return inventoryid;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    public String toString(){
        return inventoryid + " " + status + " ";
    }
}
