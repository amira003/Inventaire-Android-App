package com.inventaire2.inventaire2.LesVues;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.inventaire2.inventaire2.LesModels.Article;
import com.inventaire2.inventaire2.LesModels.RealmHelper;
import com.inventaire2.inventaire2.R;
import com.inventaire2.inventaire2.LesModels.RecyclerViewAdapter;
import com.inventaire2.inventaire2.LesDonnees.Remplissage;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<Article> articlesList = new ArrayList<>();
    RealmHelper rh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Realm realm = Realm.getDefaultInstance();

        rh = new RealmHelper();
        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        mAdapter = new RecyclerViewAdapter(this, articlesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);


        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        initialiseData();
    }


    private void save(){
        Intent intent = new Intent(MainActivity.this, Remplissage.class);
        startActivity(intent);
    }

    private void delete()
    {

        setContentView(R.layout.total_aff);

        int subTotal =0;
        for(Article p : articlesList) {

            subTotal += p.Price * p.Quantite ;
        }

        TextView productPriceTextView = (TextView) findViewById(R.id.textView2);
        productPriceTextView.setText("le total des prix est : " + Integer.toString(subTotal));
    }


    //Menu début
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                save();
                return true;
            case R.id.action_delete:
                delete();
                return true;
            case R.id.action_settings:
                return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void initialiseData() {

         articlesList = rh.getListArticle();

        mAdapter = new RecyclerViewAdapter(this, articlesList);
        mRecyclerView.setAdapter(mAdapter);

    }

}


