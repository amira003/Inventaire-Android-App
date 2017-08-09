package com.inventaire2.inventaire2.Data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.inventaire2.inventaire2.Models.Article;
import com.inventaire2.inventaire2.Controllers.RealmHelper;
import com.inventaire2.inventaire2.Controllers.RecyclerViewAdapter;
import com.inventaire2.inventaire2.View.MainActivity;
import com.inventaire2.inventaire2.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

/**
 * Created by Fal on 27/07/2017.
 */

public class Remplissage extends AppCompatActivity{

    public static final String EXTRA_MESSAGE = "com.inventaire2.inventaire2.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.inventaire2.inventaire2.MESSAGE2";
    public static final String EXTRA_MESSAGE3 = "com.inventaire2.inventaire2.MESSAGE3";
    private static final String TAG ="Remplissage" ;
    private Realm realm;
    private RecyclerViewAdapter mAdapter;
    public List<Article> articlesList = new ArrayList<>();
    public final static String API = "http://192.168.1.30:8080/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remplissage_add);

        /** Called when the user taps the Send button */
        Button btnSendSMS = (Button) findViewById(R.id.button);

        btnSendSMS.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                    Intent intent = new Intent(Remplissage.this, MainActivity.class);

            EditText editText = (EditText) findViewById(R.id.editText3);
            EditText editText2 = (EditText) findViewById(R.id.editText4);
                EditText editText3 = (EditText) findViewById(R.id.editText);
            String message = editText.getText().toString();
            int message2 = Integer.parseInt(editText2.getText()
                    .toString());
                int message3 = Integer.parseInt(editText3.getText()
                        .toString());


            intent.putExtra(EXTRA_MESSAGE, message);
            intent.putExtra(EXTRA_MESSAGE2, message2);
                intent.putExtra(EXTRA_MESSAGE3, message3);


                //GET DATA
                Article article = new Article();
                article.setName(message);
                article.setPrice(message2);
                article.setQuantite(message3);
                // cela s incrimente Tout seul article.setId(1);

          /*          // Retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    GsonService git = retrofit.create(GsonService.class);
                    Call<List<Article>> call = git.groupList();

                    call.enqueue(new Callback<List<Article>>() {
                        @Override
                        public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                            List<Article> model = response.body();
                            Toast.makeText(Remplissage.this, String.format("OK"), Toast.LENGTH_SHORT).show();
                        }
                        @Override
                        public void onFailure(Call<List<Article>> call, Throwable t) {
                            Toast.makeText(Remplissage.this, t.getMessage()+ ""+t.getStackTrace(), Toast.LENGTH_SHORT).show();
                            Log.d(TAG, "onFailure: "+t.getMessage()+ ""+t.getStackTrace());

                        }
                    });            */



               // Save Data
                RealmHelper helper=new RealmHelper();
                helper.save2(article);

                startActivity(intent);

                finish();


        }

    });
}
}
