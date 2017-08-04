package com.inventaire2.inventaire2.LesModels;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.inventaire2.inventaire2.LesVues.Information;
import com.inventaire2.inventaire2.R;

import java.util.List;

/**
 * Created by Fal on 26/07/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public static final String EXTRA_MESSAGE = "com.bakeli3.test3.MESSAGE";
    public static final String EXTRA_MESSAGE2 = "com.bakeli3.test3.MESSAGE2";

    private LayoutInflater inflater;
    private Context context;
    private List<Article> articlesList;



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

    @Override
    public int getItemCount()
    {
        return articlesList.size();
    }



    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            name=(TextView)itemView.findViewById(R.id.name);
            price=(TextView)itemView.findViewById(R.id.price);
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
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
