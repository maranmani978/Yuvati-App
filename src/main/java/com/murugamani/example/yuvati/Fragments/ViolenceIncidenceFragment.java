package com.murugamani.example.yuvati.Fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.murugamani.example.yuvati.Adapters.ViolenceIncidenceAdapter;
import com.murugamani.example.yuvati.Adapters.ViolenceStateAdapter;
import com.murugamani.example.yuvati.Loaders.IncidenceLoader;
import com.murugamani.example.yuvati.Loaders.ViolenceStateLoader;
import com.murugamani.example.yuvati.R;
import com.murugamani.example.yuvati.data.Incidence;
import com.murugamani.example.yuvati.data.StateCrime;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class ViolenceIncidenceFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<StateCrime>> {

    private static final int INCIDENCE_LOADER_ID = 1;

    private static final String URL_1999 = "https://api.data.gov.in/resource/8bac8b26-c802-43ac-8de5-4d58e18237c6?format=json";
    private static final String INCIDENCE_URL_2000 = "https://api.data.gov.in/resource/1d9859d7-d46b-48c3-882b-c2ed330dfd9e?format=json";
    private static final String INCIDENCE_URL_2001_2012 = "https://api.data.gov.in/resource/7a6f30fb-fbb8-4f6f-b1aa-a4da5ad526b3?format=json";
    private static final String INCIDENCE_URL_2013 = "https://api.data.gov.in/resource/f2809452-371e-4536-9b5d-010dbfe9f8b2?format=json";
    private static final String INCIDENCE_URL_2014 = "https://api.data.gov.in/resource/8a801c01-ed02-4d3f-9d64-b54a2bbfd151?format=json";

    private Spinner selectYear,selectCategory;

    private ListView listView;
    private static String BASE_URL;
    private LoaderManager loaderManager;
    private ProgressBar progressBar;
    private int year,option;
    private ViolenceIncidenceAdapter violenceIncidenceAdapter;
    private ViolenceStateAdapter violenceStateAdapter2;
    private boolean visibility;
    private int check1 = 0,check2 = 0;

    public ViolenceIncidenceFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_violence_incidence, container, false);

        loaderManager = getLoaderManager();
        selectYear = rootView.findViewById(R.id.select_year);
        selectCategory = rootView.findViewById(R.id.select_category);

        listView = rootView.findViewById(R.id.incidence_list);
        progressBar = rootView.findViewById(R.id.progress_indicator);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),R.array.incidence_year,R.layout.spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        selectYear.setAdapter(adapter);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(),R.array.categories,R.layout.spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        selectCategory.setAdapter(adapter1);
        Log.e("Mani","Maran0");

        selectYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (check1++ > 0) {
                    switch (adapterView.getItemAtPosition(i).toString()) {
                        case "2000":
                            year = 1;
                            selectCategory.setVisibility(View.VISIBLE);
                            BASE_URL = INCIDENCE_URL_2000;
                            Log.e("Mani", "Mani");
                            Visible();
                            break;
                        case "2001-2012":
                            year = 2;
                            selectCategory.setVisibility(View.GONE);
                            BASE_URL = INCIDENCE_URL_2001_2012;
                            Visible();
                            break;
                        case "2013":
                            year = 3;
                            selectCategory.setVisibility(View.GONE);
                            BASE_URL = INCIDENCE_URL_2013;
                            Visible();
                            break;
                        case "2014":
                            year = 4;
                            selectCategory.setVisibility(View.GONE);
                            BASE_URL = INCIDENCE_URL_2014;
                            Visible();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        selectCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Mani", "Maran1");
                if (check2++ > 0) {
                    switch (adapterView.getItemAtPosition(i).toString()) {
                        case "All":
                            option = 1;
                            Visible();
                            break;
                        case "State":
                            option = 2;
                            Visible();
                            break;
                        case "Union Territories":
                            option = 3;
                            Visible();
                            break;
                        case "Cities":
                            option = 4;
                            Visible();
                            break;
                        case "Total":
                            option = 5;
                            Visible();
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibility = isVisibleToUser;
        if (isResumed()) {
            if (isVisibleToUser) {
                Visible();
            } else {
                Invisible();
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (visibility){
            Visible();
        }
    }

    private void Visible(){

        //progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()) {
            violenceIncidenceAdapter = new ViolenceIncidenceAdapter(getContext(),new ArrayList<Incidence>());
            violenceStateAdapter2 = new ViolenceStateAdapter(getContext(),new ArrayList<StateCrime>());
            listView.setAdapter(violenceStateAdapter2);
            Log.e("Mani","visible"+year);

            loaderManager.initLoader(INCIDENCE_LOADER_ID,null, this);
        }else {
            Toast.makeText(getContext(),"No network",Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
        }
    }

    private void Invisible(){
        loaderManager.destroyLoader(INCIDENCE_LOADER_ID);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (visibility) {
            Log.e("Mani it","is visible");
        }
        Log.e("Mani it","is resume");
    }

    @Override
    public Loader<List<StateCrime>> onCreateLoader(int id, Bundle args) {
        return new ViolenceStateLoader(getContext(), 1, 1, URL_1999);
    }

    @Override
    public void onLoadFinished(Loader<List<StateCrime>> loader, List<StateCrime> data) {
        Invisible();
        if (data != null){
            violenceStateAdapter2.clear();
            violenceStateAdapter2.addAll(data);
            Log.e("Mani","Ma"+data.get(0).getName());
        }
        listView.setVisibility(View.VISIBLE);
//        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(Loader<List<StateCrime>> loader) {

    }
/*
    @Override
    public Loader<List<Incidence>> onCreateLoader(int id, Bundle args) {
        if (year == 0 || option == 0 || TextUtils.isEmpty(BASE_URL)){
            year = 1;
            option = 1;
            BASE_URL = INCIDENCE_URL_2000;
        }
        Log.e("Mani","ma"+year+option);
        return new IncidenceLoader(getContext(),year,option,BASE_URL);
    }

    @Override
    public void onLoadFinished(Loader<List<Incidence>> loader, List<Incidence> data) {
        if (data != null){
            Log.e("M","m"+data.get(0).getName());
            violenceIncidenceAdapter.clear();
            violenceIncidenceAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<Incidence>> loader) {
        loader.reset();
    }
*/
}
