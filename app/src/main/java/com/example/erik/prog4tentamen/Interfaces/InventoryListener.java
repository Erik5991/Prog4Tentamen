package com.example.erik.prog4tentamen.Interfaces;

import com.example.erik.prog4tentamen.objects.Inventoryid;

import java.util.ArrayList;

/**
 * Created by Teunvz on 19-6-2017.
 */

public interface InventoryListener {
    void onInventoryAvailible(ArrayList<Inventoryid> inventoryid);
}
