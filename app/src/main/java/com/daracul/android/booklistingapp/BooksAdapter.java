package com.daracul.android.booklistingapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amalakhov on 25.04.2018.
 */

public class BooksAdapter extends ArrayAdapter<Book> {
    public static final String LOG_TAG = BooksAdapter.class.getSimpleName();

    public BooksAdapter(@NonNull Context context, @NonNull List<Book> books) {
        super(context, 0, books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null){
            listViewItem = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }
        Book book = getItem(position);

        TextView titleTextView = (TextView) listViewItem.findViewById(R.id.book_title);
        titleTextView.setText(book.getTitle());

        TextView authorTextView = (TextView) listViewItem.findViewById(R.id.author);
        authorTextView.setText(book.getAuthor());

        ImageView imageView = (ImageView) listViewItem.findViewById(R.id.imageView);
        new DownloadImageTask(imageView).execute(book.getImageUrl());

        return listViewItem;
    }
}
