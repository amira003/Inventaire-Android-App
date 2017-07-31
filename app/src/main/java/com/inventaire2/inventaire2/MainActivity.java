package com.inventaire2.inventaire2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<Article> articlesList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerView);

        mAdapter = new RecyclerViewAdapter(this,articlesList);
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

        Intent intent = getIntent();
        String message = intent.getStringExtra(Remplissage.EXTRA_MESSAGE);
        int message2 = intent.getIntExtra(Remplissage.EXTRA_MESSAGE2,0);
        int message3= intent.getIntExtra(Remplissage.EXTRA_MESSAGE3,0);


        Article article = new Article(message, message2,message3);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,2);
        articlesList.add(article);
        article = new Article("Tee-Shirt", 2000,2);
        articlesList.add(article);
        article = new Article("Pantalon", 1500,3);
        articlesList.add(article);
        article = new Article("Veste cuir", 8000,4);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,1);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,2);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,3);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,3);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,1);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,2);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,3);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,3);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,1);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,2);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,3);
        articlesList.add(article);
        article = new Article("Vintage Logo T-shirt", 1500,3);
        articlesList.add(article);

        mAdapter.notifyItemInserted(0);
        mRecyclerView.scrollToPosition(0);
        Toast.makeText(this,"Ajouté : " + message,Toast.LENGTH_SHORT).show();

    }
}


