package com.daracul.android.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by amalakhov on 25.04.2018.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private String url;
    public static final String LOG_TAG = BookLoader.class.getSimpleName();

    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        Log.d(LOG_TAG,"TEST: Loader StartLoading method");
        forceLoad();
    }

    @Override
    public List<Book> loadInBackground() {
        Log.d(LOG_TAG,"TEST: Loader loadInBackground method");
        if (url == null) {
            return  null;
        }
        // Create a fake list of earthquake locations.

        return Utils.fetchBookData(url);
    }
}
