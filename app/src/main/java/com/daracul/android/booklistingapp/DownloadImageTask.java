package com.daracul.android.booklistingapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;

/**
 * Created by amalakhov on 24.04.2018.
 */

// show The Image in a ImageView
//new DownloadImageTask((ImageView) findViewById(R.id.imageView1))
//        .execute("http://java.sogeti.nl/JavaBlog/wp-content/uploads/2009/04/android_icon_256.png");
//
//public void onClick(View v) {
//        startActivity(new Intent(this, IndexActivity.class));
//        finish();
//
//        }

public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    private ImageView bmImage;
    private static final String LOG_TAG = DownloadImageTask.class.getSimpleName();

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap imageBitmap = null;
        InputStream in = null;
        try {
            in = new java.net.URL(urldisplay).openStream();
            imageBitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with image URL ", e);
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem retrieving the image", e);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return imageBitmap;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
