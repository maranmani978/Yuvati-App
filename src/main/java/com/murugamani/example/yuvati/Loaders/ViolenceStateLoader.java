package com.murugamani.example.yuvati.Loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.murugamani.example.yuvati.QueryUtils;
import com.murugamani.example.yuvati.data.StateCrime;

import java.util.List;

/**
 * Created by Murugamani on 3/18/2018.
 */

public class ViolenceStateLoader extends AsyncTaskLoader<List<StateCrime>> {

    private String mUrl;
    private int type,option;

    public ViolenceStateLoader(Context context, int n, int opt, String url) {
        super(context);
        mUrl = url;
        type = n;
        option = opt;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<StateCrime> loadInBackground() {
        if (mUrl == null){ return null; }

        if (type == 1) {
            String json = QueryUtils.buildUrl(mUrl);
            return QueryUtils.extract1999Json(json,option);
        }else {
            String json = QueryUtils.buildUrl(mUrl);
            return QueryUtils.extract2000Json(json,option);
        }
    }
}
