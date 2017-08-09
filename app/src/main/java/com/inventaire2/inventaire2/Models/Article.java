package com.inventaire2.inventaire2.Models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Fal on 26/07/2017.
 */

public  class Article extends RealmObject {

    @PrimaryKey
    public int Id;
    public String Name;
    public int Price;
    public int Quantite;

    public String getName(){

        return Name;
    }

    public void setName(String name)
    {
        Name = name;
    }

    public int getPrice()
    {
        return Price;
    }

    public void setPrice(int price)
    {
        Price = price;
    }
    public int getQuantite()
    {
        return Quantite;
    }

    public void setQuantite(int quantite)
    {
        Quantite = quantite;
    }

    public int getId()
    {
        return Id;
    }

    public void setId(int id)
    {
        Id = id;
    }

    public Article(String name, int price, int quantite, int id)
    {
        Name = name;
        Price = price;
        Quantite = quantite;
        Id = id;
    }

    public Article()
    {
        super();
    }
}
