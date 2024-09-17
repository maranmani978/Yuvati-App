package com.murugamani.example.yuvati.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.murugamani.example.yuvati.R;
import com.murugamani.example.yuvati.data.StateCrime;

import java.util.List;

/**
 * Created by Murugamani on 3/18/2018.
 */

public class ViolenceStateAdapter extends ArrayAdapter<StateCrime> {

    public ViolenceStateAdapter(@NonNull Context context, @NonNull List<StateCrime> objects) {
        super(context,0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.violence_state_item,parent,false);
        }

        StateCrime stateCrime = getItem(position);

        TextView name,rank,incidence,contribution,population,crimes;

        name = convertView.findViewById(R.id.state_ut_city);
        rank = convertView.findViewById(R.id.rank);
        incidence = convertView.findViewById(R.id.incidence);
        contribution = convertView.findViewById(R.id.contribution);
        population = convertView.findViewById(R.id.population);
        crimes = convertView.findViewById(R.id.total_cognizable);

        name.setText(stateCrime.getName());
        if (stateCrime.getRank().equals("NA")) {
            rank.setText("");
        }else {
            rank.setText("#"+stateCrime.getRank());
        }
        incidence.setText(stateCrime.getIncidence());
        contribution.setText(stateCrime.getPercentage());
        population.setText(stateCrime.getPopulation());
        crimes.setText(stateCrime.getTotal());

        return convertView;
    }
}
