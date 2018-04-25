package com.daracul.android.booklistingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText findEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findEditText = (EditText) findViewById(R.id.find_book_edittext);
    }

    public void onFindButtonClick(View view) {
        String typedText = findEditText.getText().toString();
        if (!TextUtils.isEmpty(typedText)){
            Intent intent = new Intent(this, ListBookActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT,typedText);
            startActivity(intent);
        }

    }
}
