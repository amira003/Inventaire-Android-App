package com.inventaire2.inventaire2.LesModels;

import android.view.View;
import android.widget.TextView;

import android.support.v7.widget.RecyclerView;

import com.inventaire2.inventaire2.R;

/**
 * Created by Fal on 26/07/2017.
 */

public class ViewHolder extends RecyclerView.ViewHolder{
    public TextView name, price;

    public ViewHolder (View view) {
        super (view);
        name = (TextView) view.findViewById(R.id.name);
        price = (TextView) view.findViewById(R.id.price);

    }

}
