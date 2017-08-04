package com.inventaire2.inventaire2.LesVues;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.inventaire2.inventaire2.R;
import com.inventaire2.inventaire2.LesModels.RecyclerViewAdapter;
import com.inventaire2.inventaire2.LesDonnees.Remplissage;

/**
 * Created by Fal on 27/07/2017.
 */

public class Information extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.information_add);
// Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(RecyclerViewAdapter.EXTRA_MESSAGE);
        int message2 = intent.getIntExtra(Remplissage.EXTRA_MESSAGE2,0);
        int message3= intent.getIntExtra(Remplissage.EXTRA_MESSAGE3,0);



        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("le nom est : " +message + " , le prix : " + Integer.toString(message2) +"et la quantite "+ Integer.toString(message3));

    }

}