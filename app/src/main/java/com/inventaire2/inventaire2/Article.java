package com.inventaire2.inventaire2;

/**
 * Created by Fal on 26/07/2017.
 */

public class Article {
    private String Name;
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

    public Article(String name, int price, int quantite)
    {
        Name = name;
        Price = price;
        Quantite = quantite;
    }

}
