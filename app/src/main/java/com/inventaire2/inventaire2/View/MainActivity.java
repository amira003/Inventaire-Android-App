package com.inventaire2.inventaire2.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.inventaire2.inventaire2.Configuration.GsonService;
import com.inventaire2.inventaire2.Models.Article;
import com.inventaire2.inventaire2.Controllers.RealmHelper;
import com.inventaire2.inventaire2.R;
import com.inventaire2.inventaire2.Controllers.RecyclerViewAdapter;
import com.inventaire2.inventaire2.Data.Remplissage;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Realm realm;
    private RecyclerView mRecyclerView;
    private RecyclerViewAdapter mAdapter;
    private List<Article> articlesList = new ArrayList<>();
    private List<Article> model;
    RealmHelper rh;
    private static final String TAG2 ="MainActivity" ;
    public final static String API = "http://192.168.1.30:8080/laravelNew/public/api/";


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

        // Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GsonService git = retrofit.create(GsonService.class);

        Call<List<Article>> call = git.groupList();

        call.enqueue(new Callback<List<Article>>() {

            @Override
            public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                if (response.isSuccessful()) {
                    Log.d("APIPlug", "Successfully " + response.body().toString());
                    model = response.body();
                    articlesList.addAll(model);
                    initialiseData();

              /*      if (model.size() > 0) {
                        initialiseData();
                    } else {
                        Log.d("APIPlug", "No item found");
                    }                commment */
                }
                else
                   {
                    Log.d("APIDefault", "Not success " + response.errorBody());
                  }

            }
            @Override
            public void onFailure(Call<List<Article>> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage()+ ""+t.getStackTrace(), Toast.LENGTH_SHORT).show();
                Log.d(TAG2, "onFailure: "+t.getMessage()+ ""+t.getStackTrace());

            }
        });

        // Fin Retrofit

     //   initialiseData();
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
        Log.d("APIPlug", "Show List");
  /*       articlesList = rh.getListArticle();

        mAdapter = new RecyclerViewAdapter(this, articlesList);
        mRecyclerView.setAdapter(mAdapter);  */


        mAdapter = new RecyclerViewAdapter(MainActivity.this, articlesList);
        mRecyclerView.setAdapter(mAdapter);

    }

}


