package com.murugamani.example.yuvati.Fragments;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.murugamani.example.yuvati.Adapters.ViolenceStateAdapter;
import com.murugamani.example.yuvati.Loaders.ViolenceStateLoader;
import com.murugamani.example.yuvati.R;
import com.murugamani.example.yuvati.data.StateCrime;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class ViolenceStateFragment extends Fragment implements AdapterView.OnItemSelectedListener,LoaderManager.LoaderCallbacks<List<StateCrime>>{

    private static final String URL_1999 = "https://api.data.gov.in/resource/8bac8b26-c802-43ac-8de5-4d58e18237c6?format=json";

    private static final String URL_2000 = "https://api.data.gov.in/resource/1b11ff57-7b44-4ef0-a290-b0de682f1ba3?format=json";

    private static final int STATE_LOADER_ID = 1;

    private LoaderManager loaderManager;
    private View rootView;
    private ListView listView;
    private RadioGroup radioGroup;
    private RadioButton r1,r2;
    private int selected_id,option;
    private ViolenceStateAdapter violenceStateAdapter;
    private boolean visibility;

    private Spinner category;
    private ProgressBar progressBar;

    public ViolenceStateFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_violence_state,container,false);

        loaderManager = getLoaderManager();
        category = rootView.findViewById(R.id.spinner);
        listView = rootView.findViewById(R.id.list);
        radioGroup = rootView.findViewById(R.id.year);
        r1 = rootView.findViewById(R.id.year_1999);
        r2 = rootView.findViewById(R.id.year_2000);
        progressBar = rootView.findViewById(R.id.progress_indicator);
        selected_id = radioGroup.getCheckedRadioButtonId();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(rootView.getContext(), R.array.categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        category.setAdapter(adapter);

        category.setOnItemSelectedListener(this);

        option = 0;
        category.setSelection(0);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_id = radioGroup.getCheckedRadioButtonId();

                Visible();
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selected_id = radioGroup.getCheckedRadioButtonId();
                Visible();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        if (visibility){
            Visible();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        visibility = isVisibleToUser;
        if (isResumed()){
            if (isVisibleToUser){
                Visible();
            }else {
                Invisible();
            }
        }
    }

    private void Visible(){
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        ConnectivityManager connectivityManager = (ConnectivityManager)getActivity().getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo!=null && networkInfo.isConnected()) {
            violenceStateAdapter = new ViolenceStateAdapter(getContext(),new ArrayList<StateCrime>());
            listView.setAdapter(violenceStateAdapter);

            loaderManager.initLoader(STATE_LOADER_ID,null, this);
        }else {
            Toast.makeText(getContext(),"No network",Toast.LENGTH_LONG).show();
            progressBar.setVisibility(View.GONE);
            listView.setVisibility(View.GONE);
        }
    }

    private void Invisible(){
        loaderManager.destroyLoader(STATE_LOADER_ID);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getItemAtPosition(i).toString()){
            case "All":
                if (option != 0){
                    option = 1;
                    Visible();
                }
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

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {  }

    @Override
    public Loader<List<StateCrime>> onCreateLoader(int id, Bundle args) {
            if (selected_id == R.id.year_1999) {
                if (option == 0){
                    return new ViolenceStateLoader(getContext(), 1, 1, URL_1999);
                }else {
                    return new ViolenceStateLoader(getContext(),1,option,URL_1999);
                }
            } else {
                if (option == 0){
                    return new ViolenceStateLoader(getContext(), 2, 1, URL_2000);
                }else {
                    return new ViolenceStateLoader(getContext(), 2, option, URL_2000);
                }
            }
    }

    @Override
    public void onLoadFinished(Loader<List<StateCrime>> loader, List<StateCrime> data) {
        if (data != null){
            violenceStateAdapter.clear();
            violenceStateAdapter.addAll(data);
        }
        listView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        loaderManager.destroyLoader(STATE_LOADER_ID);
    }

    @Override
    public void onLoaderReset(Loader<List<StateCrime>> loader) {
        violenceStateAdapter.clear();
        loader.reset();
    }
}
