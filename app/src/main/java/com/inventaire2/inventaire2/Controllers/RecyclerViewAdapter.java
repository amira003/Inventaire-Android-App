package com.inventaire2.inventaire2.Controllers;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.inventaire2.inventaire2.Activity.MainActivity;
import com.inventaire2.inventaire2.Activity.Remplissage;
import com.inventaire2.inventaire2.Application.GsonService;
import com.inventaire2.inventaire2.Models.Article;
import com.inventaire2.inventaire2.Activity.Information;
import com.inventaire2.inventaire2.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static java.security.AccessController.getContext;

/**
 * Created by Fal on 26/07/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public static final String EXTRA_MESSAGE = "com.bakeli3.test3.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.bakeli3.test3.MESSAGE2";

    private LayoutInflater inflater;
    private Context context;
    private List<Article> articlesList;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView mRecyclerView;
    MainActivity ma;
    private Realm realm;
    public final static String API3 = "http://192.168.1.26:8080/laravelNew/public/api/";



    public RecyclerViewAdapter(Context context, List<Article> articlesList)
    {
        this.context=context;

        inflater= LayoutInflater.from(context);

        this.articlesList = articlesList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row,parent,false);
        ViewHolder mViewHolder = new ViewHolder(itemView);
        return mViewHolder;

    }

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        Article article = articlesList.get(position);
        holder.name.setText(article.getName());
        holder.price.setText(Integer.toString(article.getPrice()));
    }

    public void delete(int position) { //removes the row recyclerview
        articlesList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void deleteRealm(int id, int position) {
        realm = Realm.getDefaultInstance();
        RealmQuery<Article> query = realm.where(Article.class);
        RealmResults result = query.equalTo("Id", id).findAll();

                realm.beginTransaction();
                result.deleteFirstFromRealm();
                realm.commitTransaction();
              articlesList.remove(position);
                 notifyItemRemoved(position);
                notifyItemRangeChanged(position, getItemCount());
                notifyDataSetChanged();
    }

        public int getId(int position)
        {
        return articlesList.get(position).Id;
       }


    public void add2(int position) {
        notifyItemInserted(position);
    }

    @Override
    public int getItemCount()

    {
        return articlesList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView price;
        Button btnDel;

        public ViewHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            name=(TextView)itemView.findViewById(R.id.name);
            price=(TextView)itemView.findViewById(R.id.price);
            btnDel=(Button) itemView.findViewById(R.id.buttonDel);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);


           // delete Products
            btnDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle(" Are you sure  ? ");
                    builder.setNegativeButton("cancel",null);
                    builder.setPositiveButton("confirm", new DialogInterface.OnClickListener(){

                      //  Intent intent = new Intent(context, MainActivity.class);

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            // Retrofit
                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(API3)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            GsonService git = retrofit.create(GsonService.class);

                            final int pi = getId(getAdapterPosition());
                            final int position = getAdapterPosition();


                            Call<List<Article>> call2 = git.group4List(pi);

                            deleteRealm(pi,position); // Delete items form Realm

                            Toast.makeText(context, "Supprimé " , Toast.LENGTH_SHORT).show();

                            call2.enqueue(new Callback<List<Article>>() {

                                @Override
                                public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                                    switch (response.code()) {
                                        case 200:
                                            Log.d("delS", " delete success");
                                            deleteRealm(pi,position); // Delete items form Realm
                                            delete(pi); //calls the method above to delete
                                            break;
                                        default:
                                            Log.d("APIDefault", "Not success3 , delete " + String.valueOf(response.errorBody()));
                                            //For getting error message
                                            Log.d("Error message delete", response.message());
                                            //For getting error code. Code is integer value like 200,404 etc
                                            Log.d("Error code delete", String.valueOf(response.code()));
                                            break;
                                    }
                                }

                                @Override
                                public void onFailure(Call<List<Article>> call, Throwable t) {
                                    Log.d("TAG4", "onFailure4 delete: " + t.getMessage() + "   " + t.getStackTrace());

                                }

                            });
                      //      context.startActivity(intent);
                        }
                    });
                    builder.show();
                }
            });

           // Long Click
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {

                    int po = getId(getAdapterPosition());
                    showDialog(name.getText().toString(),Integer.parseInt(price.getText().toString()),po);

                    return true;// returning true instead of false, works for me
                }
            });

        }

        // dialog
        private void showDialog(String str, int str1,final int p) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle(" Edit ");
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_view, null);
            final EditText edit_dialog = (EditText) view.findViewById(R.id.edit_dialog);
            final EditText edit_dialog1 = (EditText) view.findViewById(R.id.edit_dialog1);
            final EditText edit_dialog2 = (EditText) view.findViewById(R.id.edit_dialog2);
            edit_dialog.setText(str);
            edit_dialog1.setText(Integer.toString(str1));
            edit_dialog2.setText(Integer.toString(0));
            builder.setView(view);
            builder.setNegativeButton("cancel",null);
            builder.setPositiveButton("confirm", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    // Retrofit
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl(API3)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    GsonService git = retrofit.create(GsonService.class);

                    Call<List<Article>> call = git.group3List(p,edit_dialog.getText().toString(),Integer.parseInt(edit_dialog1.getText().toString()),Integer.parseInt(edit_dialog2.getText().toString()));

                    call.enqueue(new Callback<List<Article>>() {

                        @Override
                        public void onResponse(Call<List<Article>> call, Response<List<Article>> response) {
                            if (response.isSuccessful()) {
                                Log.d("APIPlug", "Successfully3 modify " + response.body().toString());
                                List<Article> model = response.body();
                                articlesList.addAll(model);
                                for (Article p : articlesList) {
                                    p.setName(edit_dialog.getText().toString());
                                    p.setPrice(Integer.parseInt(edit_dialog1.getText().toString()));
                                }
                            }
                            else
                            {
                                Log.d("APIDefault", "Not success3 , modify "+ String.valueOf(response.errorBody()));
                                //For getting error message
                                Log.d("Error message",response.message());
                                //For getting error code. Code is integer value like 200,404 etc
                                Log.d("Error code",String.valueOf(response.code()));
                            }

                        }

                        @Override
                        public void onFailure(Call<List<Article>> call, Throwable t) {
                            Log.d("TAG3", "onFailure3 modify: " + t.getMessage() + "   " + t.getStackTrace());


                        }
                    });

                    // Fin Retrofit

                }
            });
            builder.show();
        }



        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Information.class);
            TextView editText = (TextView) itemView.findViewById(R.id.name);
            TextView editText2 = (TextView) itemView.findViewById(R.id.price);

            String message = editText.getText().toString();
            int message2 = Integer.valueOf(editText2.getText()
                    .toString());

            intent.putExtra(EXTRA_MESSAGE, message);
            intent.putExtra(EXTRA_MESSAGE2, message2);

            context.startActivity(intent);
        }
    }

}
