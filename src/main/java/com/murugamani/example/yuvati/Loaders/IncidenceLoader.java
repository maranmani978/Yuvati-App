package com.murugamani.example.yuvati.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.murugamani.example.yuvati.QueryUtils;
import com.murugamani.example.yuvati.data.Incidence;

import java.util.List;

/**
 * Created by Murugamani on 3/20/2018.
 */

public class IncidenceLoader extends AsyncTaskLoader<List<Incidence>> {

    private int type,option;
    private String mUrl;

    public IncidenceLoader(Context context, int n, int opt , String url) {
        super(context);
        type = n;
        option = opt;
        mUrl = url;
    }

    @Override
    protected void onStartLoading() { forceLoad(); }

    @Override
    public List<Incidence> loadInBackground() {
        if (mUrl == null){ return null; }

        String json = QueryUtils.buildUrl(mUrl);
        Log.e("Mani","Inc loader");

        if (type == 1){
            return QueryUtils.extractListIncidence2000(json,option);
        }

        return null;
    }
}
