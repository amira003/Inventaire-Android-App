package com.inventaire2.inventaire2.Controllers;

/**
 * Created by Fal on 01/08/2017.
 */

import com.inventaire2.inventaire2.Models.Article;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Oclemy on 6/14/2016 for ProgrammingWizards Channel and http://www.camposha.com.
 */
public class RealmHelper {

    Realm realm;


    public RealmHelper() {
        realm = Realm.getDefaultInstance();
    }

    //WRITE
    public void save2( Article article)
    {

        realm.beginTransaction();

        int nextID;
        Number num =  realm.where(Article.class).max("Id");
        if(num == null) {
            nextID = 1;
        } else {
            nextID = num.intValue() + 1;
        }

        article.setId(nextID);

        realm.copyToRealmOrUpdate(article);

        realm.commitTransaction();

    }

    public ArrayList<Article> getListArticle () {

        RealmResults<Article> result = realm.where(Article.class)
                .findAll();

        ArrayList<Article> list = new ArrayList<>();
        list.addAll(result);
        return list;
    }


    public Article getArticle(int id) {
  RealmResults<Article> result = realm.where(Article.class)
                .equalTo("Id", id)
                .findAll();
        if (result.size() > 0) return result.first();
        return null;
    }


}
