package com.murugamani.example.yuvati.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

//import com.murugamani.example.yuvati.Fragments.ViolenceIncidenceFragment;
import com.murugamani.example.yuvati.Fragments.ViolenceIncidenceFragment;
import com.murugamani.example.yuvati.Fragments.ViolenceStateFragment;

/**
 * Created by Murugamani on 3/18/2018.
 */

public class ViolenceTabPageAdapter extends FragmentStatePagerAdapter {

    public ViolenceTabPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ViolenceStateFragment();
            case 1:
                return new ViolenceIncidenceFragment();
        }

        return null;
    }

    @Override
    public int getItemPosition(Object object) {
        return object.hashCode();
    }

    @Override
    public int getCount() {
        return 2;
    }
}
