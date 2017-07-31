package com.inventaire2.inventaire2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Fal on 28/07/2017.
 */

public class PrixTotal extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.total_aff);
// Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(RecyclerViewAdapter.EXTRA_MESSAGE);
        String message2 = intent.getStringExtra(RecyclerViewAdapter.EXTRA_MESSAGE2);


        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(message);

    }
}