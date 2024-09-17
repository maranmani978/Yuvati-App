package com.murugamani.example.yuvati.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.murugamani.example.yuvati.R;
import com.murugamani.example.yuvati.data.Incidence;

import java.util.List;

/**
 * Created by Murugamani on 3/20/2018.
 */

public class ViolenceIncidenceAdapter extends ArrayAdapter<Incidence> {

    public ViolenceIncidenceAdapter(@NonNull Context context, @NonNull List<Incidence> objects) {
        super(context, 0, objects);
        Log.e("Mani","hello");
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.incidence_list_state_item,parent,false);
        }
        Log.e("Mani","hello");
        return convertView;
    }
}
