package com.daracul.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListBookActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    public final static String LOG_TAG = ListBookActivity.class.getSimpleName();
    private final static String PRIMARY_URL = "https://www.googleapis.com/books/v1/volumes?";
    private final static String SEARCH_URL_SUFFIX ="q=";
    private final  static String MAX_RESULT_URL_SUFFIX = "&maxResults=";
    private final  static int MAX_RESULT = 10;

    private ProgressBar progressBar;
    private ListView booksListView = null;
    private BooksAdapter adapter;
    private TextView emptyTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_book);

        booksListView = (ListView)findViewById(R.id.list);
        emptyTextView = (TextView) findViewById(R.id.empty);
        progressBar = (ProgressBar)findViewById(R.id.progress_bar);
        emptyTextView = (TextView)findViewById(R.id.empty);
        booksListView.setEmptyView(emptyTextView);

        adapter = new BooksAdapter(this, new ArrayList<Book>());
        booksListView.setAdapter(adapter);

        booksListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book currentBook = adapter.getItem(position);
                String description = currentBook.getDescription();
                if (TextUtils.isEmpty(description)){
                    Toast.makeText(getBaseContext(),R.string.no_description,Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getBaseContext(),description,Toast.LENGTH_LONG).show();
                }

            }
        });


        //checking internet connection and if it's connected starting loader
        ConnectivityManager connectivityManager =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Log.d(LOG_TAG,"TEST: Init Loader");
        if (networkInfo == null || !networkInfo.isConnected()){
            emptyTextView.setText(R.string.no_network);
            progressBar.setVisibility(View.GONE);
        } else getLoaderManager().initLoader(0, null, this);

    }

    private String prepareTextForRequest (String incomeText){
        return incomeText.toLowerCase().replaceAll(" ","+");
    }

    // create complete url String that we will patse from json
    private String createCompleteUrl(String searchText, int maxResult){
        if (!TextUtils.isEmpty(searchText)&&maxResult>0){
            return PRIMARY_URL + SEARCH_URL_SUFFIX +searchText+ MAX_RESULT_URL_SUFFIX +maxResult;
        }
        return null;
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Log.d(LOG_TAG,"TEST: OnCreateLoader Callback");
        String incomingText = getIntent().getStringExtra(Intent.EXTRA_TEXT);
        String completeUrl = createCompleteUrl(prepareTextForRequest(incomingText), MAX_RESULT);
        Log.d(LOG_TAG,"TEST: url: "+completeUrl);
        return new BookLoader(this, completeUrl);
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> books) {
        Log.d(LOG_TAG,"TEST: OnLoadFinished Callback");
        adapter.clear();
        progressBar.setVisibility(View.GONE);
        emptyTextView.setText(R.string.empty);

        if (books != null && !books.isEmpty()){
            adapter.addAll(books);
        }

//        Log.d(LOG_TAG, "TEST: contains of books array: ");
//        for (int i=0; i < books.size(); i++){
//            Log.d(LOG_TAG, "author: "+ books.get(i).getAuthor() + ", Description : " + books.get(i).getDescription() + "title: "
//                    + books.get(i).getTitle() + "imageUrl: " + books.get(i).getImageUrl() +"\n");
//        }
    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {
        Log.d(LOG_TAG,"TEST: onLoaderReset Callback");
        adapter.clear();
    }
}
