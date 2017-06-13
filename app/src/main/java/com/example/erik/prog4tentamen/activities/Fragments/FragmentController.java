package com.example.erik.prog4tentamen.activities.Fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.erik.prog4tentamen.R;
import com.example.erik.prog4tentamen.activities.Activity.HomeActivity;

/**
 * Created by Erik on 13-6-2017.
 */

public class FragmentController {

    public static FragmentManager sFragmentManager;


    public FragmentController(FragmentManager sFragmentManager) {
        FragmentController.sFragmentManager = sFragmentManager;

    }

    public static Fragment getHomeFragment() {
        HomeActivity.sActionBar.setTitle("Films");
        return attemptAddFragment(new HomeFragment(), false);
    }


    private static Fragment attemptAddFragment(Fragment fragment) {
        return attemptAddFragment(fragment, true);
    }

    /**
     * Replaces the fragment in the Frameholder with the new fragment.
     * @param fragment Specify fragment for in Frameholder.
     * @param addToBackStack True if must be added to backstack.
     * @return returns the Fragment which is added to the frameHolder.
     */
    private static Fragment attemptAddFragment(Fragment fragment, boolean addToBackStack) {
        try {
            FragmentTransaction mFragmentTransaction = sFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.content_frame, fragment);
            if (addToBackStack) {
                mFragmentTransaction.addToBackStack(null);
            }
            mFragmentTransaction.commit();
        } catch (Exception e) {
            Log.e("---Fragment error--", "" + e);
        }
        return fragment;
    }



}
