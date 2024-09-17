package com.murugamani.example.yuvati.Fragments;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.murugamani.example.yuvati.Adapters.ViolenceTabPageAdapter;
import com.murugamani.example.yuvati.R;

public class ViolenceFragment extends Fragment {

    private View rootView;

    private ViolenceTabPageAdapter violenceTabPageAdapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_violence,container,false);

        viewPager = rootView.findViewById(R.id.container);
        tabLayout = rootView.findViewById(R.id.tabs);

        violenceTabPageAdapter = new ViolenceTabPageAdapter(getActivity().getSupportFragmentManager());
        viewPager.setAdapter(violenceTabPageAdapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

//        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabLayout.setTabTextColors(Color.parseColor("#FF706B6D"), Color.parseColor("#FF4081"));

        return rootView;
    }

}
